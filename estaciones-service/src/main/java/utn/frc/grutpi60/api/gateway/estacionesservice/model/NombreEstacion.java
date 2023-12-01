package utn.frc.grutpi60.api.gateway.estacionesservice.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@AttributeOverrides({
        @AttributeOverride(
                name = "nombre",
                column = @Column(name = "NOMBRE")
        )
})
public class NombreEstacion {

    String nombre;
}