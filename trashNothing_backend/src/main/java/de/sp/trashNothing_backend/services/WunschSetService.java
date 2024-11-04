package de.sp.trashNothing_backend.services;

import de.sp.trashNothing_backend.dtos.request.AddProductToWishlistRequestDTO;
import de.sp.trashNothing_backend.entities.Benutzer;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.entities.WunschSet_Produkt;
import de.sp.trashNothing_backend.entities.WunschSet;
import de.sp.trashNothing_backend.repositories.BenutzerRepository;
import de.sp.trashNothing_backend.repositories.ProduktRepository;
import de.sp.trashNothing_backend.repositories.WunschSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class WunschSetService {
    @Autowired
    WunschSetRepository wunschSetRepository;
    @Autowired
    BenutzerRepository benutzerRepository;
    @Autowired
    ProduktRepository produktRepository;

    public WunschSet createWunschSet(AddProductToWishlistRequestDTO request) {

        Benutzer benutzer = benutzerRepository.findById(request.benutzerId())
                .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden"));
        Produkt produkt = produktRepository.findById(request.produktId())
                .orElseThrow(() -> new IllegalArgumentException("Produkt nicht gefunden"));

        WunschSet wunschSet = new WunschSet();
        wunschSet.setBenutzer(benutzer);
        wunschSet.setWunschSetProdukte(Set.of(new WunschSet_Produkt(wunschSet, produkt)));

        return wunschSetRepository.save(wunschSet);
    }
    public List<WunschSet> getWunschSetsByBenutzerId(Long benutzerId) {
        return wunschSetRepository.findByBenutzerId(benutzerId);
    }
    public List<WunschSet> getAllWunschSet(){
        return  wunschSetRepository.findAll();
    }

    public void deleteWunschSet(Long id){
        wunschSetRepository.deleteById(id);
    }
}
