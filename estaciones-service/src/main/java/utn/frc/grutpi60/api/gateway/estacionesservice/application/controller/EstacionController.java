package utn.frc.grutpi60.api.gateway.estacionesservice.application.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.grutpi60.api.gateway.estacionesservice.application.request.CrearEstacionRequest;
import utn.frc.grutpi60.api.gateway.estacionesservice.application.response.EstacionResponse;
import utn.frc.grutpi60.api.gateway.estacionesservice.service.EstacionService;
import lombok.val;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estaciones")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class EstacionController {
    EstacionService estacionService;

    @GetMapping
    public ResponseEntity<List<EstacionResponse>> getAll() {
        try {
            val estaciones = estacionService.findAll()
                    .stream()
                    .map(EstacionResponse::from)
                    .toList();
            return ResponseEntity.ok(estaciones);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<EstacionResponse> create(@RequestBody CrearEstacionRequest aRequest) {
        try {
            val estacion = estacionService.create(aRequest.getNombre(), aRequest.getLatitud(), aRequest.getLongitud());
            return ResponseEntity.ok(EstacionResponse.from(estacion));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstacionResponse> getById(@PathVariable Integer id) {
        try {
            val estacion = estacionService.findById(id);
            if (estacion.isPresent()) {
                return ResponseEntity.ok(EstacionResponse.from(estacion.get()));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/cercana")
    public ResponseEntity<EstacionResponse> getEstacionesCercanas(
            @RequestParam Double latitud,
            @RequestParam Double longitud) {

        try {
            val estacionMasCercana = estacionService.encontrarEstacionMasCercana(latitud, longitud);
            if (estacionMasCercana != null) {
                EstacionResponse estacionResponse = EstacionResponse.from(estacionMasCercana);
                return ResponseEntity.ok(estacionResponse);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/distancia-entre-estaciones")
    public ResponseEntity<Double> getDistanciaEntreEstaciones(
            @RequestParam Integer idEstacionOrigen,
            @RequestParam Integer idEstacionDestino) {

        try {
            Double distancia = (Double) estacionService
                    .calcularDistanciaEntreEstaciones(idEstacionOrigen, idEstacionDestino) * 110000;
            return ResponseEntity.ok(distancia);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
