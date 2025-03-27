import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class BinaryTreeTest {
    BinaryTree<String, Producto> arboltest = new BinaryTree<>();

    @Test
    public void insertTest(){
        Map<String, Integer> tallas = new HashMap<>();
        tallas.put("L", 5);
        tallas.put("M", 3);
        Producto productoTest = new Producto("014", "Camiseta", "Argentina Mundial 2022", tallas);
        arboltest.insert(productoTest.getSku(), productoTest);

        assertEquals("014", arboltest.search("014").getSku());
        assertEquals("Camiseta", arboltest.search("014").getNombre());
    }

    @Test
    public void searchTest(){
        Map<String, Integer> tallas1= new HashMap<>();
        tallas1.put("M", 6);
        Map<String, Integer> tallas2 = new HashMap<>();
        tallas2.put("S", 15);
        Producto productoTest = new Producto("014", "Camiseta", "Argentina Mundial 2022", tallas1);
        Producto productoTest2 = new Producto("015", "Vestido", "Morado flores", tallas2);
        arboltest.insert(productoTest.getSku(), productoTest);
        arboltest.insert(productoTest2.getNombre(), productoTest2);

        assertEquals("014", arboltest.search("014").getSku());
        assertEquals("Vestido", arboltest.search("Vestido").getNombre());
    }
}
