package us.deloitteinnovation.polaris.tableauconfig.service;

import us.deloitteinnovation.polaris.tableauconfig.model.Parameter;

import java.util.List;

/**
 * Created by rbentaarit on 4/28/2017.
 */
public interface IParameterConfigService {
    List<Parameter> getAllParameters();
    Parameter getParameterById(Integer parameterId);
    List<Parameter> getParametersByTableauConfig(Integer tableauConfigId);
    Parameter insertParameter(Parameter parameter);
    List<Parameter> insertParameters(List<Parameter> parameters);
}
