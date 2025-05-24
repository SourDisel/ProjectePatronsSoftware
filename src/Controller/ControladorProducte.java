package Controller;

import View.AfegirProducteFrame;
import View.PrincipalFrame;
import Model.Categoria;
import Model.Producte;
import Data.DAOProducte;
import Data.DAOCategoria;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class ControladorProducte {
    private AfegirProducteFrame vista;
    private PrincipalFrame principalFrame;
    private ControladorPrincipal controladorPrincipal;  // <-- Agregado

    public ControladorProducte(AfegirProducteFrame vista, PrincipalFrame principalFrame, ControladorPrincipal controladorPrincipal) {
        this.vista = vista;
        this.principalFrame = principalFrame;
        this.controladorPrincipal = controladorPrincipal;  // <-- Inicializado
    }

    private final ActionListener BotonAñadirProducte = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String nom = vista.getPNomAfegir();
                String nomCategoria = vista.getPCategoriaAfegir();
                int idCategoria = DAOCategoria.buscarIdPorNombre(nomCategoria);

                Categoria categoria = new Categoria();
                categoria.setId(idCategoria);
                categoria.setNombre(nomCategoria);

                double preu = Double.parseDouble(vista.getPPreuAfegir());
                String tipus = vista.getPTipusPreuAfegir();
                int stock = Integer.parseInt(vista.getPStockAfegir());
                boolean oferta = vista.getPAfegirOferta().equalsIgnoreCase("Oferta");

                Producte producte = new Producte(nom, categoria, preu, tipus, stock, oferta);

                DAOProducte daoProducte = new DAOProducte();
                daoProducte.insertarProducte(producte);

                System.out.println("Producte afegit correctament: " + producte);

                vista.setVisible(false);
                principalFrame.setVisible(true);
                controladorPrincipal.recargarTabla();  // <-- Aquí recarga tabla

            } catch (NumberFormatException ex) {
                System.err.println("Error: preu o stock no vàlids.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    };

    public void iniciarControlador() {
        vista.getPRAfegirBoton().addActionListener(BotonAñadirProducte);
        cargarCategoriasEnCombo();

        vista.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                principalFrame.setVisible(true);
                controladorPrincipal.recargarTabla();  // <-- Recarga también al cerrar
            }
        });

        vista.setVisible(true);
    }

    public void cargarCategoriasEnCombo() {
        List<Categoria> categorias = DAOCategoria.listarCategorias();
        vista.getComboBoxCategoria().removeAllItems();
        for (Categoria c : categorias) {
            vista.getComboBoxCategoria().addItem(c.getNombre());
        }
    }
}
