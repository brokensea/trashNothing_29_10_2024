package de.sp.trashNothing_backend.services;

import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.repositories.ProduktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProduktService {
    @Autowired
    ProduktRepository produktRepository;

    public Produkt createProdukt(Produkt produkt){
        return produktRepository.save(produkt);
    }
}
