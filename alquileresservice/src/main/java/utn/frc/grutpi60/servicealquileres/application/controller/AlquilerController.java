package utn.frc.grutpi60.servicealquileres.application.controller;


import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.grutpi60.servicealquileres.application.ExchangeService;
import utn.frc.grutpi60.servicealquileres.application.ResponseHandler;
import utn.frc.grutpi60.servicealquileres.application.request.FinalizarAlquilerRequest;
import utn.frc.grutpi60.servicealquileres.application.request.IniciarAlquilerRequest;
import utn.frc.grutpi60.servicealquileres.application.response.AlquilerResponse;
import utn.frc.grutpi60.servicealquileres.model.Alquiler;
import utn.frc.grutpi60.servicealquileres.service.IAlquilerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alquileres")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AlquilerController {
    IAlquilerService alquilerService;
    ExchangeService exchangeService;

    @GetMapping("/finalizados")
    public ResponseEntity<Object> getAlquileresFinalizados() {
        try {
            val alquileres = alquilerService.findAll().stream()
                    .filter(Alquiler::isFinalizado)
                    .map(AlquilerResponse::from)
                    .toList();
            return ResponseHandler.success(alquileres);
        }
        catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping("/iniciar")
    public ResponseEntity<Object> iniciarAlquiler(@RequestBody IniciarAlquilerRequest aRequest) {
        try {
            val alquiler = alquilerService.iniciarAlquiler(aRequest.getIdCliente(), aRequest.getEstacionRetiro());
            return ResponseHandler.success(AlquilerResponse.from(alquiler));
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PatchMapping("/finalizar")
    public ResponseEntity<Object> finalizarAlquiler(@RequestBody FinalizarAlquilerRequest aRequest) {
        try {
            Alquiler alquiler = alquilerService.finalizarAlquiler(aRequest.getId(), aRequest.getIdEstacionDevolucion());
            AlquilerResponse response = AlquilerResponse.from(alquiler);
            if (aRequest.getMoneda() != null) {
                Double montoConvertido = exchangeService.convertirMontoAMoneda(response.getMonto(), aRequest.getMoneda());
                response.setMonto(montoConvertido);
            }
            return ResponseHandler.success(response);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            System.err.println("Error interno al finalizar el alquiler: " + e.getMessage());
            e.printStackTrace();
            return ResponseHandler.internalError();
        }
    }
}
