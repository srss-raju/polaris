package us.deloitteinnovation.polaris.tableauconfig.service;

import us.deloitteinnovation.polaris.tableauconfig.model.Sheet;

import java.util.List;

/**
 * Created by rbentaarit on 4/28/2017.
 */

public interface ISheetConfigService {
    List<Sheet> getAllSheets();
    Sheet getSheetById(Integer sheetId);
    List<Sheet> getSheetsByTableauConfig(Integer tableauConfigId);
    Sheet insertSheet(Sheet sheet);
}
