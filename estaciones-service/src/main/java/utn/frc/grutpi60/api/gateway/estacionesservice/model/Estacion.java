package utn.frc.grutpi60.api.gateway.estacionesservice.model;

import jakarta.persistence.*;
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

    @Embedded
    NombreEstacion nombre;

    @Embedded
    Coordenada coordenada;

    @Column(name = "FECHA_HORA_CREACION")
    LocalDateTime fechaHoraCreacion;

    public Estacion(int id, NombreEstacion nombreEstacion, Coordenada coordenadas) {
        this.id = id;
        this.nombre = nombreEstacion;
        this.coordenada = coordenadas;
        this.fechaHoraCreacion = LocalDateTime.now();
    }
}
