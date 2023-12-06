package utn.frc.grutpi60.alquileresservice.service;

import utn.frc.grutpi60.alquileresservice.model.Alquiler;

import java.util.List;

public interface IAlquilerService {

    List<Alquiler> findAll();

    Alquiler iniciarAlquiler(String idCliente, Integer idEstacionRetiro);

    Alquiler finalizarAlquiler(Integer id, Integer idEstacionDevolucion);

    Object findById(int id);
}
