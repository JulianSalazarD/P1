package gestorAplicaciones.camion;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;


/**
*  clase CamionPortaCoches es una subclase de la clase Camion
*  Atributos:
*  Camiones:  ArrayList estático de objetos de tipo CamionPortaCoches que almacena
*  todos los camiones PortaCoches creados.
 *
 *  @author Julian Salazar, Michael Garcia
*/
public class CamionPortaCoches extends Camion implements Serializable {

    //serializador
    @Serial
    private static final long serialVersionUID = 1L;

    public static ArrayList<CamionPortaCoches> camiones = new ArrayList<CamionPortaCoches>();
    public CamionPortaCoches(String placa, String pais, String CiudadActual, double pesoMaximo, double capacidad) {
        super(placa, pais, CiudadActual, pesoMaximo, capacidad);
        camiones.add(this);
    }

    public CamionPortaCoches(){}

    public static ArrayList<CamionPortaCoches> getCamiones(){
        return CamionPortaCoches.camiones;
    }

    public static void setCamiones(ArrayList<CamionPortaCoches> camiones){
        CamionPortaCoches.camiones = camiones;
    }

    @Override
    public void calcularCostoCamion() {
        double factor = 0.008;
        double km = this.getRuta().get(this.getRuta().size()-1).getValue();
        this.setCosto(km * this.getCapacidad() * factor);
    }

    @Override
    public double valocidad() {
        double velocidadBase = 80.57;
        double factor = -0.42857;
        return velocidadBase + this.getCapacidad() * factor;
    }

    @Override
    public String toString() {
        return "\nTipo: Portacohes" +
                "\nPlaca: " + this.getPlaca() +
                "\npais: " + this.getPais() +
                "\nCiudad actual: " + this.getCiudadActual() +
                "\nPeso Maximo: " + this.getPesoMaximo() +
                "\nCapacidad: " + this.getCapacidad() +
                "\nDisponible: " + this.isDisponible() +"\n";
    }
}
