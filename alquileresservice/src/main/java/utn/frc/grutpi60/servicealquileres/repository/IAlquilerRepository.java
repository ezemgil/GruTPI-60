package utn.frc.grutpi60.servicealquileres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frc.grutpi60.servicealquileres.model.Alquiler;
@Repository
public interface IAlquilerRepository extends JpaRepository<Alquiler, Long> {

    // get alquiler by estado
}
