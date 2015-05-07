package merchants.service.impl.projectInformation;


import merchants.service.inter.projectInformation.ProjectService;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 

public class ProjectServiceImplTest {

	private static ProjectService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext act =  new ClassPathXmlApplicationContext("beans.xml");
		service = (ProjectService) act.getBean("projectServiceImpl");
	}
	@Test
	public void testgetProjectInformation(){
		String  p=service.getProjectInformation("130702");
		System.out.println(p);
	}

}
