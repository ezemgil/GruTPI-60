package utn.frc.grutpi60.api.gateway.estacionesservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;
import utn.frc.grutpi60.api.gateway.estacionesservice.model.Coordenada;
import utn.frc.grutpi60.api.gateway.estacionesservice.model.Estacion;
import utn.frc.grutpi60.api.gateway.estacionesservice.model.NombreEstacion;
import utn.frc.grutpi60.api.gateway.estacionesservice.repository.EstacionRepository;
import utn.frc.grutpi60.api.gateway.estacionesservice.repository.IdentifierRepository;

import java.util.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EstacionServiceImpl implements EstacionService {
    EstacionRepository estacionRepository;
    IdentifierRepository identifierRepository;
    @Override
    public List<Estacion> findAll() {
        return estacionRepository.findAll();
    }

    @Override
    public Optional<Estacion> findById(Integer id) {
        return estacionRepository.findById(Long.valueOf(id));
    }

    @Override
    public Estacion create(String nombre, Double latitud, Double longitud) {
        val coordenadas = new Coordenada(latitud, longitud);
        val nombreEstacion = new NombreEstacion(nombre);
        val id = identifierRepository.nextValue(Estacion.TABLE_NAME);
        val estacion = new Estacion(id, nombreEstacion, coordenadas);
        return estacionRepository.save(estacion);
    }

    @Override
    public Estacion encontrarEstacionMasCercana(Double latitud, Double longitud) {
        List<Estacion> estaciones = estacionRepository.findAll();

        Estacion estacionMasCercana = null;
        double distanciaMinima = Double.MAX_VALUE;

        for (Estacion estacion : estaciones) {
            Coordenada coordenada = estacion.getCoordenada();
            if (coordenada != null && coordenada.getLatitud() != null && coordenada.getLongitud() != null) {
                double distancia = calcularDistancia(
                        latitud, longitud,
                        coordenada.getLatitud(), coordenada.getLongitud());

                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                    estacionMasCercana = estacion;
                }
            }
        }

        return estacionMasCercana;
    }

    @Override
    public Double calcularDistanciaEntreEstaciones(Integer idEstacionOrigen, Integer idEstacionDestino) {
        Estacion estacionOrigen = estacionRepository.findById(Long.valueOf(idEstacionOrigen)).get();
        Estacion estacionDestino = estacionRepository.findById(Long.valueOf(idEstacionDestino)).get();

        double distancia = calcularDistancia(
                estacionOrigen.getCoordenada().getLatitud(), estacionOrigen.getCoordenada().getLongitud(),
                estacionDestino.getCoordenada().getLatitud(), estacionDestino.getCoordenada().getLongitud());

        return distancia;
    }

    private double calcularDistancia(double latitud1, double longitud1, double latitud2, double longitud2) {
        double diferenciaLatitud = latitud2 - latitud1;
        double diferenciaLongitud = longitud2 - longitud1;
        return Math.sqrt(diferenciaLatitud * diferenciaLatitud + diferenciaLongitud * diferenciaLongitud);
    }

}
