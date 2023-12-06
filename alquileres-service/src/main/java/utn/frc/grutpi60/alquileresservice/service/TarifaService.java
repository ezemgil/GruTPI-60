package utn.frc.grutpi60.alquileresservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.grutpi60.alquileresservice.model.Tarifa;
import utn.frc.grutpi60.alquileresservice.repository.ITarifaRepository;


import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TarifaService implements ITarifaService{
    @Autowired
    ITarifaRepository tarifaRepository;

    @Override
    public Optional<Tarifa> findByDiaSemana(int diaSemana) {
        return tarifaRepository.findByDiaSemana(diaSemana);
    }

    @Override
    public Optional<Tarifa> getTarifa(LocalDateTime fecha) {
        Optional<Tarifa> tarifa = null;
        tarifa = tarifaRepository.findByDiaMes(fecha.getDayOfMonth());
        if (!tarifa.isPresent()) {
            tarifa = tarifaRepository.findByDiaSemana(fecha.getDayOfWeek().getValue());
        }
        return tarifa;
    }
}
