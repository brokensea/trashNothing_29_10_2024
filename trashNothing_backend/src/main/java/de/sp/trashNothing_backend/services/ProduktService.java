package de.sp.trashNothing_backend.services;

import de.sp.trashNothing_backend.dtos.request.ProduktRequestDTO;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.mapper.ProduktMapper;
import de.sp.trashNothing_backend.repositories.ProduktRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProduktService {
    @Autowired
    ProduktRepository produktRepository;


    public Produkt createProdukt(@Valid ProduktRequestDTO produktRequestDTO){
        Produkt produkt = ProduktMapper.toProdukt(produktRequestDTO);
        return produktRepository.save(produkt);
    }
}
