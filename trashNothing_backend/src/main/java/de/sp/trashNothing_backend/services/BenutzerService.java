package de.sp.trashNothing_backend.services;

import de.sp.trashNothing_backend.dtos.request.GetAllSoldProductsRequestDto;
import de.sp.trashNothing_backend.dtos.request.ProduktVerkauftRequestDto;
import de.sp.trashNothing_backend.dtos.response.GetAllSoldProductsResponseDto;
import de.sp.trashNothing_backend.dtos.response.ProduktVerkauftResponseDto;
import de.sp.trashNothing_backend.entities.Benutzer;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.repositories.BenutzerRepository;
import de.sp.trashNothing_backend.repositories.ProduktRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BenutzerService {
    BenutzerRepository benutzerRepository;
    ProduktRepository produktRepository;

    public BenutzerService(BenutzerRepository benutzerRepository, ProduktRepository produktRepository) {
        this.benutzerRepository = benutzerRepository;
        this.produktRepository = produktRepository;
    }

    public GetAllSoldProductsResponseDto GetListeverkaufterProdukte(GetAllSoldProductsRequestDto dto){
        Optional<Benutzer> benutzer = benutzerRepository.findById(dto.benutzerId());
        if(benutzer.isEmpty()) throw new IllegalArgumentException("Kein Benutzer mit dieser ID vorhanden");
        else {
            return new GetAllSoldProductsResponseDto(benutzer.get().getVerkaufSet());
        }
    }

    public ProduktVerkauftResponseDto ProduktVerkauft(ProduktVerkauftRequestDto dto){
        Benutzer benutzer = benutzerRepository.findById(dto.benutzerId()).orElseThrow(()-> new RuntimeException("Kein benutzer mit dieser ID vorhanden"));
        Produkt verkauftesProdukt = produktRepository.findById(dto.produktId()).orElseThrow(()-> new RuntimeException("Kein PRodukt mit dieser Id gefunden"));
        benutzer.getVerkaufSet().add(verkauftesProdukt);
        return new ProduktVerkauftResponseDto(dto.benutzerId(), dto.produktId());
    }
}
