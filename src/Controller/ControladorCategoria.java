package Controller;

import View.AfegirCategoriaFrame;
import Model.Categoria;
import Data.DAOCategoria;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ControladorCategoria {
    private AfegirCategoriaFrame vista;

    public ControladorCategoria(AfegirCategoriaFrame vista) {
        this.vista = vista;
    }

    private ActionListener BotonAñadirCategoria = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nom = vista.getCNomAfegir();
            String descripcio = vista.getCDescripcioAfegir();
            Categoria categoria = new Categoria(nom, descripcio);
            DAOCategoria daoCategoria = new DAOCategoria();
            daoCategoria.insertarCategoria(categoria);
        }
    };
    public void iniciarControlador() {
        vista.getPRAfegirBoton().addActionListener(BotonAñadirCategoria);
        vista.setVisible(true);
    }
}

