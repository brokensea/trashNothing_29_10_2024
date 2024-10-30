package de.sp.trashNothing_backend.mapper;

import de.sp.trashNothing_backend.dtos.request.ProduktEinkaufenRequestDTO;
import de.sp.trashNothing_backend.dtos.response.ProduktEinkaufenResponseDTO;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.entities.Benutzer;

public class ProduktMapper {
    public static Produkt toProdukt(ProduktEinkaufenRequestDTO requestDTO, Benutzer benutzer) {
        Produkt produkt = new Produkt();
        produkt.setTitel(requestDTO.titel());
        produkt.setBeschreibung(requestDTO.beschreibung());
        produkt.setAnzahl(requestDTO.anzahl());
        produkt.setPreis(requestDTO.preis());
        produkt.setZustand(requestDTO.zustand());
        produkt.setMarke(requestDTO.marke());
        produkt.setLieferung(requestDTO.lieferung());
        produkt.setKategorie(requestDTO.kategorie());
        produkt.setBenutzer(benutzer); // verbinden mit Benutzer Entity
        return produkt;
    }

    //  Produkt to ProduktEinkaufenResponseDTO
    public static ProduktEinkaufenResponseDTO toProduktEinkaufenResponseDTO(Produkt produkt) {
        return new ProduktEinkaufenResponseDTO(
                produkt.getId(),
                produkt.getTitel(),
                produkt.getBeschreibung(),
                produkt.getAnzahl(),
                produkt.getPreis(),
                produkt.getZustand(),
                produkt.getMarke(),
                produkt.isLieferung(),
                produkt.getImgUrl(),
                produkt.getDeleteUrl(),
                produkt.getKategorie(),
                produkt.getBenutzer() != null ? produkt.getBenutzer().getId() : null
                // back to Benutzer ID
        );
    }

    // update Produkt for update funktion
    public static void updateProduktFromDTO(ProduktEinkaufenRequestDTO requestDTO, Produkt produkt) {
        produkt.setTitel(requestDTO.titel());
        produkt.setBeschreibung(requestDTO.beschreibung());
        produkt.setAnzahl(requestDTO.anzahl());
        produkt.setPreis(requestDTO.preis());
        produkt.setZustand(requestDTO.zustand());
        produkt.setMarke(requestDTO.marke());
        produkt.setLieferung(requestDTO.lieferung());
        produkt.setKategorie(requestDTO.kategorie());
    }
}
