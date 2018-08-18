package us.deloitteinnovation.polaris.tableauconfig.service;

import us.deloitteinnovation.polaris.tableauconfig.model.Tableau;

import java.util.List;

/**
 * Created by rbentaarit on 4/27/2017.
 */
public interface ITableauConfigService {
    List<Tableau> getAllTableau();
    Tableau getTableauById(Integer tableauId);
    Tableau insertTableau(Tableau tableau);
}
