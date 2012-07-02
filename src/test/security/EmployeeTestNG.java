package test.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import test.conf.ConfiguracionPrueba;
import test.conf.PruebaUnitaria;

import com.lotrading.softlot.businessPartners.entity.Phone;
import com.lotrading.softlot.security.entity.Employee;
import com.lotrading.softlot.security.entity.Option;
import com.lotrading.softlot.security.entity.Role;
import com.lotrading.softlot.security.logic.IEmployeeLogic;
import com.lotrading.softlot.setup.entity.MasterValue;

public class EmployeeTestNG extends PruebaUnitaria {
   
   private IEmployeeLogic employeeLogic;
   
   public EmployeeTestNG() {
      setUp();
   }
   
   protected void setUp(){
      try  {
    	  employeeLogic = (IEmployeeLogic)ConfiguracionPrueba.obtenerBean("employeeLogic"); 
      } catch (Exception ex)  {
         ex.printStackTrace();
      } 
   }
   
   /*@Test(groups = {"grupo-autenticar"})
   public void authenticateEmployee() throws Exception{
      try  {
    	  Employee employee = new Employee();
    	  employee.setLogin("jftabord");
    	  employee.setPassword("abc");
    	  
    	  Employee _tmpEmp = employeeDao.authenticate(employee);
    	  System.out.println(_tmpEmp.getFirstName());
    	  assertNotNull(_tmpEmp);
      }catch(Exception e){
    	  e.printStackTrace();
          assertNull("Se produjo un error:"+e.getMessage(),e);
      }
   }
   */
   @Test(groups = {"grupo-crear"})
   public void createEmployee() throws Exception{
      try  {
         assertNotNull(employeeLogic);
         
         Employee employee = new Employee();
         employee.setLogin("JM");
         employee.setPassword("123");
         employee.setFirstName("Johnnatan");
         employee.setLastName("Mery");
         employee.setEmail("johnnatan.mery@lotrading.com");        
         employee.getDepartmentLotId().setValueId(21);
         employee.setWorkPhone(new Phone(2));
         employee.setEnteredDate(new Date());
         employee.setUpdatedDate(new Date());
         
         int _tmpReturn = employeeLogic.saveEmployee(employee);
         System.out.println(_tmpReturn);
         assertTrue(_tmpReturn  > 0);
      } catch (Exception ex)  {
         ex.printStackTrace();
         assertNull("Se produjo un error:"+ex.getMessage(),ex);
      } 
   }
   
   @Test(groups = {"grupo-actualizar"})
   public void updateEmployee() throws Exception{
      try  {
         assertNotNull(employeeLogic);
         
         Employee employee = new Employee();
         employee.setLogin("pp");
         
         employee = employeeLogic.loadEmployeeByLogin(employee);
         
         System.out.println("\n\n***************");
         System.out.println(employee.getEmail()+"--"+employee.getFirstName()+"--"+employee.getPassword());
         System.out.println("************\n\n\n");
         
         employee.setPassword("lotrading");
         employee.setEmail("pepe@lotrading.com");
         
         int _tmpReturn = employeeLogic.saveEmployee(employee);
         
         assertTrue(_tmpReturn  > 0);
      } catch (Exception ex)  {
         ex.printStackTrace();
         assertNull("Se produjo un error:"+ex.getMessage(),ex);
      } 
   }
   //@Test(groups = {"grupo-actualizar"})
   public void updateEmployeeRoles() throws Exception{
      try  {
         assertNotNull(employeeLogic);
         
         Employee employee = new Employee();
         employee.setLogin("jdu");
         
         employee = employeeLogic.loadEmployeeByLogin(employee);
         
         System.out.println("\n\n***************");
         System.out.println(employee.getEmail()+"--"+employee.getFirstName()+"--"+employee.getPassword());
         System.out.println("************\n\n\n");
         
         List<Role> roles = new ArrayList<Role>();
         roles.add(new Role(1));
         roles.add(new Role(2));
         roles.add(new Role(3));
         employee.setRoles(roles);
         
         boolean _tmpReturn = employeeLogic.updateEmployeeRoles(employee);
         
         assertTrue(_tmpReturn);
      } catch (Exception ex)  {
         ex.printStackTrace();
         assertNull("Se produjo un error:"+ex.getMessage(),ex);
      } 
   }
   
   @Test(groups = {"grupo-obtener"})
   public void loadEmployeeByLogin() throws Exception{
      try  {
         assertNotNull(employeeLogic);
         
         Employee employee = new Employee();
         employee.setLogin("mp");
         
         employee = employeeLogic.loadEmployeeByLogin(employee);
         System.out.println(employee.getFirstName()+" "+employee.getLastName());
         assertTrue(employee.getPassword()!=null);
      } catch (Exception ex)  {
         ex.printStackTrace();
         assertNull("Se produjo un error:"+ex.getMessage(),ex);
      } 
   }
   
   @Test(groups = {"grupo-obtener"})
   public void searchEmployees() throws Exception{
      try  {
         assertNotNull(employeeLogic);
         
         Employee employee = new Employee();
         employee.setFirstName("juan");
         
         List<Employee> employees = null;
         
         employees = employeeLogic.searchEmployees(employee);
         
         //Asserts generales para la lista
         assertTrue(employees!=null);
         assertTrue(employees.size()>0);
         
       
       
		 for ( Employee elemento: employees ){			 
			 
			 //Assert a nivel de cada empleado
			 assertTrue(elemento.getPassword()!=null);			 
			 System.out.println( elemento.getFirstName() + " " + elemento.getLastName() + " >> Login: " + elemento.getLogin() );
		 }
		 
      } catch (Exception ex)  {
         ex.printStackTrace();
         assertNull("Se produjo un error:"+ex.getMessage(),ex);
      } 
   }
   
   @Test(groups = {"grupo-obtener"})
   public void loadEmployeeRoles() throws Exception{
      try  {
         assertNotNull(employeeLogic);
         
         List<Role> roles = null;
         Employee employee = new Employee();
         employee.setLogin("ju");
         employee = employeeLogic.loadEmployeeByLogin(employee);
         
         roles = employeeLogic.loadEmployeeRoles(false, employee);
         
         for(Role _tmpRole : roles){
        	 System.out.println(">> "+_tmpRole.getName());
         }         
      } catch (Exception ex)  {
         ex.printStackTrace();
         assertNull("Se produjo un error:"+ex.getMessage(),ex);
      } 
   }
   
   @Test(groups = {"grupo-obtener"})
   public void loadOptions() throws Exception{
      try  {
         assertNotNull(employeeLogic);
         
         List<Option> options = null;
         Employee employee = new Employee();
         employee.setEmployeeId(3);
         
         options = employeeLogic.loadOptions(true, employee);
         
         for(Option _tmpOption : options){
        	 System.out.println(">> "+_tmpOption.getName());
         }         
      } catch (Exception ex)  {
         ex.printStackTrace();
         assertNull("Se produjo un error:"+ex.getMessage(),ex);
      } 
   }
   
}