package de.sp.trashNothing_backend.repositories;

import de.sp.trashNothing_backend.entities.Benutzer;
import de.sp.trashNothing_backend.entities.Produkt;
import de.sp.trashNothing_backend.entities.WunschSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WunschSetRepository extends JpaRepository<WunschSet, Long> {
    @Query("SELECT ws FROM WunschSet ws JOIN ws.wunschSetProdukte wsp WHERE ws.benutzer = :benutzer AND wsp.produkt = :produkt")
    Optional<WunschSet> findByUserAndProduct(@Param("benutzer") Benutzer benutzer, @Param("produkt") Produkt produkt);
}
