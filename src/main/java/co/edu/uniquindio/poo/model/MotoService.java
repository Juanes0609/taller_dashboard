package co.edu.uniquindio.poo.model;

import java.util.List;
import co.edu.uniquindio.poo.repositories.MotoRepository;
import co.edu.uniquindio.poo.repositories.MotoRepositoryLogger;

public class MotoService {
    private final MotoRepositoryLogger repository;

    public MotoService() {
        this.repository = new MotoRepositoryLogger(MotoRepository.getInstance());
    }

    public List<Moto> getAllMotos() {
        return repository.getMotos();
    }

    public boolean addMoto(Moto moto) {
        if (repository.searchByPlate(moto.getPlate()) != null) {
            System.out.println("Ya existe una moto con la placa " + moto.getPlate());
            return false;
        }
        repository.addMoto(moto);
        return true;
    }

    public boolean deleteMoto(Moto moto) {
        return repository.deleteMoto(moto);
    }

    public Moto searchByPlate(String plate) {
        return repository.searchByPlate(plate);
    }
}