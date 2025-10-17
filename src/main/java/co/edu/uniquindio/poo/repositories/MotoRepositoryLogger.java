package co.edu.uniquindio.poo.repositories;

import java.util.List;
import co.edu.uniquindio.poo.model.Moto;

public class MotoRepositoryLogger {
    private final MotoRepository wrapped;

    public MotoRepositoryLogger(MotoRepository wrapped) {
        this.wrapped = wrapped;
    }

    public List<Moto> getMotos() {
        System.out.println("[LOG] Obteniendo lista de motos");
        return wrapped.getMotos();
    }

    public void addMoto(Moto moto) {
        System.out.println("[LOG] Agregando moto: " + moto.getPlate());
        wrapped.addMoto(moto);
    }

    public boolean deleteMoto(Moto moto) {
        System.out.println("[LOG] Eliminando moto: " + moto.getPlate());
        return wrapped.deleteMoto(moto);
    }

    public Moto searchByPlate(String plate) {
        System.out.println("[LOG] Buscando moto con placa: " + plate);
        return wrapped.searchByPlate(plate);
    }
}