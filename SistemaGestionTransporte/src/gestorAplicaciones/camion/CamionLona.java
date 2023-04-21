package gestorAplicaciones.camion;

import gestorAplicaciones.util.Pair;

import java.util.ArrayList;
import java.util.Map;

public class CamionLona extends Camion {
    public final static ArrayList<CamionLona> camiones = new ArrayList<CamionLona>();

    public CamionLona(String placa, String pais, double pesoMaximo, double capacidad) {
        super(placa, pais, pesoMaximo, capacidad);
    }

    public static ArrayList<CamionLona> getCamiones() {
        return CamionLona.camiones;
    }

    @Override
    public void calcularCostoCamion() {
        double factor = 0.005;
        double km = this.getRuta().get(this.getRuta().size() - 1).getValue();
        this.setCosto(km * this.getCapacidad() * factor);
    }

    @Override
    public boolean comprobarPlaca(String placa) {
        return false;
    }

    @Override
    public String ubicacion() {
        return null;
    }

    @Override
    public String tiempoRestante() {
        return null;
    }
}