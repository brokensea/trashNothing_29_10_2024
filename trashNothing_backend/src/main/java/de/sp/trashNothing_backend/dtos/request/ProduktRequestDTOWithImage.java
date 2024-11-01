package de.sp.trashNothing_backend.dtos.request;

import de.sp.trashNothing_backend.entities.enumClass.Kategorie;
import de.sp.trashNothing_backend.entities.enumClass.Zustand;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record ProduktRequestDTOWithImage(
        String titel,
        String beschreibung,
        int anzahl,
        BigDecimal preis,
        Zustand zustand,
        String marke,
        boolean lieferung,
        Kategorie kategorie,
        Long benutzerId,
        MultipartFile imgFile  // New field for image) {
) {
}