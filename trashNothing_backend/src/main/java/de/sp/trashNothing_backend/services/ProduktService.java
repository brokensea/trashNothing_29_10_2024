package de.sp.trashNothing_backend.services;

import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.repositories.ProduktRepository;

import java.util.List;
import java.util.Set;

public class ProduktService {

    ProduktRepository produktRepository;

    public ProduktService(ProduktRepository produktRepository) {
        this.produktRepository = produktRepository;
    }


}