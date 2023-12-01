package utn.frc.grutpi60.servicealquileres.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "ESTACIONES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Estacion {

    @Id
    Integer id;

    @Column(name = "NOMBRE")
    String nombre;

    @Column(name = "FECHA_HORA_CREACION")
    LocalDateTime fechaHoraCreacion;

    @Column(name = "LATITUD")
    Double latitud;

    @Column(name = "LONGITUD")
    Double longitud;
}