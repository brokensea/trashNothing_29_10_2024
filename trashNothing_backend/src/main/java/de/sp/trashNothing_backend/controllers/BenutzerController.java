package de.sp.trashNothing_backend.controllers;

import de.sp.trashNothing_backend.dtos.response.GetListeVerkaufterProdukteResponseDTO;
import de.sp.trashNothing_backend.services.BenutzerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/benutzer")
public class BenutzerController {
    BenutzerService benutzerService;

    public BenutzerController(BenutzerService benutzerService) {
        this.benutzerService = benutzerService;
    }

    @GetMapping("/soldItems")
    public GetListeVerkaufterProdukteResponseDTO GetListeVerkaufterProdukte(){
        return null;
    }
}
