package de.sp.trashNothing_backend.services;

import de.sp.trashNothing_backend.dtos.response.GetAllSoldProductsResponseDto;
import de.sp.trashNothing_backend.entities.Benutzer;
import de.sp.trashNothing_backend.repositories.BenutzerRepository;

import java.util.Optional;

public class BenutzerService {
    BenutzerRepository benutzerRepository;

    public BenutzerService(BenutzerRepository benutzerRepository) {
        this.benutzerRepository = benutzerRepository;
    }

    public GetAllSoldProductsResponseDto GetListeverkaufterProdukte(long benutzerId){
        Optional<Benutzer> benutzer = benutzerRepository.findById(benutzerId);
        if(benutzer.isEmpty()) throw new IllegalArgumentException("Kein Benutzer mit dieser ID");
        else {
            return new GetAllSoldProductsResponseDto(benutzer.get().getVerkaufSet());
    }
}}
