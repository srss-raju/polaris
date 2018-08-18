package us.deloitteinnovation.polaris.tableauconfig.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.tableauconfig.dao.ITableauConfigDAO;
import us.deloitteinnovation.polaris.tableauconfig.model.Tableau;
import us.deloitteinnovation.polaris.tableauconfig.service.IParameterConfigService;
import us.deloitteinnovation.polaris.tableauconfig.service.ISheetConfigService;
import us.deloitteinnovation.polaris.tableauconfig.service.ITableauConfigService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by rbentaarit on 4/28/2017.
 */
@Service
public class TableauConfigServiceImpl implements ITableauConfigService {

    private final ITableauConfigDAO tableauConfigDAO;
    private final ISheetConfigService sheetConfigService;
    private final IParameterConfigService parameterConfigService;

    @Autowired
    public TableauConfigServiceImpl(ITableauConfigDAO tableauConfigDAO, ISheetConfigService sheetConfigService, IParameterConfigService parameterConfigService) {
        this.tableauConfigDAO = tableauConfigDAO;
        this.sheetConfigService = sheetConfigService;
        this.parameterConfigService = parameterConfigService;
    }

    @Override
    public List<Tableau> getAllTableau() {
       List<Tableau> tableauList = tableauConfigDAO.getAllTableau();
       tableauList.stream().forEach(tableau -> {
           tableau.setSheets(sheetConfigService.getSheetsByTableauConfig(tableau.getId()));
           tableau.setParameters(parameterConfigService.getParametersByTableauConfig(tableau.getId()));
       });
        return tableauList;
    }

    @Override
    public Tableau getTableauById(Integer tableauId) {
        Tableau tableau = tableauConfigDAO.getTableauById(tableauId);
        tableau.setSheets(sheetConfigService.getSheetsByTableauConfig(tableauId));
        tableau.setParameters(parameterConfigService.getParametersByTableauConfig(tableauId));
        return tableau;
    }

    @Override
    public Tableau insertTableau(Tableau tableau) {
        Integer tableauId = tableauConfigDAO.insertTableau(tableau);
        return tableauConfigDAO.getTableauById(tableauId);
    }
}
