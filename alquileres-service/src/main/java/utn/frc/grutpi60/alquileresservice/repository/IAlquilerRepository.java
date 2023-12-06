package utn.frc.grutpi60.alquileresservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frc.grutpi60.alquileresservice.model.Alquiler;

@Repository
public interface IAlquilerRepository extends JpaRepository<Alquiler, Long> {
}
