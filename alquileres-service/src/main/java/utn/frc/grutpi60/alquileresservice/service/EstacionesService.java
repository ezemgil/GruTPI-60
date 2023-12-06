package utn.frc.grutpi60.alquileresservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import utn.frc.grutpi60.alquileresservice.model.Estacion;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class EstacionesService implements IEstacionesService {

    private final String URL;
    private final RestTemplate restTemplate;

    public EstacionesService(RestTemplateBuilder restTemplateBuilder, @Value("${estaciones-service.url}") String estacionesServiceUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.URL = estacionesServiceUrl;
    }

    @Override
    public Double calcularDistanciaEntreEstaciones(Integer idEstacionOrigen, Integer idEstacionDestino) {
        try {
            String url = "http://localhost:8081/estaciones/distancia-entre-estaciones?idEstacionOrigen={estacionOrigen}&idEstacionDestino={estacionDestino}";

            Map<String, Integer> params = new HashMap<>();
            params.put("estacionOrigen", idEstacionOrigen);
            params.put("estacionDestino", idEstacionDestino);

            ResponseEntity<Double> response = restTemplate.getForEntity(url, Double.class, params);

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new RuntimeException("Error al calcular la distancia entre estaciones");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al calcular la distancia entre estaciones", e);
        }
    }

    @Override
    public Optional<Estacion> findById(Integer id) {
        try {
            String apiUrl = URL + "/{id}";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Estacion> response = restTemplate.getForEntity(apiUrl, Estacion.class, id);

            if (response.getStatusCode().is2xxSuccessful()) {
                return Optional.ofNullable(response.getBody());
            } else {
                throw new RuntimeException("Error al buscar la estación");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar la estación", e);
        }
    }
}
