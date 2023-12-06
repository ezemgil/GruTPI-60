package utn.frc.grutpi60.alquileresservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "ESTACIONES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Estacion {

    public static final String TABLE_NAME = "ESTACIONES";

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
