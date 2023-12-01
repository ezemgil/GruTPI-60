package utn.frc.grutpi60.servicealquileres.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ALQUILERES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Alquiler {

    public static final String TABLE_NAME = "ALQUILERES";

    @Id
    Integer id;

    @Column(name = "ID_CLIENTE")
    String idCliente;

    @Column(name = "ESTADO")
    Integer estado;

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
        this.idCliente = Objects.requireNonNull(idCliente, "ID Cliente no puede ser nulo");
        this.estacionRetiro = estacionRetiro;
        this.fechaHoraRetiro = LocalDateTime.now();
    }

    public boolean isFinalizado() {
        return Objects.equals(this.estado, 2);
    }
}
