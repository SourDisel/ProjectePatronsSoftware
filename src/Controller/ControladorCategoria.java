package Controller;

import View.AfegirCategoriaFrame;
import View.PrincipalFrame;
import Model.Categoria;
import Data.DAOCategoria;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ControladorCategoria {
    private AfegirCategoriaFrame vista;
    private PrincipalFrame principalFrame;
    private ControladorPrincipal controladorPrincipal;

    public ControladorCategoria(AfegirCategoriaFrame vista, PrincipalFrame principalFrame, ControladorPrincipal controladorPrincipal) {
        this.vista = vista;
        this.principalFrame = principalFrame;
        this.controladorPrincipal = controladorPrincipal;
    }

    private ActionListener BotonAñadirCategoria = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nom = vista.getCNomAfegir();
            String descripcio = vista.getCDescripcioAfegir();
            Categoria categoria = new Categoria(nom, descripcio);
            DAOCategoria daoCategoria = new DAOCategoria();
            daoCategoria.insertarCategoria(categoria);

            vista.setVisible(false);
            principalFrame.setVisible(true);
            controladorPrincipal.recargarTabla();
        }
    };

    public void iniciarControlador() {
        vista.getPRAfegirBoton().addActionListener(BotonAñadirCategoria);

        vista.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                principalFrame.setVisible(true);
                controladorPrincipal.recargarTabla();
            }
        });

        vista.setVisible(true);
    }
}
