package de.sp.trashNothing_backend.repositories;


import de.sp.trashNothing_backend.entities.Produkt;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProduktRepository extends JpaRepository<Produkt, Long> {

}
