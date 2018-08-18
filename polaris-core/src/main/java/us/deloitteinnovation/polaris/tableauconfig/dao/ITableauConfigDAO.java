package us.deloitteinnovation.polaris.tableauconfig.dao;

import us.deloitteinnovation.polaris.tableauconfig.model.Tableau;

import java.util.List;

/**
 * Created by rbentaarit on 4/27/2017.
 */
public interface ITableauConfigDAO {
    List<Tableau> getAllTableau();

    Tableau getTableauById(Integer tableauId);

    Integer insertTableau(Tableau tableau);
}
