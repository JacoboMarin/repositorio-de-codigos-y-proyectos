import java.util.ArrayList;
import java.util.Scanner;

class Cliente {
    String nombre;
    String telefono;
    ArrayList<Bicicleta> bicicletas = new ArrayList<>();

    public Cliente(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public void agregarBicicleta(Bicicleta bici) {
        bicicletas.add(bici);
    }
}

class Bicicleta {
    String marca;
    String modelo;

    public Bicicleta(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }
}

class Mecanico {
    String nombre;

    public Mecanico(String nombre) {
        this.nombre = nombre;
    }
}

class Repuesto {
    String nombre;
    int cantidad;
    double costoUnitario;

    public Repuesto(String nombre, int cantidad, double costoUnitario) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
    }

    public double calcularCosto() {
        return cantidad * costoUnitario;
    }
}

class OrdenServicio {
    Bicicleta bicicleta;
    String descripcionProblema;
    String fechaIngreso;
    String estado;
    Mecanico mecanico;
    ArrayList<String> tareas = new ArrayList<>();
    ArrayList<Repuesto> repuestos = new ArrayList<>();
    double manoObra;

    public OrdenServicio(Bicicleta bicicleta, String descripcionProblema, String fechaIngreso) {
        this.bicicleta = bicicleta;
        this.descripcionProblema = descripcionProblema;
        this.fechaIngreso = fechaIngreso;
        this.estado = "Recibida";
    }

    public void asignarMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public void agregarTarea(String tarea) {
        tareas.add(tarea);
    }

    public void agregarRepuesto(Repuesto repuesto) {
        repuestos.add(repuesto);
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public double calcularCostoTotal() {
        double totalRepuestos = 0;
        for (Repuesto r : repuestos) {
            totalRepuestos += r.calcularCosto();
        }
        return manoObra + totalRepuestos;
    }

    public void mostrarResumen() {
        System.out.println("\n----- ORDEN DE SERVICIO -----");
        System.out.println("Bicicleta: " + bicicleta.marca + " " + bicicleta.modelo);
        System.out.println("Problema: " + descripcionProblema);
        System.out.println("Fecha ingreso: " + fechaIngreso);
        System.out.println("Estado: " + estado);
        System.out.println("Mecánico: " + (mecanico != null ? mecanico.nombre : "No asignado"));
        System.out.println("Tareas: " + tareas);
        System.out.println("Costo total: $" + calcularCostoTotal());
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Mecanico> mecanicos = new ArrayList<>();
        ArrayList<OrdenServicio> ordenes = new ArrayList<>();

        mecanicos.add(new Mecanico("Carlos"));
        mecanicos.add(new Mecanico("Luis"));

        int opcion;

        do {
            System.out.println("\n--- MENU TALLER ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Crear orden de servicio");
            System.out.println("3. Cambiar estado de orden");
            System.out.println("4. Mostrar ordenes");
            System.out.println("5. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Nombre cliente: ");
                    String nombre = sc.nextLine();
                    System.out.print("Telefono: ");
                    String telefono = sc.nextLine();

                    Cliente cliente = new Cliente(nombre, telefono);

                    System.out.print("Marca bicicleta: ");
                    String marca = sc.nextLine();
                    System.out.print("Modelo bicicleta: ");
                    String modelo = sc.nextLine();

                    Bicicleta bici = new Bicicleta(marca, modelo);
                    cliente.agregarBicicleta(bici);

                    clientes.add(cliente);
                    System.out.println("Cliente registrado.");
                    break;

                case 2:
                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados.");
                        break;
                    }

                    Cliente c = clientes.get(0); // simplificado

                    Bicicleta b = c.bicicletas.get(0);

                    System.out.print("Descripción del problema: ");
                    String desc = sc.nextLine();

                    System.out.print("Fecha ingreso: ");
                    String fecha = sc.nextLine();

                    OrdenServicio orden = new OrdenServicio(b, desc, fecha);

                    orden.asignarMecanico(mecanicos.get(0));

                    System.out.print("Costo mano de obra: ");
                    orden.manoObra = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Agregar tarea: ");
                    orden.agregarTarea(sc.nextLine());

                    System.out.print("Nombre repuesto: ");
                    String nomRep = sc.nextLine();
                    System.out.print("Cantidad: ");
                    int cant = sc.nextInt();
                    System.out.print("Costo unitario: ");
                    double costo = sc.nextDouble();
                    sc.nextLine();

                    orden.agregarRepuesto(new Repuesto(nomRep, cant, costo));

                    ordenes.add(orden);
                    System.out.println("Orden creada.");
                    break;

                case 3:
                    if (!ordenes.isEmpty()) {
                        OrdenServicio o = ordenes.get(0);
                        System.out.println("1. En proceso");
                        System.out.println("2. Finalizada");
                        System.out.println("3. Entregada");
                        int est = sc.nextInt();
                        sc.nextLine();

                        if (est == 1) o.cambiarEstado("En proceso");
                        if (est == 2) o.cambiarEstado("Finalizada");
                        if (est == 3) o.cambiarEstado("Entregada");

                        System.out.println("Estado actualizado.");
                    }
                    break;

                case 4:
                    for (OrdenServicio o : ordenes) {
                        o.mostrarResumen();
                    }
                    break;
            }

        } while (opcion != 5);

        sc.close();
    }
}