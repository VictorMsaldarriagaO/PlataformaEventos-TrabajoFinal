package Controller;

import model.PlataformaService;

/**
 * Controlador de lógica pura encargado de auditar y procesar las reglas de acceso
 * a la plataforma de manera desacoplada de JavaFX.
 */
public class AutenticacionController {

    /**
     * Procesa la solicitud de ingreso de un usuario.
     * @param correo Cadena con el correo del usuario.
     * @param telefono Cadena con el número celular de contacto.
     * @return true si las credenciales coinciden con el registro en memoria.
     */
    public boolean procesarAutenticacion(String correo, String telefono) {
        if (correo == null || correo.trim().isEmpty() || telefono == null || telefono.trim().isEmpty()) {
            return false;
        }
        return PlataformaService.getInstancia().iniciarSesion(correo.trim(), telefono.trim());
    }
}