package com.example.demo.service;

import com.example.demo.model.Empleado;
import com.example.demo.exception.GenderException;

public class AdministradorDelSistema {

    // Métodos existentes

    // MODIFICACIÓN INICIO: HDU-EMP-003
    public void eliminarEmpleado(Empleado empleado) throws GenderException {
        // Lógica existente de eliminación de empleado

        // Validación de género
        if (empleado.getGenero().equalsIgnoreCase("Femenino")) {
            throw new GenderException("No se permite eliminar empleados de género femenino");
        }

        // Lógica existente de eliminación de empleado
    }
    // MODIFICACIÓN FIN: HDU-EMP-003
}

