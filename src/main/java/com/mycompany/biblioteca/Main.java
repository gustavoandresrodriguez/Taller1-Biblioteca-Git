package com.mycompany.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    static ArrayList<Cliente> clientes = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static ArrayList<Libro> libros = new ArrayList<>();
    
    static ArrayList<Prestamo> prestamos = new ArrayList<>();
    
    public static void main(String[] args) {
        // Aquí irá el menú (Fase 8)
    }

    static void crearCliente() {
        System.out.print("Ingrese ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese teléfono: ");
        String telefono = sc.nextLine();

        Cliente cliente = new Cliente(id, nombre, telefono);
        clientes.add(cliente);

        System.out.println("Cliente creado correctamente.");
    }
    
    static void listarClientes() {
    if (clientes.isEmpty()) {
        System.out.println("No hay clientes registrados.");
        return;
    }

    System.out.println("Lista de clientes:");

    for (Cliente cliente : clientes) {
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Teléfono: " + cliente.getTelefono());
        System.out.println("----------------------");
        }
    }
    
    static void buscarCliente() {
    System.out.print("Ingrese el ID del cliente a buscar: ");
    int id = sc.nextInt();

    for (Cliente cliente : clientes) {
        if (cliente.getId() == id) {
            System.out.println("Cliente encontrado:");
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Teléfono: " + cliente.getTelefono());
            return;
            }
        }

    System.out.println("Cliente no encontrado.");
    }
    
    static void actualizarCliente() {
    System.out.print("Ingrese el ID del cliente a actualizar: ");
    int id = sc.nextInt();
    sc.nextLine();

    for (Cliente cliente : clientes) {
        if (cliente.getId() == id) {
            System.out.print("Ingrese el nuevo nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Ingrese el nuevo teléfono: ");
            String telefono = sc.nextLine();

            cliente.setNombre(nombre);
            cliente.setTelefono(telefono);

            System.out.println("Cliente actualizado correctamente.");
            return;
            }
        }

    System.out.println("Cliente no encontrado.");
    }
    
    static void eliminarCliente() {
    System.out.print("Ingrese el ID del cliente a eliminar: ");
    int id = sc.nextInt();

    for (Cliente cliente : clientes) {
        if (cliente.getId() == id) {
            clientes.remove(cliente);
            System.out.println("Cliente eliminado correctamente.");
            return;
            }
        }

    System.out.println("Cliente no encontrado.");
    }
    
    public static void crearLibro() {
    System.out.println("\n--- Registrar nuevo libro ---");
    System.out.print("Código: ");
    String codigo = sc.nextLine();

    if (buscarLibro(codigo) != null) {
        System.out.println("Ya existe un libro con ese código.");
        return;
    }

    System.out.print("Título: ");
    String titulo = sc.nextLine();
    System.out.print("Año de publicación: ");
    String anio = sc.nextLine();
    System.out.print("Autor: ");
    String autor = sc.nextLine();

    Libro nuevo = new Libro(codigo, titulo, anio, autor);
    libros.add(nuevo);
    System.out.println("Libro registrado con éxito.");
    }
    
    public static void listarLibros() {
    System.out.println("\n--- Listado de libros ---");
    if (libros.isEmpty()) {
        System.out.println("No hay libros registrados.");
        return;
        }
    for (Libro l : libros) {
        System.out.println(l);
        }
    }
    
    public static Libro buscarLibro(String codigo) {
    for (Libro l : libros) {
        if (l.getCodigo().equalsIgnoreCase(codigo)) {
            return l;
        }
    }
    return null;
    }
    
    public static void actualizarLibro() {
    System.out.print("Ingrese el código del libro a actualizar: ");
    String codigo = sc.nextLine();

    Libro libro = buscarLibro(codigo);
    if (libro == null) {
        System.out.println("Libro no encontrado.");
        return;
    }

    System.out.print("Nuevo título: ");
    String titulo = sc.nextLine();
    System.out.print("Nuevo año de publicación: ");
    String anio = sc.nextLine();
    System.out.print("Nuevo autor: ");
    String autor = sc.nextLine();

    libro.setTitulo(titulo);
    libro.setAnioPublicacion(anio);
    libro.setAutor(autor);

    System.out.println("Libro actualizado correctamente.");
    }
    
    public static void eliminarLibro() {
    System.out.print("Ingrese el código del libro a eliminar: ");
    String codigo = sc.nextLine();

    Libro libro = buscarLibro(codigo);
    if (libro == null) {
        System.out.println("Libro no encontrado.");
        return;
    }

    libros.remove(libro);
    System.out.println("Libro eliminado correctamente.");
    }
    
    public static void crearPrestamo() {
    System.out.println("\n--- Registrar nuevo préstamo ---");
    System.out.print("ID del préstamo: ");
    String idPrestamo = sc.nextLine();

    System.out.print("ID del cliente: ");
    int idCliente = sc.nextInt();
    sc.nextLine();

    Cliente cliente = null;
    for (Cliente c : clientes) {
        if (c.getId() == idCliente) {
            cliente = c;
            break;
        }
    }
    if (cliente == null) {
        System.out.println("Cliente no encontrado.");
        return;
    }

    System.out.print("Código del libro: ");
    String codigoLibro = sc.nextLine();
    Libro libro = buscarLibro(codigoLibro);
    if (libro == null) {
        System.out.println("Libro no encontrado.");
        return;
    }
    if (!libro.isDisponible()) {
        System.out.println("El libro no está disponible actualmente.");
        return;
    }

    Prestamo prestamo = new Prestamo(idPrestamo, cliente, libro, LocalDate.now());
    prestamos.add(prestamo);
    libro.setDisponible(false);

    System.out.println("Préstamo registrado con éxito.");
    }
    
    public static void devolucionPrestamo() {
    System.out.println("\n--- Registrar devolución ---");
    System.out.print("ID del préstamo: ");
    String idPrestamo = sc.nextLine();

    for (Prestamo p : prestamos) {
        if (p.getIdPrestamo().equalsIgnoreCase(idPrestamo)) {
            if (p.getEstado().equals("DEVUELTO")) {
                System.out.println("Este préstamo ya fue devuelto.");
                return;
            }
            p.setEstado("DEVUELTO");
            p.getLibro().setDisponible(true);
            System.out.println("Devolución registrada con éxito.");
            return;
        }
    }
    System.out.println("Préstamo no encontrado.");
    }
    
    public static void listarPrestamos() {
    System.out.println("\n--- Listado de préstamos ---");
    if (prestamos.isEmpty()) {
        System.out.println("No hay préstamos registrados.");
        return;
    }
    for (Prestamo p : prestamos) {
        System.out.println(p);
    }
    }
}