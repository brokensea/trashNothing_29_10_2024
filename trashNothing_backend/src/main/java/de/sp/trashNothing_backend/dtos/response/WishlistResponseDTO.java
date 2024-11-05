package de.sp.trashNothing_backend.dtos.response;

import java.math.BigDecimal;

public record WishlistResponseDTO(Long wunschSetId, Long benutzerId, Long produktId, String titel, String beschreibung, BigDecimal preis, boolean lieferung){
}
