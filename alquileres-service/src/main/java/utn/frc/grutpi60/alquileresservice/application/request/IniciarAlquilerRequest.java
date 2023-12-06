    package utn.frc.grutpi60.alquileresservice.application.request;

    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;
    import lombok.Data;
    import lombok.experimental.FieldDefaults;


    @Data
    @FieldDefaults(level = lombok.AccessLevel.PRIVATE)
    public class IniciarAlquilerRequest {
        @NotNull(message = "Id cliente es requerido")
        @NotBlank(message = "Id cliente es requerido")
            String idCliente;

        @NotNull(message = "Estacion de retiro es requerida")
        Integer estacionRetiro;
    }
