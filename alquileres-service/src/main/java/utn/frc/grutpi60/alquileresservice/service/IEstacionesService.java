package utn.frc.grutpi60.alquileresservice.service;



import utn.frc.grutpi60.alquileresservice.model.Estacion;

import java.util.Optional;

public interface IEstacionesService {
    Double calcularDistanciaEntreEstaciones(Integer estacionOrigen, Integer estacionDestino);
    Optional<Estacion> findById(Integer id);
}
