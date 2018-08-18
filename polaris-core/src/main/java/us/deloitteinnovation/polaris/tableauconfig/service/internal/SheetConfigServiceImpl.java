package us.deloitteinnovation.polaris.tableauconfig.service.internal;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.tableauconfig.dao.ISheetConfigDAO;
import us.deloitteinnovation.polaris.tableauconfig.model.Sheet;
import us.deloitteinnovation.polaris.tableauconfig.service.IGroupConfigService;
import us.deloitteinnovation.polaris.tableauconfig.service.ISheetConfigService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by rbentaarit on 4/28/2017.
 */
@Service
public class SheetConfigServiceImpl implements ISheetConfigService {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SheetConfigServiceImpl.class);
    private final ISheetConfigDAO sheetConfigDAO;

    private final IGroupConfigService groupConfigService;

    @Autowired
    public SheetConfigServiceImpl(ISheetConfigDAO sheetConfigDAO, IGroupConfigService groupConfigService) {
        this.sheetConfigDAO = sheetConfigDAO;
        this.groupConfigService = groupConfigService;
    }

    @Override
    public List<Sheet> getAllSheets() {
       List<Sheet> sheets = sheetConfigDAO.getAllSheets();
       sheets.forEach(sheet -> sheet.setGroups(groupConfigService.getGroupBySheetConfig(sheet.getId())));
       return sheets;
    }

    @Override
    public Sheet getSheetById(Integer sheetId) {
        Sheet sheet = sheetConfigDAO.getSheetById(sheetId);
        sheet.setGroups(groupConfigService.getGroupBySheetConfig(sheetId));
        return sheet;
    }

    @Override
    public List<Sheet> getSheetsByTableauConfig(Integer tableauConfigId) {
        List<Sheet> sheets = sheetConfigDAO.getSheetsByTableauConfig(tableauConfigId);
        sheets.forEach(sheet -> sheet.setGroups(groupConfigService.getGroupBySheetConfig(sheet.getId())));
        return sheets;
    }

    @Override
    public Sheet insertSheet(Sheet sheet) {
        Integer sheetId = sheetConfigDAO.insertSheet(sheet);
        return sheetConfigDAO.getSheetById(sheetId);
    }
}
