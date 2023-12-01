package utn.frc.grutpi60.api.gateway.estacionesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frc.grutpi60.api.gateway.estacionesservice.model.Estacion;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Long> {
}
