package utn.frc.grutpi60.api.gateway.estacionesservice.application.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.grutpi60.api.gateway.estacionesservice.application.request.CrearEstacionRequest;
import utn.frc.grutpi60.api.gateway.estacionesservice.application.response.EstacionResponse;
import utn.frc.grutpi60.api.gateway.estacionesservice.service.EstacionService;
import utn.frc.grutpi60.api.gateway.estacionesservice.application.ResponseHandler;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estaciones")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class EstacionController {
    EstacionService estacionService;

    // ROL: CLIENTE/ADMINISTRADOR
    @GetMapping
    public ResponseEntity<Object> getAll() {
        try {
            val estaciones = estacionService.findAll()
                    .stream()
                    .map(EstacionResponse::from)
                    .toList();
            return ResponseHandler.success(estaciones);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }


    // ROL: ADMINISTRADOR
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CrearEstacionRequest aRequest) {
        try {
            val estacion = estacionService.create(aRequest.getNombre(), aRequest.getLatitud(), aRequest.getLongitud());
            return ResponseHandler.success(EstacionResponse.from(estacion));
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    // ROL: CLIENTE/ADMINISTRADOR
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        try {
            return estacionService.findById(id)
                    .map(aCustomer -> ResponseHandler.success(EstacionResponse.from(aCustomer)))
                    .orElseGet(ResponseHandler::notFound);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    // ROL: CLIENTE/ADMINISTRADOR
    @GetMapping("/cercana")
    public ResponseEntity<Object> getEstacionesCercanas(
            @RequestParam Double latitud,
            @RequestParam Double longitud) {

        try {
            val estacionMasCercana = estacionService.encontrarEstacionMasCercana(latitud, longitud);
            if (estacionMasCercana != null) {
                EstacionResponse estacionResponse = EstacionResponse.from(estacionMasCercana);
                return ResponseHandler.success(estacionResponse);
            } else {
                return ResponseHandler.notFound();
            }
        } catch (Exception e) {
            System.err.println("Error while getting estaciones cercanas: " + e.getMessage());
            e.printStackTrace(System.err);
            return ResponseHandler.internalError();
        }
    }

    @GetMapping("/distancia-entre-estaciones")
    public ResponseEntity<Object> getDistanciaEntreEstaciones(
            @RequestParam Integer idEstacionOrigen,
            @RequestParam Integer idEstacionDestino) {

        try {
            val distancia = (Double) estacionService
                    .calcularDistanciaEntreEstaciones(idEstacionOrigen, idEstacionDestino) * 110000;
            return ResponseHandler.success(distancia);
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}
