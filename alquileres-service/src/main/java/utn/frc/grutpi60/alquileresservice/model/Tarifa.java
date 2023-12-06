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

import java.time.Duration;

@Entity
@Table(name = "TARIFAS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(makeFinal = true)
public class Tarifa {

    public static final String TABLE_NAME = "TARIFAS";

    @Id
    Integer id;

    @Column(name = "TIPO_TARIFA")
    Integer tipo;

    @Column(name = "DEFINICION")
    String definicion;

    @Column(name = "DIA_SEMANA")
    Integer diaSemana;

    @Column(name = "DIA_MES")
    Integer diaMes;

    @Column(name = "MES")
    Integer mes;

    @Column(name = "ANIO")
    Integer anio;

    @Column(name = "MONTO_FIJO_ALQUILER")
    Double montoFijoAlquiler;

    @Column(name = "MONTO_MINUTO_FRACCION")
    Double montoMinutoFraccion;

    @Column(name = "MONTO_HORA")
    Double montoHora;

    @Column(name = "MONTO_KM")
    Double montoKm;

}