package utn.frc.grutpi60.alquileresservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ALQUILERES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Alquiler {

    public static final String TABLE_NAME = "ALQUILERES";

    @Id
    Integer id;

    @Column(name = "ESTADO")
    Integer estado;

    @Column(name = "ID_CLIENTE")
    String idCliente;

    @Column(name = "ESTACION_RETIRO")
    Integer estacionRetiro;

    @Column(name = "ESTACION_DEVOLUCION")
    Integer estacionDevolucion;

    @Column(name = "FECHA_HORA_RETIRO")
    LocalDateTime fechaHoraRetiro;

    @Column(name = "FECHA_HORA_DEVOLUCION")
    LocalDateTime fechaHoraDevolucion;

    @Column(name = "MONTO")
    Double monto;

    @ManyToOne
    @JoinColumn(name = "ID_TARIFA")
    Tarifa tarifa;

    public Alquiler(Integer id, Integer estado, String idCliente, Integer estacionRetiro) {
        this.id = id;
        this.estado = estado;
        this.idCliente = idCliente;
        this.estacionRetiro = estacionRetiro;
        this.fechaHoraRetiro = LocalDateTime.now();
    }

    public boolean isFinalizado() {
        return Objects.equals(this.estado, 2);
    }
}
