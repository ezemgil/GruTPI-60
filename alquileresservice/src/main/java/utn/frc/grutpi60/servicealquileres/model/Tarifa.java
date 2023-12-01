package utn.frc.grutpi60.servicealquileres.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

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
    Float montoFijoAlquiler;

    @Column(name = "MONTO_MINUTO_FRACCION")
    Float montoMinutoFraccion;

    @Column(name = "MONTO_HORA")
    Float montoHora;

    @Column(name = "MONTO_KM")
    Float montoKm;
}
