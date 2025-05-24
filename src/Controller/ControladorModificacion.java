package Controller;

import Model.Categoria;
import Model.Producte;
import View.ModificarProducteFrame;
import View.PrincipalFrame;
import Data.DAOCategoria;
import Data.DAOProducte;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class ControladorModificacion {
    private DAOProducte daoProducte;
    private ModificarProducteFrame vista;
    private PrincipalFrame principalFrame;
    private ControladorPrincipal controladorPrincipal;
    private int productoId; // Guarda el id para modificar

    public ControladorModificacion(ModificarProducteFrame vista, PrincipalFrame principalFrame,
            ControladorPrincipal controladorPrincipal) {
        this.vista = vista;
        this.principalFrame = principalFrame;
        this.controladorPrincipal = controladorPrincipal;
        this.daoProducte = new DAOProducte();
    }

    private final ActionListener botonModificarProducte = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String nom = vista.getNomodificat();
                String Cat = (String) vista.getComboBoxCategoria().getSelectedItem();
                int idCategoria = DAOCategoria.buscarIdPorNombre(Cat);

                Categoria categoria = new Categoria();
                categoria.setId(idCategoria);
                categoria.setNombre(Cat);

                double preu = Double.parseDouble(vista.getPreuModificat());
                String Tipus = vista.getTipusPreuModificat();
                int stock = Integer.parseInt(vista.getStockModificat());

                Producte producteModificat = new Producte();
                producteModificat.setNom(nom);
                producteModificat.setCategoria(categoria);
                producteModificat.setPreu(preu);
                producteModificat.setTipusPreu(Tipus);
                producteModificat.setStock(stock);

                daoProducte.modificarProducte(producteModificat);

                JOptionPane.showMessageDialog(vista, "Producto modificado correctamente.");

                vista.setVisible(false);
                principalFrame.setVisible(true);
                controladorPrincipal.recargarTabla();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Formato de número incorrecto.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(vista, "Error inesperado: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    };

    public void iniciarControlador() {
        cargarCategoriasEnCombo();
        vista.getPRModificarBoton().addActionListener(botonModificarProducte);

        vista.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                principalFrame.setVisible(true);
                controladorPrincipal.recargarTabla();
            }
        });

        vista.setVisible(true);
    }

    public void cargarProducto(Producte producto) {
        cargarCategoriasEnCombo(); // Carga todas las categorías en el combo

        vista.setNomodificat(producto.getNom());

        String nombreCategoria = producto.getCategoria() != null ? producto.getCategoria().getNombre() : "";
        vista.setCategoriaModificat(nombreCategoria); // aquí se selecciona en el JComboBox la categoría

        vista.setPreuModificat(String.valueOf(producto.getPreu()));
        vista.setTipusPreuModificat(producto.getTipusPreu());
        vista.setStockModificat(String.valueOf(producto.getStock()));
    }

    public void cargarCategoriasEnCombo() {
    List<Categoria> categorias = DAOCategoria.listarCategorias();
    vista.getComboBoxCategoria().removeAllItems();
    for (Categoria c : categorias) {
        vista.getComboBoxCategoria().addItem(c.getNombre());
    }
}
}
