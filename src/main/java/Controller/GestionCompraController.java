package Controller;

import model.CompraFacade;
import model.Evento;
import model.Zona;
import model.Asiento;
import model.Usuario;

/**
 * Coordinador lógico que procesa el flujo transaccional crudo,
 * actuando como un intermediario directo antes de delegar las llamadas a la Fachada.
 */
public class GestionCompraController {
    private CompraFacade fachadaCompras;

    public GestionCompraController() {
        this.fachadaCompras = new CompraFacade();
    }

    /**
     * Valida la consistencia estructural de los parámetros antes de disparar el cobro.
     */
    public boolean ejecutarTransaccion(Usuario usuario, Evento evento, Zona zona, Asiento asiento,
                                       String tarjeta, boolean vip, boolean seguro) {

        if (usuario == null || evento == null || zona == null || asiento == null) {
            return false;
        }
        if (tarjeta == null || tarjeta.trim().isEmpty()) {
            return false;
        }
        return fachadaCompras.efectuarCompra(usuario, evento, zona, asiento, tarjeta.trim(), vip, seguro);
    }
}