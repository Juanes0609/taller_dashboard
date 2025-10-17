package co.edu.uniquindio.poo.model;

import java.util.List;

import co.edu.uniquindio.poo.repositories.MotoRepository;

public class MotoService {
    private final MotoRepository repository = MotoRepository.getInstance();

    public List<Moto> getAllMotos () {
        return repository.getMotos();
    }

    public boolean addMoto (Moto moto) {
        if (repository.searchByPlate(moto.getPlate()) != null) return false;
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
