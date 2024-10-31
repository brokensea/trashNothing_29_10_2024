package de.sp.trashNothing_backend.controllers;


import de.sp.trashNothing_backend.dtos.request.ProduktEinkaufenRequestDTO;
import de.sp.trashNothing_backend.dtos.response.ProduktEinkaufenResponseDTO;
import de.sp.trashNothing_backend.services.ProduktEinkaufenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produkte")
public class ProduktEinkaufenController {

    private final ProduktEinkaufenService produktEinkaufenService;

    @Autowired
    public ProduktEinkaufenController(ProduktEinkaufenService produktEinkaufenService) {
        this.produktEinkaufenService = produktEinkaufenService;
    }

    @PostMapping("addToShoppinglist")
    public ResponseEntity<ProduktEinkaufenResponseDTO> addProduktToGekauftList(
            @RequestBody ProduktEinkaufenRequestDTO requestDTO) {
        ProduktEinkaufenResponseDTO responseDTO = produktEinkaufenService.kaufenProdukt(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProduktEinkaufenResponseDTO>> getAllProdukte() {
        List<ProduktEinkaufenResponseDTO> responseDTOs = produktEinkaufenService.getAllProdukte();
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }
    
    @GetMapping("/gekauft/{benutzerId}")
    public ResponseEntity<List<ProduktEinkaufenResponseDTO>> getGekaufteProdukteByBenutzerId(
            @PathVariable Long benutzerId) {
        List<ProduktEinkaufenResponseDTO> responseDTOs = produktEinkaufenService.getGekaufteProdukteByBenutzerId(benutzerId);
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }
}