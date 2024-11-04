package de.sp.trashNothing_backend.controllers;

import de.sp.trashNothing_backend.dtos.request.GetAllSoldProductsRequestDto;
import de.sp.trashNothing_backend.dtos.request.ProduktVerkauftRequestDto;
import de.sp.trashNothing_backend.dtos.response.GetAllSoldProductsResponseDto;
import de.sp.trashNothing_backend.dtos.response.ProduktVerkauftResponseDto;
import de.sp.trashNothing_backend.services.BenutzerService;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/benutzer")
public class BenutzerController {
    BenutzerService benutzerService;

    public BenutzerController(BenutzerService benutzerService) {
        this.benutzerService = benutzerService;
    }

    @GetMapping("/soldItems")
    public ResponseEntity<GetAllSoldProductsResponseDto> GetListeVerkaufterProdukte(@Valid @RequestBody GetAllSoldProductsRequestDto dto){
        return ResponseEntity.ok(benutzerService.GetListeverkaufterProdukte(dto));

    }
    @PostMapping("/sellItem")
    public ResponseEntity<ProduktVerkauftResponseDto> ProduktVerkaufen(@Valid @RequestBody ProduktVerkauftRequestDto dto){
        return ResponseEntity.ok(benutzerService.ProduktVerkauft(dto));
    }
}
