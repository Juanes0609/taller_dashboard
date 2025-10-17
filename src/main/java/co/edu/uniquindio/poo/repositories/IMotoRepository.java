package co.edu.uniquindio.poo.repositories;

import java.util.List;

import co.edu.uniquindio.poo.model.Moto;

public interface IMotoRepository {
    List<Moto> getMotos ();
    void addMoto(Moto moto);
    boolean deleteMoto(Moto moto);
    Moto searchByPlate(String plate);
}
