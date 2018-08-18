package us.deloitteinnovation.polaris.tableauconfig.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.deloitteinnovation.polaris.tableauconfig.dao.IParameterConfigDAO;
import us.deloitteinnovation.polaris.tableauconfig.exception.ParameterException;
import us.deloitteinnovation.polaris.tableauconfig.model.Parameter;
import us.deloitteinnovation.polaris.tableauconfig.service.IParameterConfigService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by rbentaarit on 4/28/2017.
 */
@Service
public class ParameterConfigServiceImpl implements IParameterConfigService {

    private final IParameterConfigDAO parameterConfigDAO;

    @Autowired
    public ParameterConfigServiceImpl(IParameterConfigDAO parameterConfigDAO) {
        this.parameterConfigDAO = parameterConfigDAO;
    }

    @Override
    public List<Parameter> getAllParameters() {
        return parameterConfigDAO.getAllParameters();
    }

    @Override
    public Parameter getParameterById(Integer parameterId) {
        return parameterConfigDAO.getParameterById(parameterId);
    }

    @Override
    public List<Parameter> getParametersByTableauConfig(Integer tableauConfigId) {
        return parameterConfigDAO.getParametersByTableauConfig(tableauConfigId);
    }

    @Transactional
    @Override
    public Parameter insertParameter(Parameter parameter) {
        Integer parameterId = parameterConfigDAO.insertParameter(parameter);
        return parameterConfigDAO.getParameterById(parameterId);
    }

    @Transactional
    @Override
    public List<Parameter> insertParameters(List<Parameter> parameters) {
        Integer tableauId = parameters.get(0).getTableauId();
        if (parameters.stream().allMatch(parameter -> parameter.getTableauId().equals(tableauId))) {
            parameterConfigDAO.insertParameters(parameters);
            return parameterConfigDAO.getParametersByTableauConfig(tableauId);
        }
        else {
            throw new ParameterException("All tableau Ids should be the same.");
        }
    }
}
