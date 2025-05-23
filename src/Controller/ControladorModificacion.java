package Controller;

import Model.Producte;
import View.ModificarProducteFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Data.DAOCategoria;

public class ControladorModificacion {
    private Data.DAOProducte daoProducte;
    private View.ModificarProducteFrame vista;
    public ControladorModificacion(ModificarProducteFrame vista) {
        this.vista = vista;
    }
    private ActionListener BotonModificarProducte = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nom = vista.getNomodificat();
            String Cat = vista.getCategoriaModificat();
            int idCategoria = DAOCategoria.buscarIdPorNombre(Cat);
            double preu = Double.parseDouble(vista.getPreuModificat());
            String Tipus = vista.getTipusPreuModificat();
            int stock = Integer.parseInt(vista.getStockModificat());
            Producte producteModificat = new Producte(nom, idCategoria, preu, Tipus, stock);
            daoProducte.modificarProducte(producteModificat);
    }
        };
    public void iniciarControlador() {
        vista.getPRModificarBoton().addActionListener(BotonModificarProducte);
        vista.setVisible(true);
    }
}
