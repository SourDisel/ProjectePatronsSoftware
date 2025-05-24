package Controller;

import View.AfegirProducteFrame;
import Model.Categoria;
import Model.Producte;
import Data.DAOProducte;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import Data.DAOCategoria;
public class ControladorProducte {
    private AfegirProducteFrame vista;

    public ControladorProducte(AfegirProducteFrame vista) {
        this.vista = vista;
    }

    private ActionListener BotonAñadirProducte = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nom = vista.getPNomAfegir();
            String Cat = vista.getPCategoriaAfegir();
            int idCategoria = DAOCategoria.buscarIdPorNombre(Cat);
            double preu = Double.parseDouble(vista.getPPreuAfegir());
            String Tipus = vista.getPTipusPreuAfegir();
            int stock = Integer.parseInt(vista.getPStockAfegir());
            Producte producte = new Producte(nom, idCategoria, preu, Tipus,stock);
            DAOProducte daoProducte = new DAOProducte();
            daoProducte.insertarProducte(producte);
        }
    };

    public void iniciarControlador() {
        vista.getPRAfegirBoton().addActionListener(BotonAñadirProducte);
        cargarCategoriasEnCombo();
        vista.setVisible(true);
    }
    public void cargarCategoriasEnCombo() {
        List<Categoria> categorias = DAOCategoria.listarCategorias();

        // Primero, limpia los items actuales
        vista.getComboBoxCategoria().removeAllItems();

        // Añade cada categoría por su nombre
        for (Categoria c : categorias) {
            vista.getComboBoxCategoria().addItem(c.getNombre());
        }
    }
}
