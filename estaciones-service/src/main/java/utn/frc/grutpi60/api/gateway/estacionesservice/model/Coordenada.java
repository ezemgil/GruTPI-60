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
                name = "latitud",
                column = @Column(name = "LATITUD")
        ),
        @AttributeOverride(
                name = "longitud",
                column = @Column(name = "LONGITUD")
        )
})
public class Coordenada {

    Double latitud;

    Double longitud;
}
