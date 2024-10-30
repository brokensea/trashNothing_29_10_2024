package de.sp.trashNothing_backend.controllers;

import de.sp.trashNothing_backend.dtos.request.ProduktRequestDTO;
import de.sp.trashNothing_backend.dtos.response.ProduktResponseDTO;
import de.sp.trashNothing_backend.dtos.response.WishlistResponseDTO;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.mapper.ProduktMapper;
import de.sp.trashNothing_backend.mapper.WunschSetMapper;
import de.sp.trashNothing_backend.services.ProduktService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProduktController {
    @Autowired
    ProduktService produktService;

    @PostMapping
    public ResponseEntity<ProduktResponseDTO> createProdukt (@Valid @RequestBody ProduktRequestDTO request){
        Produkt createdProdukt = produktService.createProdukt(request);
        ProduktResponseDTO response = ProduktMapper.toProduktResponse(createdProdukt);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
