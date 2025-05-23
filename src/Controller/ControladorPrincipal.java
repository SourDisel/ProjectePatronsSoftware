package Controller;

import Model.Categoria;
import Data.DAOCategoria;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import View.AfegirProducteFrame;
import View.AfegirCategoriaFrame;
import View.ModificarProducteFrame;
import Data.DAOProducte;

public class ControladorPrincipal {

    ActionListener BotonAfegirProducte = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            AfegirProducteFrame vista = new AfegirProducteFrame();
            ControladorProducte controlador = new ControladorProducte(vista);
            controlador.iniciarControlador();
        }
    };

    ActionListener BotonAfegirCategoria = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            AfegirCategoriaFrame vista = new AfegirCategoriaFrame();
            ControladorCategoria controlador = new ControladorCategoria(vista);
            controlador.iniciarControlador();
        }
    };
    ActionListener BotonModificarProducte = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ModificarProducteFrame vista = new ModificarProducteFrame();
            ControladorModificacion controlador = new ControladorModificacion(vista);
            controlador.iniciarControlador();
        }
    };
    ActionListener BotonEliminarProducte = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };
    ActionListener BotonFiltrar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };
    ActionListener llistatProductes = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };
    public void iniciarControlador() {
        AfegirProducteFrame vista = new AfegirProducteFrame();
        vista.setVisible(true);
    }
}
