package de.sp.trashNothing_backend.controllers;

import de.sp.trashNothing_backend.dtos.request.GetAllSoldProductsRequestDto;
import de.sp.trashNothing_backend.dtos.response.GetAllSoldProductsResponseDto;
import de.sp.trashNothing_backend.services.BenutzerService;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<GetAllSoldProductsResponseDto> GetListeVerkaufterProdukte(@Valid @RequestBody GetAllSoldProductsRequestDto dto) {
        return ResponseEntity.ok(benutzerService.GetListeverkaufterProdukte(dto.benutzerId()));
    }
    
}
