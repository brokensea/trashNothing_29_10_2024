package de.sp.trashNothing_backend.controllers;

import de.sp.trashNothing_backend.dtos.request.AddProductToWishlistRequestDTO;
import de.sp.trashNothing_backend.dtos.response.WishlistResponseDTO;
import de.sp.trashNothing_backend.entities.WunschSet;
import de.sp.trashNothing_backend.repositories.mapper.WunschSetMapper;
import de.sp.trashNothing_backend.services.WunschSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product/AddToWishlist")
public class WunschSetController {
    @Autowired
    WunschSetService wunschSetService;

    @PostMapping
    public ResponseEntity<WishlistResponseDTO> addToWunschSet(@RequestBody AddProductToWishlistRequestDTO request) {
        WunschSet createdWunschSet = wunschSetService.createWunschSet(request);
        WishlistResponseDTO response = WunschSetMapper.toWishlistResponse(createdWunschSet);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteWunschSet(@PathVariable Long id) {
        try {
           wunschSetService.deleteWunschSet(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
