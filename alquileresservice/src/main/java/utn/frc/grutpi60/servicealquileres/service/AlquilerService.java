package utn.frc.grutpi60.servicealquileres.service;

import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.grutpi60.servicealquileres.model.Alquiler;
import utn.frc.grutpi60.servicealquileres.model.Tarifa;
import utn.frc.grutpi60.servicealquileres.repository.IAlquilerRepository;
import utn.frc.grutpi60.servicealquileres.repository.IdentifierRepository;

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
    public Alquiler iniciarAlquiler(String idCliente, int estacionRetiro) {
        val id = identifierRepository.nextValue(Alquiler.TABLE_NAME);
        val alquiler = new Alquiler(id, 1, idCliente, estacionRetiro);
        return alquilerRepository.save(alquiler);
    }

    @Override
    public Alquiler finalizarAlquiler(int id, int estacionDevolucion) {
        val alquiler = alquilerRepository.findById((long) id)
                .orElseThrow(() -> new IllegalArgumentException("Alquiler no encontrado con ID: " + id));
        alquiler.setEstado(2);
        alquiler.setEstacionDevolucion(estacionDevolucion);
        alquiler.setFechaHoraDevolucion(LocalDateTime.now());
        val monto = calcularMonto(alquiler);
        alquiler.setMonto(monto);
        return alquilerRepository.save(alquiler);
    }

    private Double calcularMonto(Alquiler alquiler) {
        Double montoFinal = 0.0;
        Double distancia = estacionesService.calcularDistanciaEntreEstaciones(alquiler.getEstacionRetiro(), alquiler.getEstacionDevolucion()) * 0.001;
        Duration horas = Duration.between(alquiler.getFechaHoraRetiro(), alquiler.getFechaHoraDevolucion());
        Optional<Tarifa> tarifa = tarifaService.findByDiaSemana(alquiler.getFechaHoraRetiro().getDayOfWeek().getValue());
        if (tarifa.isPresent()) {
            if (horas.toMinutes() > 30) {
                montoFinal += tarifa.get().getMontoHora() * horas.toHours();
            } else {
                montoFinal += tarifa.get().getMontoHora();
            }
            montoFinal += tarifa.get().getMontoKm() * distancia;
        } else {
            throw new IllegalArgumentException("Tarifa no encontrada");
        }
        return montoFinal;
    }

    @Override
    public Object findById(int id) {
        return alquilerRepository.findById((long) id);
    }
}
