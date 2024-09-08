import java.util.Scanner;

public class InventarioTienda {

    public static void main(String[] args) {
        Object[][] productos = new Object[10][3];
        ejecutarMenu(productos);
    }

    public static void ejecutarMenu(Object[][] productos){
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            ejecutarOpcion(opcion, productos);
        } while (opcion != 5);
    }

    public static void mostrarMenu(){
        System.out.println("--Inventario de Tienda--");
        System.out.println("1) Agregar Producto");
        System.out.println("2) Restar Producto");
        System.out.println("3) Consultar Disponibilidad");
        System.out.println("4) Listar Todo");
        System.out.println("5) Salir");
        System.out.print("Ingrese una opcion: ");
    }

    public static int leerOpcion(){
        int opcion = 0;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                if (opcion >= 1 && opcion <= 5) {
                    break;
                } else {
                    System.out.println("Opcion Invalida. Intente de nuevo: ");
                }
            } else {
                System.out.println("Entrada Invalida. Intente de nuevo: ");
                scanner.next();
            }
        }
        return opcion;
    }

    public static String leerCadena(String mensaje){
        Scanner scanner = new Scanner(System.in);
        System.out.println(mensaje);
        return scanner.nextLine();
    }

    public static void agregarProductos(Object[][] productos){
        try {
            int id = Integer.parseInt(leerCadena("Ingrese el ID del producto:"));
            String nombre = leerCadena("Ingrese el nombre del producto:");
            int cantidad = Integer.parseInt(leerCadena("Ingrese la cantidad del producto:"));
            boolean productoEncontrado = false;
            for (int i = 0; i < productos.length; i++) {
                if (productos[i][0] != null && (int)productos[i][0] == id) {
                    int cantidadActual = (int)productos[i][2];
                    productos[i][2] = cantidadActual + cantidad;
                    productoEncontrado = true;
                    System.out.println("Cantidad actualizada.");
                    break;
                }
            }
            if (!productoEncontrado) {
                boolean espacioEncontrado = false;
                for (int i = 0; i < productos.length; i++) {
                    if (productos[i][0] == null) {
                        productos[i][0] = id;
                        productos[i][1] = nombre;
                        productos[i][2] = cantidad;
                        espacioEncontrado = true;
                        System.out.println("Producto agregado.");
                        break;
                    }
                }
                if (!espacioEncontrado) {
                    System.out.println("No hay espacio para agregar más productos.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida. Debe ser un número.");
        }
    }

    public static void restarProductos(Object[][] productos){
        try {
            int id = Integer.parseInt(leerCadena("Ingrese el ID del producto:"));
            int cantidad = Integer.parseInt(leerCadena("Ingrese la cantidad a restar:"));
            boolean productoEncontrado = false;
            for (int i = 0; i < productos.length; i++) {
                if (productos[i][0] != null && (int)productos[i][0] == id) {
                    int cantidadActual = (int)productos[i][2];
                    if (cantidadActual >= cantidad) {
                        productos[i][2] = cantidadActual - cantidad;
                        productoEncontrado = true;
                        System.out.println("Cantidad restada.");
                    } else {
                        System.out.println("Cantidad a restar mayor que la disponible.");
                    }
                    break;
                }
            }

            if (!productoEncontrado) {
                System.out.println("Producto no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida. Debe ser un número.");
        }
    }

    public static void consultarDisponibilidad(Object[][] productos){
        try {
            int id = Integer.parseInt(leerCadena("Ingrese el ID del producto:"));
            boolean productoEncontrado = false;
            for (int i = 0; i < productos.length; i++) {
                if (productos[i][0] != null && (int)productos[i][0] == id) {
                    System.out.println("Cantidad disponible: " + productos[i][2]);
                    productoEncontrado = true;
                    break;
                }
            }
            if (!productoEncontrado) {
                System.out.println("Producto no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida. Debe ser un número.");
        }
    }

    public static void listarProductos(Object[][] productos){
        boolean hayProductos = false;

        for (int i = 0; i < productos.length; i++) {
            if (productos[i][0] != null) {
                System.out.println("ID: " + productos[i][0] +
                        ", Nombre: " + productos[i][1] +
                        ", Cantidad: " + productos[i][2]);
                hayProductos = true;
            }
        }

        if (!hayProductos) {
            System.out.println("No hay productos en el inventario.");
        }
    }

    public static void ejecutarOpcion(int opcion, Object[][] productos){
        switch (opcion) {
            case 1:
                agregarProductos(productos);
                break;
            case 2:
                restarProductos(productos);
                break;
            case 3:
                consultarDisponibilidad(productos);
                break;
            case 4:
                listarProductos(productos);
                break;
            case 5:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opcion Invalida.");
        }
    }
}
