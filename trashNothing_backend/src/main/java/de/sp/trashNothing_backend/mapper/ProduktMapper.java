package de.sp.trashNothing_backend.mapper;

import de.sp.trashNothing_backend.dtos.request.ProduktEinkaufenRequestDTO;
import de.sp.trashNothing_backend.dtos.request.ProduktRequestDTO;
import de.sp.trashNothing_backend.dtos.response.ProduktEinkaufenResponseDTO;
import de.sp.trashNothing_backend.dtos.response.ProduktResponseDTO;
import de.sp.trashNothing_backend.entities.Benutzer;
import de.sp.trashNothing_backend.entities.Produkt;
import org.springframework.stereotype.Component;

@Component
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
        produkt.setBenutzer(benutzer);
        return produkt;
    }


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
                produkt.getBenutzer() != null ? produkt.getBenutzer().getId() : null // 返回关联的 Benutzer ID
        );
    }

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
                produkt.getKategorie(),
                produkt.getBenutzer().getId()
        );
    }

    public static Produkt toProdukt(ProduktRequestDTO request, Benutzer benutzer) {
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
                request.kategorie(),
                benutzer
        );
    }
}
