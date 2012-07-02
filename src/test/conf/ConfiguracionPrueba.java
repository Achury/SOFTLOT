package test.conf;

import java.util.Hashtable;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfiguracionPrueba {
    
    public ConfiguracionPrueba() {
    }
    
    private static Hashtable beans = new Hashtable();
    private static BeanFactory factory = null;
    
    static {
        try 
        {
            System.out.println("SET UP");
            ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"/test/conf/applicationContext.xml"});
            factory = (BeanFactory) context;
        } catch (Exception e) {
            System.out.println("Se presento un error*");
            e.printStackTrace();
        }
    }
    
    public static synchronized Object obtenerBean(String nombre) {
        Object retorno = null;
        try 
        {
            if (! beans.containsKey(nombre)) {
                retorno = factory.getBean(nombre);
                beans.put(nombre, retorno);
            } else {
                retorno = beans.get(nombre);
            }
        } catch (Exception e) {
            System.out.println("Se presento un error");
            e.printStackTrace();
        }
        return retorno;
    }
}
