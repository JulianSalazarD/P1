package gestorAplicaciones.producto;

import gestorAplicaciones.entidades.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Factura {
    //atributos
    private static final ArrayList<Factura> facturas = new ArrayList<Factura>();
    private static  long IDfactura = 100000000;
    private final double ganancia = 1.25;
    private Pedido pedido;
    private Usuario usuario;
    private String Fecha;
    private long ID;
    private double costo;
    private LocalDateTime horaSalida;
    private LocalDateTime horaLLegada;

    //constructor
    public Factura() {
        this.ID = Factura.IDfactura + 1;
    }

    //constrctor
    public Factura(Pedido pedido,Usuario usuario){
        super();
        this.pedido = pedido;
        this.usuario = usuario;
    }

    //metodos getter and setter
    public static long getIDfactura() {
        return IDfactura;
    }

    public static void setIDfactura(long IDfactura) {
        Factura.IDfactura = IDfactura;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public double getGanancia() {
        return ganancia;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public LocalDateTime getHoraLLegada() {
        return horaLLegada;
    }

    public void setHoraLLegada(LocalDateTime horaLLegada) {
        this.horaLLegada = horaLLegada;
    }

    //metodos

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String llegada = this.horaSalida.format(formato) + "\n";
        return "Factura Nro: " + this.getID() + "\n" +
                this.pedido.toString() +
                "Vendido a:\n" + this.usuario.toString() +
                "Hora Salida: " + this.horaSalida.format(formato) +
                "\nHora llegada: " + this.horaLLegada.format(formato);
    }

    public void calcularCostoTotal(double costoCamion, double capacidad){
        //Calcular costo total del pedido
        this.costo = capacidad * 0.05 * costoCamion * this.ganancia;

    }

    public static void agregarFactura(Factura factura){
        Factura.facturas.add(factura);
    }

    public static void historialFacturas(Usuario usuairo){
        /*
            imprime toString() de los objetos de tipo Factura que tieden como atributo al Usuario
            pasado como argumento.
        */

        for (Factura factura : Factura.facturas){
            if(factura.getUsuario() == usuairo){
                System.out.println(factura);
            }
        }
    }

    public String infoViaje(String ubicacion, double tiempo){
        StringBuilder info = new StringBuilder();
        int horas, minutos;
        horas = (int)  Math.floor(tiempo);
        tiempo = (tiempo - (double)horas) * 60;
        minutos = (int) Math.floor(tiempo);
        info.append("Ubicacion actual: ").append(ubicacion).append("\n");
        info.append("Tiempo restante: ").append(horas).append(" h ").append(minutos).append(" m .");
        return info.toString();
    }

    public boolean isFactura(long id, String nombre){
        return this.ID == id && usuario.getNombre().equals(nombre);
    }

    public static Factura buscarFactura(long id, String nombre){
        for (Factura factura : Factura.facturas){
            if (factura.isFactura(id,nombre)) return factura;

        }
        return null;
    }

    public static void actualizarInformacion(){
        
    }
}
