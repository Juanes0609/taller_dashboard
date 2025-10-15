package co.edu.uniquindio.poo.repositories;

import java.util.ArrayList;
import co.edu.uniquindio.poo.model.Moto;
/**
 * Repositorio centralizado para gestionar los motoos
 * Singleton para garantizar una única instancia en toda la aplicación
 */
public class MotoRepository {
    private static MotoRepository instance;
    private ArrayList<Moto> motos;

    private MotoRepository() {
        motos = new ArrayList<>();
        loadDataExample();
    }

    /**
     * Obtiene la instancia única del repositorio
     */
    public static MotoRepository getInstance() {
        if (instance == null) {
            instance = new MotoRepository();
        }
        return instance;
    }

    /**
     * Carga algunos Motos de ejemplo
     */
    private void loadDataExample() {
        motos.add(new Moto("P001", "Yamaha", "2021"));
        motos.add(new Moto("P002", "Suzuki", "2017"));
        motos.add(new Moto("P003", "BMW", "2025"));
    }

    /**
     * Obtiene todos los Motos
     */
    public ArrayList<Moto> getMotos() {
        return motos;
    }

    /**
     * Agrega un nuevo Moto
     */
    public void addMoto(Moto moto) {
        motos.add(moto);
    }

    /**
     * Elimina un Moto
     */
    public boolean deleteMoto(Moto moto) {
        return motos.remove(moto);
    }

    /**
     * Busca un Moto por código
     */
    public Moto searchByPlate(String plate) {
        return motos.stream()
                .filter(p -> p.getPlate().equals(plate))
                .findFirst()
                .orElse(null);
    }

    /**
     * Obtiene la cantidad de Motos
     */
    public int getCantidadMotos() {
        return motos.size();
    }
}

