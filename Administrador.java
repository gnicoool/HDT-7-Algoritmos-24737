import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Administrador {
    private ArrayList<Producto> inventarioTot;
    private BinaryTree<String, Producto> arbolxSKU;
    private BinaryTree<String, Producto> arbolxNombre;

    public Administrador(){
        inventarioTot = new ArrayList<>();
        arbolxSKU = new BinaryTree<>();
        arbolxNombre = new BinaryTree<>();
    }
    /*Lector de archivo csv */
    public ArrayList<Producto> leer(String archivo) throws IOException{
       
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            br.readLine();
            String line;
            while ((line= br.readLine()) != null) {
                String[] datos = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                Producto producto = new Producto();
                producto.setSku(datos[0]);
                producto.setNombre(datos[1]);
                producto.setDescripcion(datos[2]);
                String tallasdata = datos[3].trim();
                String[] tallas = tallasdata.split("\\|");
                for(String talla : tallas){
                    String[] separar = talla.split(":");
                    if(separar.length == 2){
                        try {
                            String keyTalla = separar[0].trim().toUpperCase();
                            int cantidad = Integer.parseInt(separar[1].trim());
                            producto.addTalla(keyTalla, cantidad);
                        } catch (NumberFormatException e) {
                            System.err.println("Error al procesar tallas");
                        }
                    }
                }
                inventarioTot.add(producto);
                arbolxSKU.insert(producto.getSku(), producto);
                arbolxNombre.insert(producto.getNombre(), producto);
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Error al leer datos de archivo csv");
            throw e;
        }
        return inventarioTot;
    }

    public Producto searchxSKU(String sku){
        return arbolxSKU.search(sku);
    }

    public Producto searchxNombre(String nombre){
        return arbolxNombre.search(nombre);
    }

    public void insertarArboles(Producto producto){
        arbolxSKU.insert(producto.getSku(), producto);
        arbolxNombre.insert(producto.getNombre(), producto);
    }

    public void listarxsku(){
        arbolxSKU.inOrder();
    }
    public void listarxnombre(){
        arbolxNombre.inOrder();
    }
    public ArrayList<Producto> getInventarioTot() {
        return this.inventarioTot;
    }

    public void setInventarioTot(ArrayList<Producto> inventarioTot) {
        this.inventarioTot = inventarioTot;
    }
    
}
