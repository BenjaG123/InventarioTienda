import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class InventarioTiendaTest {

    Object[][] productos = new Object[10][3];

    @BeforeEach
    public void setUp() {
        InventarioTienda.agregarProductos(productos, 1,"Manzana", 20);
        InventarioTienda.agregarProductos(productos, 2,"Pera", 20);
        InventarioTienda.agregarProductos(productos, 3,"Naranja", 30);
        InventarioTienda.agregarProductos(productos, 4,"Papa", 40);
        InventarioTienda.agregarProductos(productos, 5,"Empanada", 20);

    }

    @Test
    public void testAgregarProducto() {
        InventarioTienda.agregarProductos(productos, 1, "Manzana", 20);
        assertEquals(20, InventarioTienda.consultarDisponibilidad(productos, 1));
    }

    @Test
    public void testRestarProducto() {
        InventarioTienda.agregarProductos(productos, 2, "Pera", 20);
        InventarioTienda.restarProductos(productos, 2, 5);
        assertEquals(15, InventarioTienda.consultarDisponibilidad(productos, 2));
    }

    @Test
    public void testConsultarDisponibilidad() {
        InventarioTienda.agregarProductos(productos, 3, "Naranja", 30);
        assertEquals(30, InventarioTienda.consultarDisponibilidad(productos, 3));
    }

    @Test
    public void testListarProductosConProductos() {
        InventarioTienda.agregarProductos(productos, 1, "Manzana", 20);
        InventarioTienda.agregarProductos(productos, 2, "Pera", 20);

        String output = InventarioTienda.listarProductos(productos);

        assertTrue(output.contains("ID: 1, Nombre: Manzana, Cantidad: 20"));
        assertTrue(output.contains("ID: 2, Nombre: Pera, Cantidad: 20"));
        assertFalse(output.contains("No hay productos en el inventario."));
    }

    @Test
    public void testListarProductosSinProductos() {
        String output = InventarioTienda.listarProductos(productos);
        assertEquals("No hay productos en el inventario.", output);
    }
}
