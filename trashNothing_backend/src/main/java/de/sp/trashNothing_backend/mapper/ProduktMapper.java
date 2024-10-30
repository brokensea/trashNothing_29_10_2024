package de.sp.trashNothing_backend.mapper;

import de.sp.trashNothing_backend.dtos.request.ProduktRequestDTO;
import de.sp.trashNothing_backend.dtos.response.ProduktResponseDTO;
import de.sp.trashNothing_backend.dtos.response.WishlistResponseDTO;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.entities.WunschSet;
import de.sp.trashNothing_backend.entities.WunschSet_Produkt;

public class ProduktMapper {
    public static ProduktResponseDTO toProduktResponse(Produkt produkt) {

        return new ProduktResponseDTO(
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
                produkt.getKategorie()
        );
    }
    public static Produkt toProdukt(ProduktRequestDTO request) {
        return new Produkt(
                request.titel(),
                request.beschreibung(),
                request.anzahl(),
                request.preis(),
                request.zustand(),
                request.marke(),
                request.lieferung(),
                request.imgUrl(),
                request.deleteUrl(),
                request.kategorie()
        );
    }
}
