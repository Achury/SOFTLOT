package test.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;

import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

import com.lotrading.softlot.security.dao.IResourceDao;
import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.security.entity.Resource;
import com.lotrading.softlot.security.entity.Role;

public class ResourceTestNG extends PruebaUnitaria {

	private IResourceDao resourceDao;

	public ResourceTestNG() {
		setUp();
	}

	protected void setUp() {
		try {
			resourceDao = (IResourceDao) ConfiguracionPrueba
					.obtenerBean("resourceDao");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test(groups = { "grupo-crear" })
	public void createResource() throws Exception {
		try {
			assertNotNull(resourceDao);

			Resource resource = new Resource();
			resource.setDescription("boton 8");
			resource.setUrl("/esta_es_la_URL");
			resource.setType("T");
			resource.setAction("Action22");
			resource.setEnteredDate(new Date(1000));
			int _tmpReturn = resourceDao.createResource(resource);
			if (_tmpReturn > 0) {
				System.out.println("true");
				assertTrue(true);
			} else {
				System.out.println("false");
				assertTrue(false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-obtener" })
	public void searchResources() throws Exception {
		try {
			assertNotNull(resourceDao);

			Resource resource = new Resource();
			resource.setDescription("boto");

			List<Resource> resources = null;

			resources = resourceDao.searchResource(resource);

			// Asserts generales para la lista
			assertTrue(resources != null);
			assertTrue(resources.size() > 0);

			for (Resource _tmpResouorce : resources) {
				// Assert a nivel de cada empleado
				System.out.println(_tmpResouorce.getDescription() + " "
						+ _tmpResouorce.getUrl() + " >> Action: "
						+ _tmpResouorce.getAction());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}

	@Test(groups = { "grupo-actualizar" })
	public void updateEmployeeRoles() throws Exception {
		try {
			assertNotNull(resourceDao);

			Resource resource = new Resource();
			resource.setDescription("boton 8");

			List<Resource> resources = null;
			resources = resourceDao.searchResource(resource);
			resource = resources.get(0);

			System.out.println("\n\n***************");
			System.out.println(resource.getDescription() + "--"
					+ resource.getAction() + "--" + resource.getUrl());
			System.out.println("************\n\n\n");

			resource.setDescription("BOTON 8-");

			boolean _tmpReturn = resourceDao.updateResource(resource);

			assertTrue(_tmpReturn);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertNull("Se produjo un error:" + ex.getMessage(), ex);
		}
	}
}