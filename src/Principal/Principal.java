/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;
import Controller.ControladorPrincipal;
import Data.DataBaseCreacio;
/**
 *
 * @author ivanm
 */
public class Principal {
    public static void main(String[] args) {
        // Creem la base de dades
        DataBaseCreacio.CreacionBase();
        ControladorPrincipal controlador = new ControladorPrincipal();
        controlador.iniciarControlador();
    }
}
