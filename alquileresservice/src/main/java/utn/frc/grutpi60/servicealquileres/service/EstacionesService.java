package utn.frc.grutpi60.servicealquileres.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class EstacionesService implements IEstacionesService {

    private final String URL;
    private final RestTemplate restTemplate;

    public EstacionesService(RestTemplateBuilder restTemplateBuilder, @Value("${estaciones-service.url}") String estacionesServiceUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.URL = estacionesServiceUrl;
    }

    @Override
    public Double calcularDistanciaEntreEstaciones(Integer estacionOrigen, Integer estacionDestino) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            String url = "http://localhost:8081/estaciones/distancia-entre-estaciones?idEstacionOrigen={estacionOrigen}&idEstacionDestino={estacionDestino}";

            Map<String, Integer> params = new HashMap<>();
            params.put("estacionOrigen", estacionOrigen);
            params.put("estacionDestino", estacionDestino);

            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class, params);

            if (response.getStatusCode().is2xxSuccessful()) {
                Double distancia = (Double) response.getBody().get("data");
                return distancia;
            } else {
                throw new RuntimeException("Error al calcular la distancia entre estaciones");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al calcular la distancia entre estaciones", e);
        }
    }

}
