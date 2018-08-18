package us.deloitteinnovation.polaris.tableauconfig.dao;

import us.deloitteinnovation.polaris.tableauconfig.model.Parameter;

import java.util.List;

/**
 * Created by rbentaarit on 4/28/2017.
 */
public interface IParameterConfigDAO {
    List<Parameter> getAllParameters();
    Parameter getParameterById(Integer parameterId);
    List<Parameter> getParametersByTableauConfig(Integer tableauConfigId);
    Integer insertParameter(Parameter parameter);
    int[] insertParameters(List<Parameter> parameters);
}
