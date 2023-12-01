package utn.frc.grutpi60.api.gateway.estacionesservice.application.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import utn.frc.grutpi60.api.gateway.estacionesservice.model.Estacion;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EstacionResponse {
    Integer id;
    String nombre;
    Double latitud;
    Double longitud;

    public static EstacionResponse from(Estacion aEstacion) {
        return EstacionResponse.builder()
                .id(aEstacion.getId())
                .nombre(aEstacion.getNombre().getNombre())
                .latitud(aEstacion.getCoordenada().getLatitud())
                .longitud(aEstacion.getCoordenada().getLongitud())
                .build();
    }
}
