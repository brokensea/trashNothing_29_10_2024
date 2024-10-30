package de.sp.trashNothing_backend.services;

import de.sp.trashNothing_backend.dtos.request.ProduktEinkaufenRequestDTO;
import de.sp.trashNothing_backend.dtos.response.ProduktEinkaufenResponseDTO;
import de.sp.trashNothing_backend.entities.Benutzer;
import de.sp.trashNothing_backend.entities.GekauftSet_Produkt;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.mapper.ProduktMapper;
import de.sp.trashNothing_backend.repositories.BenutzerRepository;
import de.sp.trashNothing_backend.repositories.GekauftSet_ProduktRepository;
import de.sp.trashNothing_backend.repositories.ProduktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduktEinkaufenService {

    private final ProduktRepository produktRepository;
    private final BenutzerRepository benutzerRepository;
    private final GekauftSet_ProduktRepository gekauftSet_produktRepository;


    @Autowired
    public ProduktEinkaufenService(ProduktRepository produktRepository, BenutzerRepository benutzerRepository, GekauftSet_ProduktRepository gekauftSet_produktRepository) {
        this.produktRepository = produktRepository;
        this.benutzerRepository = benutzerRepository;
        this.gekauftSet_produktRepository = gekauftSet_produktRepository;
    }

    //morgen weiter Deng
/*
    public ProduktEinkaufenResponseDTO kaufenProdukt(ProduktEinkaufenRequestDTO requestDTO) {

        Benutzer benutzer = benutzerRepository.findById(requestDTO.benutzerId())
                .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden"));


        Produkt produkt = produktRepository.findById(requestDTO.produktId())
                .orElseThrow(() -> new RuntimeException("Produkt nicht gefunden"));


        GekauftSet_Produkt gekauftSetProdukt = gekauftSet_produktRepository.findById(
                benutzer.getGekauftSet().getFirst().getId()
        ).orElse(null);

        if (gekauftSetProdukt != null) {
        }


        Produkt produkt = ProduktMapper.toProdukt(requestDTO, benutzer);
        produkt.setBenutzer(benutzer);
        Produkt savedProdukt = produktRepository.save(produkt);
        return ProduktMapper.toProduktEinkaufenResponseDTO(savedProdukt);
    }*/

    public List<ProduktEinkaufenResponseDTO> getAllProdukte() {
        return produktRepository.findAll().stream()
                .map(ProduktMapper::toProduktEinkaufenResponseDTO)
                .collect(Collectors.toList());
    }
}