package de.sp.trashNothing_backend.controllers;

import de.sp.trashNothing_backend.dtos.request.GetAllSoldProductsRequestDto;
import de.sp.trashNothing_backend.dtos.response.GetAllSoldProductsResponseDto;
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

    public GetAllSoldProductsResponseDto GetListeVerkaufterProdukte(GetAllSoldProductsRequestDto dto){
        return benutzerService.GetListeverkaufterProdukte(dto.benutzerId());

    }
}
