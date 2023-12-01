package utn.frc.grutpi60.servicealquileres.service;


import utn.frc.grutpi60.servicealquileres.model.Alquiler;

import java.util.List;

public interface IAlquilerService {

    List<Alquiler> findAll();

    Alquiler iniciarAlquiler(String idCliente, int estacionRetiro);

    Alquiler finalizarAlquiler(int id, int estacionDevolucion);

    Object findById(int id);
}
