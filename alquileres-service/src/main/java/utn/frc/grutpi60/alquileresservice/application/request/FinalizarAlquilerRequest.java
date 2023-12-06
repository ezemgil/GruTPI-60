package utn.frc.grutpi60.alquileresservice.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class FinalizarAlquilerRequest {
    @NotBlank(message = "Id es requerido")
    @NotNull(message = "Id es requerido")
    Integer id;

    @NotBlank(message = "Estacion de devolucion es requerida")
    @NotNull(message = "Estacion de devolucion es requerida")
    Integer idEstacionDevolucion;

    String moneda;
}
