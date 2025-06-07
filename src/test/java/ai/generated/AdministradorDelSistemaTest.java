package com.example.demo.service;

import com.example.demo.model.Empleado;
import com.example.demo.exception.GenderException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdministradorDelSistemaTest {

    @Test
    public void eliminarEmpleado_generoFemenino_lanzaExcepcion() {
        // Preparar
        Empleado empleado = new Empleado();
        empleado.setGenero("Femenino");
        AdministradorDelSistema admin = new AdministradorDelSistema();

        // Ejecutar y verificar
        assertThrows(GenderException.class, () -> admin.eliminarEmpleado(empleado));
    }
}
