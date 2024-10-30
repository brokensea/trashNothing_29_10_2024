package de.sp.trashNothing_backend.controllers;

import de.sp.trashNothing_backend.dtos.request.ProduktRequestDTO;
import de.sp.trashNothing_backend.dtos.response.ProduktResponseDTO;
import de.sp.trashNothing_backend.dtos.response.WishlistResponseDTO;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.mapper.ProduktMapper;
import de.sp.trashNothing_backend.mapper.WunschSetMapper;
import de.sp.trashNothing_backend.services.ProduktService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static de.sp.trashNothing_backend.mapper.ProduktMapper.toProduktResponse;

@RestController
@RequestMapping("/api/v1/product")
public class ProduktController {
    @Autowired
    ProduktService produktService;

    @PostMapping
    public ResponseEntity<ProduktResponseDTO> createProdukt (@Valid @RequestBody ProduktRequestDTO request){
        Produkt createdProdukt = produktService.createProdukt(request);
        ProduktResponseDTO response = toProduktResponse(createdProdukt);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduktResponseDTO> updateProdukt (@PathVariable Long id,  @Valid @RequestBody ProduktRequestDTO produktRequestDTO){
        try {
            Produkt updatedProdukt = produktService.updateProdukt(id, produktRequestDTO);
            ProduktResponseDTO responseDTO = ProduktMapper.toProduktResponse(updatedProdukt);
            return ResponseEntity.ok(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
