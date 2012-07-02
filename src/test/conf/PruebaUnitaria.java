package test.conf;

public abstract class PruebaUnitaria {
    
    public void assertNotNull(Object o) {
        assert o != null;
    }

    public void assertNotNull(String msg, Object o) {
        assertNotNull(o);
    }   
    
    public void assertNull(Object o) {
        assert o == null;
    }
    
    public void assertNull(String msg, Object o) {
        assertNull(o);
    }    

    public void assertTrue(String msg, boolean condicion) {
        assertTrue(condicion);
    }

    public void assertTrue(boolean condicion) {
        assert condicion == true;
    }
}