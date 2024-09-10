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

    public static void agregarProductos(Object[][] productos, int id, String nombre, int cantidad){
        try {
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

    public static void restarProductos(Object[][] productos, int id, int cantidad){
        try {

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

    public static int consultarDisponibilidad(Object[][] productos, int id){
        try {
            for (int i = 0; i < productos.length; i++) {
                if (productos[i][0] != null && (int)productos[i][0] == id) {
                    return (int)productos[i][2];
                }
            }
            System.out.println("Producto no encontrado.");
            return -1;
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida. Debe ser un número.");
            return -1;
        }
    }

    public static String listarProductos(Object[][] productos){
        StringBuilder output = new StringBuilder();
        boolean hayProductos = false;

        for (int i = 0; i < productos.length; i++) {
            if (productos[i][0] != null) {
                output.append("ID: ").append(productos[i][0])
                        .append(", Nombre: ").append(productos[i][1])
                        .append(", Cantidad: ").append(productos[i][2])
                        .append("\n");
                hayProductos = true;
            }
        }

        if (!hayProductos) {
            output.append("No hay productos en el inventario.");
        }

        return output.toString().trim();
    }

    public static void ejecutarOpcion(int opcion, Object[][] productos) {
        switch (opcion) {
            case 1:
                try {
                    int id = Integer.parseInt(leerCadena("Ingrese el ID del producto:"));
                    String nombre = leerCadena("Ingrese el Nombre del producto:");
                    int cantidad = Integer.parseInt(leerCadena("Ingrese la cantidad del producto:"));
                    agregarProductos(productos, id, nombre, cantidad);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida. Debe ser un número.");
                }
                break;
            case 2:
                try {
                    int id = Integer.parseInt(leerCadena("Ingrese el ID del producto:"));
                    int cantidad = Integer.parseInt(leerCadena("Ingrese la cantidad a restar:"));
                    restarProductos(productos, id, cantidad);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida. Debe ser un número.");
                }
                break;
            case 3:
                int id = Integer.parseInt(leerCadena("Ingrese el ID del producto:"));
                int disponibilidad = consultarDisponibilidad(productos, id);
                if (disponibilidad != -1) {
                    System.out.println("Cantidad disponible: " + disponibilidad);
                }
                break;
            case 4:
                System.out.println(listarProductos(productos));
                break;
            case 5:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opción Inválida.");
        }
    }
}
