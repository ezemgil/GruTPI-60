package utn.frc.grutpi60.alquileresservice.application.controller;


import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.grutpi60.alquileresservice.application.ExchangeService;
import utn.frc.grutpi60.alquileresservice.application.request.FinalizarAlquilerRequest;
import utn.frc.grutpi60.alquileresservice.application.request.IniciarAlquilerRequest;
import utn.frc.grutpi60.alquileresservice.application.response.AlquilerResponse;
import utn.frc.grutpi60.alquileresservice.model.Alquiler;
import utn.frc.grutpi60.alquileresservice.service.IAlquilerService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/alquileres")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AlquilerController {
    IAlquilerService alquilerService;
    ExchangeService exchangeService;

    @GetMapping("/finalizados")
    public ResponseEntity<List<AlquilerResponse>> getAlquileresFinalizados() {
        try {
            List<AlquilerResponse> alquileres = alquilerService.findAll().stream()
                    .filter(Alquiler::isFinalizado)
                    .map(AlquilerResponse::from)
                    .toList();
            return ResponseEntity.ok(alquileres);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/iniciar")
    public ResponseEntity<AlquilerResponse> iniciarAlquiler(@RequestBody IniciarAlquilerRequest aRequest) {
        try {
            val alquiler = alquilerService.iniciarAlquiler(aRequest.getIdCliente(), aRequest.getEstacionRetiro());
            return ResponseEntity.ok(AlquilerResponse.from(alquiler));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/finalizar")
    public ResponseEntity<AlquilerResponse> finalizarAlquiler(@RequestBody FinalizarAlquilerRequest aRequest) {
        try {
            Alquiler alquiler = alquilerService.finalizarAlquiler(aRequest.getId(), aRequest.getIdEstacionDevolucion());
            AlquilerResponse response = AlquilerResponse.from(alquiler);
            if (aRequest.getMoneda() != null && !aRequest.getMoneda().isEmpty()) {
                Double montoConvertido = exchangeService.convertirMontoAMoneda(response.getMonto(), aRequest.getMoneda());
                response.setMonto(montoConvertido);
            }
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
