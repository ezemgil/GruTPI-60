package utn.frc.grutpi60.alquileresservice.service;

import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.grutpi60.alquileresservice.model.Alquiler;
import utn.frc.grutpi60.alquileresservice.model.Estacion;
import utn.frc.grutpi60.alquileresservice.model.Tarifa;
import utn.frc.grutpi60.alquileresservice.repository.IAlquilerRepository;
import utn.frc.grutpi60.alquileresservice.repository.IdentifierRepository;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AlquilerService implements IAlquilerService {
    @Autowired
    ITarifaService tarifaService;
    @Autowired
    IEstacionesService estacionesService;
    @Autowired
    IAlquilerRepository alquilerRepository;
    @Autowired
    IdentifierRepository identifierRepository;

    @Override
    public List<Alquiler> findAll() {
        return alquilerRepository.findAll();
    }

    @Override
    public Alquiler iniciarAlquiler(String idCliente, Integer idEstacionRetiro) {
        val id = identifierRepository.nextValue(Alquiler.TABLE_NAME);
        Estacion estacion = estacionesService.findById(idEstacionRetiro)
                .orElseThrow(() -> new IllegalArgumentException("Estación no encontrada con ID: " + idEstacionRetiro));
        Alquiler alquiler = new Alquiler(id, 1, idCliente, idEstacionRetiro);
        return alquilerRepository.save(alquiler);
    }


    @Override
    public Alquiler finalizarAlquiler(Integer id, Integer idEstacionDevolucion) {
        Alquiler alquiler = alquilerRepository.findById((long) id)
                .orElseThrow(() -> new IllegalArgumentException("Alquiler no encontrado con ID: " + id));
        Estacion estacion = estacionesService.findById(idEstacionDevolucion)
                .orElseThrow(() -> new IllegalArgumentException("Estación no encontrada con ID: " + idEstacionDevolucion));
        if (alquiler.isFinalizado()) {
            throw new IllegalArgumentException("El alquiler ya se encuentra finalizado");
        }
        Optional<Tarifa> tarifa = tarifaService.getTarifa(alquiler.getFechaHoraRetiro());
        if (tarifa.isPresent() & tarifa != null) {
            val monto = calcularMonto(alquiler, idEstacionDevolucion, tarifa.get());
            alquiler.setEstado(2);
            alquiler.setEstacionDevolucion(idEstacionDevolucion);
            alquiler.setFechaHoraDevolucion(LocalDateTime.now());
            alquiler.setMonto(monto);
            return alquilerRepository.save(alquiler);
        } else {
            throw new IllegalArgumentException("No se encontró una tarifa para la fecha de retiro del alquiler");
        }
    }

    private Double calcularMonto(Alquiler alquiler, Integer idEstacionDevolucion, Tarifa tarifa) {
        Double distancia = estacionesService.calcularDistanciaEntreEstaciones(
                alquiler.getEstacionRetiro(), idEstacionDevolucion) * 0.001;
        Duration duracion = Duration.between(alquiler.getFechaHoraRetiro(), LocalDateTime.now());
        Double montoFinal = tarifa.getMontoFijoAlquiler();
        montoFinal += calcularMontoHora(tarifa.getMontoHora(), tarifa.getMontoMinutoFraccion(), duracion);
        montoFinal += tarifa.getMontoKm() * distancia;
        return montoFinal;
    }

    private Double calcularMontoHora(Double montoHora, Double montoFraccion, Duration duracion) {
        if (duracion.toMinutesPart() > 30) {
            return (duracion.toHours() + 1) * montoHora;
        } else {
            return duracion.toHours() * montoFraccion;
        }
    }
    @Override
    public Object findById(int id) {
        return alquilerRepository.findById((long) id);
    }
}
