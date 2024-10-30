package de.sp.trashNothing_backend.services;

import de.sp.trashNothing_backend.dtos.request.ProduktEinkaufenRequestDTO;
import de.sp.trashNothing_backend.dtos.response.ProduktEinkaufenResponseDTO;
import de.sp.trashNothing_backend.entities.Benutzer;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.mapper.ProduktMapper;
import de.sp.trashNothing_backend.repositories.BenutzerRepository;
import de.sp.trashNothing_backend.repositories.ProduktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduktEinkaufenService {

    private final ProduktRepository produktRepository;
    private final BenutzerRepository benutzerRepository;


    @Autowired
    public ProduktEinkaufenService(ProduktRepository produktRepository, BenutzerRepository benutzerRepository, ProduktMapper produktMapper) {
        this.produktRepository = produktRepository;
        this.benutzerRepository = benutzerRepository;
    }

    public ProduktEinkaufenResponseDTO kaufenProdukt(ProduktEinkaufenRequestDTO requestDTO, Long benutzerId) {
        Benutzer benutzer = benutzerRepository.findById(benutzerId)
                .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden"));
        Produkt produkt = ProduktMapper.toProdukt(requestDTO, benutzer);
        produkt.setBenutzer(benutzer);
        Produkt savedProdukt = produktRepository.save(produkt);
        return ProduktMapper.toProduktEinkaufenResponseDTO(savedProdukt);
    }

    public List<ProduktEinkaufenResponseDTO> getAllProdukte() {
        return produktRepository.findAll().stream()
                .map(ProduktMapper::toProduktEinkaufenResponseDTO)
                .collect(Collectors.toList());
    }
}