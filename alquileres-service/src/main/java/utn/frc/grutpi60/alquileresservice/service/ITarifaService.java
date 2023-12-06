package utn.frc.grutpi60.alquileresservice.service;



import utn.frc.grutpi60.alquileresservice.model.Tarifa;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ITarifaService {
    Optional<Tarifa> findByDiaSemana(int diaSemana);

    Optional<Tarifa> getTarifa(LocalDateTime fecha);
}
