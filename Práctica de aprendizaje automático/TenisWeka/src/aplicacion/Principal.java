/*
 *  Javier Zudaire
 */
package aplicacion;

import java.io.IOException;
import modelos.Modelo;

/**
 *
 * @author javierzudaire
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Modelo modelo = new Modelo();
        modelo.introducirDatos();
        modelo.aprenderModelo();
        System.out.println("El clasificador con Random Forest obtiene que el ganador es " + modelo.aplicarModelo());
    }

}
