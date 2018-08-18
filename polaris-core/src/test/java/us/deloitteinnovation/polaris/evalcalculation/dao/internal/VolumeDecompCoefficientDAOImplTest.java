package us.deloitteinnovation.polaris.evalcalculation.dao.internal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.evalcalculation.dao.IVolumeDecompCoefficientDAO;
import us.deloitteinnovation.polaris.evalcalculation.exception.CoefficientNotFoundException;
import us.deloitteinnovation.polaris.evalcalculation.model.VolumeDecompCoefficient;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by hkaja on 02-06-2017.
 */
public class VolumeDecompCoefficientDAOImplTest extends AbstractTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private IVolumeDecompCoefficientDAO volumeDecompCoefficientDAO;

    @Before
    public void setup() {
        volumeDecompCoefficientDAO = new VolumeDecompCoefficientDAOImpl(new JdbcTemplate(dataSource));
    }

    @Test
    public void testFindFirst() throws Exception {
        VolumeDecompCoefficient first = volumeDecompCoefficientDAO.findFirst();
        Assert.assertNotNull(first);
    }

    @Test
    public void testFindFirstForException() throws Exception {
        volumeDecompCoefficientDAO = new VolumeDecompCoefficientDAOImpl(jdbcTemplate);

        thrown.expect(CoefficientNotFoundException.class);
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(new ArrayList<>());
        VolumeDecompCoefficient first = volumeDecompCoefficientDAO.findFirst();
    }


}