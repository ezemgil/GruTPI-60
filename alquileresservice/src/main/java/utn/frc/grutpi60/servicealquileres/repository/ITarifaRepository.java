package utn.frc.grutpi60.servicealquileres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frc.grutpi60.servicealquileres.model.Tarifa;

import java.util.Optional;

@Repository
public interface ITarifaRepository extends JpaRepository<Tarifa, Long> {

    Optional<Tarifa> findByTipo(int tipo);

    Optional<Tarifa> findByDefinicion(String definicion);

    Optional<Tarifa> findByDiaSemana(int diaSemana);

    Optional<Tarifa> findByDiaMes(int diaMes);

    Optional<Tarifa> findByAnio(int anio);
}

