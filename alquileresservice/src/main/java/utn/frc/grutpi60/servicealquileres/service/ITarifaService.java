package utn.frc.grutpi60.servicealquileres.service;

import utn.frc.grutpi60.servicealquileres.model.Tarifa;

import java.util.Optional;

public interface ITarifaService {
    Optional<Tarifa> findByTipo(int tipo);
    Optional<Tarifa> findByDefinicion(String definicion);
    Optional<Tarifa> findByDiaSemana(int diaSemana);
    Optional<Tarifa> findByDiaMes(int diaMes);
    Optional<Tarifa> findByAnio(int anio);

}
