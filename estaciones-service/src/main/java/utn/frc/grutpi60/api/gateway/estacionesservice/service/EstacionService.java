package utn.frc.grutpi60.api.gateway.estacionesservice.service;

import utn.frc.grutpi60.api.gateway.estacionesservice.model.Estacion;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface EstacionService {
    List<Estacion> findAll();

    Optional<Estacion> findById(Integer id);

    Estacion create(String nombre, Double latitud, Double longitud);

    public Estacion encontrarEstacionMasCercana(Double latitud, Double longitud);

    Object calcularDistanciaEntreEstaciones(Integer idEstacionOrigen, Integer idEstacionDestino);
}
