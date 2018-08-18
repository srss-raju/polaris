package us.deloitteinnovation.polaris.tableauconfig.dao;

import us.deloitteinnovation.polaris.tableauconfig.model.Sheet;

import java.util.List;

/**
 * Created by rbentaarit on 4/28/2017.
 */
public interface ISheetConfigDAO {
    List<Sheet> getAllSheets();

    Sheet getSheetById(Integer sheetId);

    List<Sheet> getSheetsByTableauConfig(Integer tableauConfigId);

    Integer insertSheet(Sheet sheet);
}
