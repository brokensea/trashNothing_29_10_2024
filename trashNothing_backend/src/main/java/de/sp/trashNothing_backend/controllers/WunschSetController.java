package de.sp.trashNothing_backend.controllers;

import de.sp.trashNothing_backend.dtos.request.AddProductToWishlistRequestDTO;
import de.sp.trashNothing_backend.dtos.response.WishlistResponseDTO;
import de.sp.trashNothing_backend.entities.WunschSet;
import de.sp.trashNothing_backend.mapper.WunschSetMapper;
import de.sp.trashNothing_backend.services.WunschSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product/AddToWishlist")
public class WunschSetController {
    @Autowired
    WunschSetService wunschSetService;


    @GetMapping
    public ResponseEntity<List<WishlistResponseDTO>> getAllWunschSet(){
        List<WunschSet> wunschSets = wunschSetService.getAllWunschSet();
        List<WishlistResponseDTO> response = wunschSets.stream()
                .map(WunschSetMapper::toWishlistResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{benutzerId}")
    public ResponseEntity<List<WishlistResponseDTO>> getWunschSetByUser(@PathVariable Long benutzerId) {
        List<WunschSet> wunschSets = wunschSetService.getWunschSetsByBenutzerId(benutzerId);
        List<WishlistResponseDTO> response = wunschSets.stream()
                .map(WunschSetMapper::toWishlistResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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
