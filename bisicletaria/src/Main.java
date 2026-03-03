import java.util.ArrayList;

// -------------------- CLIENTE --------------------
class Cliente {
    private String nombre;
    private String telefono;

    public Cliente(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente: " + nombre + " | Teléfono: " + telefono;
    }
}

// -------------------- BICICLETA --------------------
class Bicicleta {
    private String marca;
    private String modelo;
    private Cliente cliente;

    public Bicicleta(String marca, String modelo, Cliente cliente) {
        this.marca = marca;
        this.modelo = modelo;
        this.cliente = cliente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Bicicleta: " + marca + " " + modelo + " | Dueño: " + cliente.getNombre();
    }
}

// -------------------- MECANICO --------------------
class Mecanico {
    private String nombre;

    public Mecanico(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Mecánico: " + nombre;
    }
}

// -------------------- REPUESTO --------------------
class Repuesto {
    private String nombre;
    private int cantidad;
    private double costoUnitario;

    public Repuesto(String nombre, int cantidad, double costoUnitario) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public double calcularSubtotal() {
        return cantidad * costoUnitario;
    }

    @Override
    public String toString() {
        return nombre + " | Cantidad: " + cantidad +
                " | Costo unitario: $" + costoUnitario;
    }
}

// -------------------- ORDEN DE SERVICIO --------------------
class OrdenServicio {
    private Bicicleta bicicleta;
    private String descripcion;
    private String fechaIngreso;
    private String estado;
    private Mecanico mecanico;
    private ArrayList<String> tareas;
    private ArrayList<Repuesto> repuestos;

    public OrdenServicio(Bicicleta bicicleta, String descripcion, String fechaIngreso) {
        this.bicicleta = bicicleta;
        this.descripcion = descripcion;
        this.fechaIngreso = fechaIngreso;
        this.estado = "Recibida";
        this.tareas = new ArrayList<>();
        this.repuestos = new ArrayList<>();
    }

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public double calcularCostoTotal() {
        double total = 0;
        for (Repuesto r : repuestos) {
            total += r.calcularSubtotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "\n----- ORDEN DE SERVICIO -----\n" +
                bicicleta +
                "\nProblema: " + descripcion +
                "\nFecha ingreso: " + fechaIngreso +
                "\nEstado: " + estado +
                "\nMecánico: " + (mecanico != null ? mecanico.getNombre() : "No asignado") +
                "\nTareas: " + tareas +
                "\nCosto total: $" + calcularCostoTotal();
    }
}

// -------------------- MAIN --------------------
public class Main {

    public static void main(String[] args) {

        Cliente cliente1 = new Cliente("Juan Pérez", "3001234567");

        Bicicleta bici1 = new Bicicleta("GW", "Rin 29", cliente1);

        Mecanico mecanico1 = new Mecanico("Carlos");

        OrdenServicio orden1 = new OrdenServicio(
                bici1,
                "Frenos dañados",
                "03/03/2026"
        );

        orden1.asignarMecanico(mecanico1);

        orden1.agregarTarea("Ajuste de frenos");
        orden1.agregarTarea("Cambio de guayas");

        orden1.agregarRepuesto(new Repuesto("Guaya de freno", 2, 15000));
        orden1.agregarRepuesto(new Repuesto("Pastillas", 1, 25000));

        orden1.setEstado("En proceso");

        System.out.println(orden1);

        orden1.setEstado("Finalizada");
        System.out.println("\nEstado actualizado: " + orden1.getEstado());
    }
}