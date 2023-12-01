package utn.frc.grutpi60.servicealquileres.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.grutpi60.servicealquileres.model.Tarifa;
import utn.frc.grutpi60.servicealquileres.repository.ITarifaRepository;

import java.util.Optional;

@Service
public class TarifaService implements ITarifaService{
    @Autowired
    ITarifaRepository tarifaRepository;

    @Override
    public Optional<Tarifa> findByTipo(int tipo) {
        try {
            return tarifaRepository.findByTipo(tipo);
        } catch (Exception e) {
            throw new IllegalArgumentException("Tarifa no encontrada");
        }
    }

    @Override
    public Optional<Tarifa> findByDefinicion(String definicion) {
        return tarifaRepository.findByDefinicion(definicion);
    }

    @Override
    public Optional<Tarifa> findByDiaSemana(int diaSemana) {
        return tarifaRepository.findByDiaSemana(diaSemana);
    }

    @Override
    public Optional<Tarifa> findByDiaMes(int diaMes) {
        try {
            return tarifaRepository.findByDiaMes(diaMes);
        } catch (Exception e) {
            throw new IllegalArgumentException("Tarifa no encontrada");
        }
    }

    @Override
    public Optional<Tarifa> findByAnio(int anio) {
        try {
            return tarifaRepository.findByAnio(anio);
        } catch (Exception e) {
            throw new IllegalArgumentException("Tarifa no encontrada");
        }
    }
}
