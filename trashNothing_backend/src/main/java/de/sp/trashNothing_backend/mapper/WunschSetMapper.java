package de.sp.trashNothing_backend.mapper;
import de.sp.trashNothing_backend.entities.WunschSet_Produkt;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.dtos.response.WishlistResponseDTO;
import de.sp.trashNothing_backend.entities.WunschSet;
import org.springframework.stereotype.Component;

@Component
public class WunschSetMapper {
    public static WishlistResponseDTO toWishlistResponse(WunschSet wunschSet) {

        Long produktId = wunschSet.getWunschSetProdukte().stream()
                .findFirst()
                .map(WunschSet_Produkt::getProdukt)
                .map(Produkt::getId)
                .orElse(null);

        return new WishlistResponseDTO(
                wunschSet.getId(),
                wunschSet.getBenutzer().getId(),
                produktId
        );
    }
}
