package de.sp.trashNothing_backend.services;

import de.sp.trashNothing_backend.dtos.request.GetAllSoldProductsRequestDto;
import de.sp.trashNothing_backend.dtos.request.ProduktRequestDTOWithImage;
import de.sp.trashNothing_backend.dtos.request.ProduktVerkauftRequestDto;
import de.sp.trashNothing_backend.dtos.response.GetAllSoldProductsResponseDto;
import de.sp.trashNothing_backend.dtos.response.ProduktEinkaufenResponseDTO;
import de.sp.trashNothing_backend.dtos.response.ProduktVerkauftResponseDto;
import de.sp.trashNothing_backend.entities.Benutzer;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.repositories.BenutzerRepository;
import de.sp.trashNothing_backend.repositories.ProduktRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BenutzerService {
    BenutzerRepository benutzerRepository;
    ProduktRepository produktRepository;

    public BenutzerService(BenutzerRepository benutzerRepository, ProduktRepository produktRepository) {
        this.benutzerRepository = benutzerRepository;
        this.produktRepository = produktRepository;
    }

    public List<ProduktEinkaufenResponseDTO> getListeverkaufterProdukte(Long benutzerId) {
        Optional<Benutzer> benutzer = benutzerRepository.findById(benutzerId);
        if (benutzer.isEmpty()) {
            throw new IllegalArgumentException("Kein Benutzer mit dieser ID vorhanden");
        } else {
            return benutzer.get().getVerkaufSet().stream()
                    .map(produkt -> new ProduktEinkaufenResponseDTO(
                            produkt.getId(),
                            produkt.getTitel(),
                            produkt.getBeschreibung(),
                            produkt.getAnzahl(),
                            produkt.getPreis(),
                            produkt.getZustand(),
                            produkt.getMarke(),
                            produkt.isLieferung(),
                            produkt.getImgUrl(),
                            produkt.getDeleteUrl(),
                            produkt.getKategorie(),
                            benutzerId
                    ))
                    .collect(Collectors.toList());
        }
    }


    public ProduktVerkauftResponseDto produktVerkauft(ProduktVerkauftRequestDto dto) {
        Benutzer benutzer = benutzerRepository.findById(dto.benutzerId()).orElseThrow(() -> new RuntimeException("Kein benutzer mit dieser ID vorhanden"));
        Produkt verkauftesProdukt = produktRepository.findById(dto.produktId()).orElseThrow(() -> new RuntimeException("Kein PRodukt mit dieser Id gefunden"));
        benutzer.getVerkaufSet().add(verkauftesProdukt);
        return new ProduktVerkauftResponseDto(dto.benutzerId(), dto.produktId());
    }
}
