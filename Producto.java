import java.util.HashMap;
import java.util.Map;

public class Producto {
    private String sku;
    private String nombre;
    private String descripcion;
    private Map<String, Integer> tallasDispo;

    public Producto(){}

    public Producto(String sku, String nombre, String descripcion, Map<String, Integer> tallasDispo){
        this.sku = sku;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tallasDispo = new HashMap<>(tallasDispo);
    }

    /*Metodo que permite agregar más tallas en el producto */
    public void addTalla(String talla, int cantidad){
        tallasDispo.put(talla.toUpperCase(), cantidad);
    }

    /*Metodo que permite la actualizacion en la cantidad de tallas  */
    public void UpdateTalla(String talla, int cantidad){
        if(tallasDispo.containsKey(talla.toUpperCase())){
            tallasDispo.put(talla.toUpperCase(), tallasDispo.getOrDefault(talla.toUpperCase(), 0)+cantidad);
        }
    }

    public void deleteTalla(String talla){
        tallasDispo.remove(talla.toUpperCase());
    }

    public String getSku() {
        return this.sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Map<String, Integer> getTallasDispo(){
        return new HashMap<>(tallasDispo);
    }
    public void setTallasDispo(Map<String, Integer> tallasDispo){
        this.tallasDispo = tallasDispo;
    }

}
