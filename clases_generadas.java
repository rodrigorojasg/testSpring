package com.example.demo.model;

public class Empleado {
    private String id;
    private String nombre;
    private String genero;

    // Constructor, getters y setters existentes

    // Getter para el genero del empleado
    public String getGenero() {
        return genero;
    }
}

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

package com.example.demo.exception;

public class GenderException extends Exception {
    public GenderException(String message) {
        super(message);
    }
}

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
