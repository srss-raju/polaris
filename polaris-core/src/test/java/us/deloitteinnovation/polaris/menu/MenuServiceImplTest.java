package us.deloitteinnovation.polaris.menu;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.stereotype.Component;
import us.deloitteinnovation.polaris.common.AbstractTest;
import us.deloitteinnovation.polaris.menu.dao.internal.MenuDAOImpl;
import us.deloitteinnovation.polaris.menu.service.IMenuService;
import us.deloitteinnovation.polaris.menu.service.internal.MenuServiceImpl;

@Component
public class MenuServiceImplTest extends AbstractTest {

	@Mock
	private MenuDAOImpl menuDAO;

	private IMenuService menuService;

	@Before
	public void setup() {
		menuService = new MenuServiceImpl(menuDAO);
	}
	
	@Test
	public void testGetMenuDetails(){
		menuService.getMenuDetails();
	}
}
