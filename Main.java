/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * @Author: Jackelyn Nicolle Girón Villacinda 
 * Carné: 24737
 */

import java.util.Scanner;
import java.util.Map;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        boolean continuar = true;
        Scanner sc = new Scanner(System.in);
        Administrador admin = new Administrador();

        try{
            admin.leer("inventario_ropa_deportiva_30.csv");
        }catch(IOException e){
            System.err.println("Error en la carga de  archivo");
        }

        while (continuar) {
            System.out.println("");
            System.out.println("----- Menú -----");
            System.out.println("1. Mostrar inventario");
            System.out.println("2. Buscar producto");
            System.out.println("3. Agregar nuevo producto");
            System.out.println("4. Editar Descripcion de producto");
            System.out.println("5. Realizar una venta");
            System.out.println("6. Actualizar inventario de tallas");
            System.out.println("7. Salir");

            int opcionMenu = sc.nextInt();
            sc.nextLine();
            switch (opcionMenu) {
                case 1:/*Listar productos */
                    System.out.println("1. Listar por SKU");
                    System.out.println("2. Listar por nombre");
                    int opListar = sc.nextInt();
                    sc.nextLine();
                    System.out.println("----- Inventario -----");
                    switch (opListar) {
                        case 1:
                            admin.listarxsku();
                            break;
                        case 2:
                            admin.listarxnombre();
                            break;
                        default:
                            break;
                    }
                    
                    break;
                case 2: /*Buscar producto por sku o por nombre */
                    System.out.println("1. Buscar por SKU");
                    System.out.println("2. Buscar por nombre");
                    int opbuscar = sc.nextInt();
                    sc.nextLine();
                    switch (opbuscar) {
                        case 1:
                            System.out.println("Ingrese el sku del producto");
                            String skubuscado = sc.nextLine();
                            Producto encontradosku = admin.searchxSKU(skubuscado);
                            if (encontradosku != null) {
                                System.out.println("Detalles del producto:");
                                System.out.println(encontradosku);
                            }else{
                                System.out.println("El producto no se encontro");
                            }
                            break;
                        case 2:
                            System.out.println("Ingrese el nombre del producto");
                            String nombrebuscado = sc.nextLine();
                            Producto encontradoName = admin.searchxNombre(nombrebuscado);
                            if (encontradoName != null) {
                                System.out.println("Detalles del producto:");
                                System.out.println(encontradoName);
                            }else{
                                System.out.println("El producto no se encontro");
                        }
                            break;
                        default:
                            break;
                    }

                    break;
                case 3:
                    System.out.println("----- Agregar Producto -----");
                    System.out.println("Ingresar SKU: ");
                    String sku = sc.nextLine();
                    System.out.println("Ingresar Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.println("Ingresar Descripcion: ");
                    String descripcion = sc.nextLine();
                    Map<String, Integer> tallas = new HashMap<>();
                    boolean agregartallas = true;
                    while (agregartallas) {
                        System.out.println("Ingresar talla( S, M, L etc): ");
                        String talla = sc.nextLine();
                        System.out.println("Ingresar cantidad disponible: ");
                        int cantidad = sc.nextInt();
                        sc.nextLine();
                        tallas.put(talla, cantidad);

                        System.out.println("Agregar mas tallas y/n : ");
                        String respuesta = sc.nextLine().toLowerCase();
                        agregartallas = respuesta.equals("y");
                    }
                    Producto nuevProducto  = new Producto(sku, nombre, descripcion, tallas);
                    admin.getInventarioTot().add(nuevProducto);
                    admin.insertarArboles(nuevProducto);
                    System.out.println("Se agrego correctamente el nuevo producto");
                    break;
                case 4:/*Editar descripcion */
                    System.out.println("Ingrese sku del producto:");
                    String skuedit = sc.nextLine();
                    Producto producEdit = admin.searchxSKU(skuedit);

                    if (producEdit != null) {
                        System.out.println("Descripcion actual: " + producEdit.getDescripcion());
                        System.out.println("Escriba la nueva descripcion: ");
                        String newDescripcion = sc.nextLine();
                        producEdit.setDescripcion(newDescripcion);
                        System.out.println("Descripcion actualizada correctamente");
                    }else {
                        System.out.println("No se encontro el prooducto");
                    }
                    break;
                case 5:/*Realizar  una venta */
                    System.out.println("Ingrese sku del producto:");
                    String skuVenta = sc.nextLine();
                    Producto producVenta = admin.searchxSKU(skuVenta);
                    if (producVenta != null) {
                        System.out.println("Tallas disponibles: " + producVenta.getTallasDispo());
                        System.out.println("Ingrese la talla para  vender: ");
                        String tallaVenta = sc.nextLine().toUpperCase();
                        System.out.println("Ingrese la  cantidad para vender: ");
                        int cantidadVenta = sc.nextInt();
                        sc.nextLine();
                        boolean vendido = producVenta.venderTalla(tallaVenta, cantidadVenta);
                        if (vendido) {
                            System.out.println("Se realizo la venta correctamente");
                        }else{
                            System.out.println("No se encontro el producto");
                        }
                    }
                    break;
                case 6:/*Actualizar las tallas */
                    System.out.println("Ingrese sku del producto:");
                    String skuUpdate = sc.nextLine();
                    Producto producUpdate = admin.searchxSKU(skuUpdate);
                    System.out.println("1. Agregar nueva talla");
                    System.out.println("2. Actualizar cantidad de una talla");
                    int optalla = sc.nextInt();
                    sc.nextLine();
                    switch (optalla) {
                        case 1:
                            if (producUpdate !=null) {
                                System.out.println("Tallas actuales: "+ producUpdate.getTallasDispo());
                                System.out.println("Ingrese la talla que desea agregar: ");
                                String tallanueva = sc.nextLine().toUpperCase();
                                System.out.println("Ingrese la cantidad a añadir: ");
                                int cantidanueva = sc.nextInt();
                                sc.nextLine();
                                producUpdate.addTalla(tallanueva, cantidanueva);
                                System.out.println("Se agrego correctamente la nueva talla." +producUpdate.getTallasDispo() );
                            }else{
                                System.out.println("No se encontro el producto");
                            }
                            break;
                        case 2:
                            if (producUpdate != null) {
                                System.out.println("Tallas actuales: "+ producUpdate.getTallasDispo());
                                System.out.println("Ingrese la talla que desea actualizar: ");
                                String tallaUpdate = sc.nextLine().toUpperCase();
                                System.out.println("Ingrese la cantidad a añadir: ");
                                int cantidadUpdate = sc.nextInt();
                                sc.nextLine();
                                producUpdate.UpdateTalla(tallaUpdate, cantidadUpdate);
                                System.out.println("Se realizo la actualizacion correctamente" + producUpdate.getTallasDispo());
                            }else{
                                System.out.println("No se encontro el producto");
                            }
                            break;
                        default:
                            break;
                    }
                    

                    break;
                case 7:
                    System.out.println("Saliendo del programa");
                    continuar = false;
                    return;
                default:
                    System.out.println("Ingrese una opcion valida, entre 1 y 6");
                    break;
            }
        }
        sc.close();
    }
}
