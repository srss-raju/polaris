package us.deloitteinnovation.polaris.evaluator.chart.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.evaluator.calculation.model.ValueDetails;
import us.deloitteinnovation.polaris.evaluator.chart.model.Chart;
import us.deloitteinnovation.polaris.evaluator.chart.service.IChartService;
import us.deloitteinnovation.polaris.evaluator.scenario.dao.IScenarioDAO;
import us.deloitteinnovation.polaris.evaluator.scenario.model.Scenario;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

/**
 * Created by rbentaarit on 7/6/2017.
 */
@Service
public class ChartServiceImpl implements IChartService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DecimalFormat decimalFormatter = new DecimalFormat("#0.00");
    private static final Integer WEEK = 7;

    private final IScenarioDAO scenarioDAO;
    @Autowired
    public ChartServiceImpl(IScenarioDAO scenarioDAO) {
        this.scenarioDAO = scenarioDAO;
    }

    @Override
    public Chart calculateChart(Integer scenarioId, Boolean isPlan) {
        Scenario scenario = isPlan ? scenarioDAO.getPlanDetails(scenarioId) : scenarioDAO.getScenario(scenarioId);
        Map<String, Double> values = new HashMap<>();
        Chart chart = new Chart();
        chart.setId(scenarioId);
        LocalDate start = Instant.ofEpochMilli(new Date().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = Instant.ofEpochMilli(new Date().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        start = start.with(firstDayOfYear());
        end = start.with(lastDayOfYear());


        Map<LocalDate, ValueDetails> val = new HashMap<>();

        while (!start.isAfter(end)){
            Double randVal = Double.valueOf(decimalFormatter.format(ThreadLocalRandom.current().nextDouble(10000d, 50000d)));
            val.put(start,new ValueDetails("Total Discount",randVal,43.2, "fiancial","USD",'K'));
            start = start.plusDays(WEEK);
        }

        Map<LocalDate, ValueDetails> monthMap = initMonths();

        Map<LocalDate, ValueDetails> sMap =  val.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (u, u2) -> u, LinkedHashMap::new));


        monthMap.entrySet().forEach(monthDoubleEntry -> monthDoubleEntry.setValue(new ValueDetails("Test",
                sMap.entrySet().stream()
                        .filter(weekDoubleEntry -> monthDoubleEntry.getKey().getMonth() == weekDoubleEntry.getKey().getMonth())
                        .mapToDouble(value -> value.getValue().getFullValue()).sum(),3d, "TEST", "USD", 'K')));
        chart.setWeeklyValues(sMap);
        chart.setMonthlyValues(monthMap);

        return chart;
    }

    private Map<LocalDate, ValueDetails> initMonths(){
        Map<LocalDate, ValueDetails> monthMap = new HashMap<>();
        LocalDate start = Instant.ofEpochMilli(new Date().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end =  start.with(lastDayOfYear());
        start = start.with(firstDayOfYear());
        while (!start.isAfter(end)){
            monthMap.put(start, new ValueDetails());
            start = start.plusMonths(1);
        }
       return monthMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(u, u2) -> u, LinkedHashMap::new));
    }
}
