package utn.frc.grutpi60.servicealquileres.application.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import utn.frc.grutpi60.servicealquileres.model.Alquiler;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AlquilerResponse {
    Long id;
    int estado;
    int estacion_retiro;
    int estacion_devolucion;
    String fecha_hora_retiro;
    String fecha_hora_devolucion;
    double monto;

    public static AlquilerResponse from(Alquiler aAlquiler) {
        return AlquilerResponse.builder()
                .id(Long.valueOf(aAlquiler.getId()))
                .estado(aAlquiler.getEstado())
                .estacion_retiro(aAlquiler.getEstacionRetiro())
                .estacion_devolucion(getEstacionDevolucionOrDefault(aAlquiler))
                .fecha_hora_retiro(aAlquiler.getFechaHoraRetiro().toString())
                .fecha_hora_devolucion(getFechaHoraDevolucionOrDefault(aAlquiler))
                .monto(aAlquiler.getMonto() != null ? aAlquiler.getMonto().doubleValue() : 0.0)
                .build();
    }

    private static int getEstacionDevolucionOrDefault(Alquiler aAlquiler) {
        Integer estacionDevolucion = aAlquiler.getEstacionDevolucion();
        return estacionDevolucion != null ? estacionDevolucion : 0;
    }

    private static String getFechaHoraDevolucionOrDefault(Alquiler aAlquiler) {
        LocalDateTime fechaHoraDevolucion = aAlquiler.getFechaHoraDevolucion();
        return fechaHoraDevolucion != null ? fechaHoraDevolucion.toString() : "N/A";
    }

}
