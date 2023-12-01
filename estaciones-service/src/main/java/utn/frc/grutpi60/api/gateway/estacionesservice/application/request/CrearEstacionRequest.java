package utn.frc.grutpi60.api.gateway.estacionesservice.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CrearEstacionRequest {
    @NotBlank(message = "Nombre es requerido")
    @NotNull(message = "Nombre es requerido")
    String nombre;
    @NotBlank(message = "Latitud es requerida")
    @NotNull(message = "Latitud es requerida")
    Double latitud;
    @NotBlank(message = "Longitud es requerida")
    @NotNull(message = "Longitud es requerida")
    Double longitud;
}
