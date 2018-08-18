package us.deloitteinnovation.polaris.filter.dao.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import us.deloitteinnovation.polaris.filter.dao.IChartFilterDAO;
import us.deloitteinnovation.polaris.filter.model.ChartFilter;
import us.deloitteinnovation.polaris.filter.model.FilterDetail;
import us.deloitteinnovation.polaris.filter.model.FilterItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by rbentaarit on 2/8/2017.
 */

@Repository
public class ChartFilterDAOImpl implements IChartFilterDAO {

    private static final String GET_CHART_FILTER = "SELECT * FROM [dbo].[tbl_Metadata]  ORDER BY Category";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ChartFilter> getChartFilters() {
        List<FilterItem> filterItems = new ArrayList<>();
        Map<String, List<String>> categoryAttributeMap = new HashMap<>();
        List<ChartFilter> chartFilterResult = new ArrayList<>();
        return jdbcTemplate.query(GET_CHART_FILTER, new ResultSetExtractor<List<ChartFilter>>() {
            @Override
            public List<ChartFilter> extractData(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    String category = rs.getString("Category");
                    String attribute = rs.getString("Attribute");
                    String value = rs.getString("Value");
                    if (!categoryAttributeMap.containsKey(category)) {
                        categoryAttributeMap.put(category, new ArrayList<>(Collections.singletonList(attribute)));
                    } else if (categoryAttributeMap.get(category).indexOf(attribute) == -1) {
                        List<String> attributes = new ArrayList<>(categoryAttributeMap.get(category));
                        attributes.add(attribute);
                        categoryAttributeMap.put(category, attributes);
                    }
                    Optional<FilterItem> filterItemTmp = filterItems.stream().filter(filterIt -> attribute.equals(filterIt.getName())).findFirst();
                    FilterDetail filterDetail = new FilterDetail();
                    filterDetail.setName(value);
                    FilterItem filterItem = new FilterItem();
                    if (!filterItemTmp.isPresent()) {
                        filterItem.setName(attribute);
                        filterItem.setFilterDetails(new ArrayList<>(Collections.singletonList(filterDetail)));
                    } else {
                        filterItem = filterItemTmp.get();
                        List<FilterDetail> filterDetails = new ArrayList<>(filterItem.getFilterDetails());
                        filterDetails.add(filterDetail);
                        filterItem.setFilterDetails(filterDetails);
                        filterItems.remove(filterItemTmp.get());
                    }
                    filterItems.add(filterItem);
                }
                for (Map.Entry<String, List<String>> entry : categoryAttributeMap.entrySet()) {
                    List<FilterItem> filterItemList = new ArrayList<>();
                    for (String itemName : entry.getValue()) {
                        filterItemList.addAll(filterItems.stream().filter(filterItem -> filterItem.getName().equals(itemName)).collect(Collectors.toList()));
                    }
                    chartFilterResult.add(new ChartFilter(entry.getKey(), filterItemList));
                }

                //Item names formatting needed temporary: Replacing underscores by spaces and wrapping the item names by double braces
                chartFilterResult.stream().forEach(chartFilter ->
                        chartFilter.getFilterItems().stream().forEach(filterItem ->
                                filterItem.setName(String.format("{{%s}}", filterItem.getName().replace("_", " ")))));
                return chartFilterResult;
            }
        });
    }

}
