// ControladorPrincipal.java completo corregido

package Controller;

import Data.DAOProducte;
import Data.DAOCategoria;
import Model.Categoria;
import Model.Producte;
import View.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ControladorPrincipal {

    private final PrincipalFrame vista;
    private final DAOProducte daoProducte;
    private final DAOCategoria daoCategoria;

    public ControladorPrincipal() {
        this.vista = new PrincipalFrame();
        this.daoProducte = new DAOProducte();
        this.daoCategoria = new DAOCategoria();
        cargarCategoriasEnCombo();
        afegirListeners();
    }

    public ControladorPrincipal(PrincipalFrame vista) {
        this.vista = vista;
        this.daoProducte = new DAOProducte();
        this.daoCategoria = new DAOCategoria();
        afegirListeners();
    }

    public void iniciarControlador() {
        vista.setVisible(true);
        carregarTaulaProductes();
    }

    private void afegirListeners() {
        vista.getJButtonAfegir().addActionListener(e -> {
            AfegirProducteFrame finestra = new AfegirProducteFrame();
            ControladorProducte controlador = new ControladorProducte(finestra);
            controlador.iniciarControlador();
        });

        vista.getJButtonAfegirCategoria().addActionListener(e -> {
            AfegirCategoriaFrame finestra = new AfegirCategoriaFrame();
            ControladorCategoria controlador = new ControladorCategoria(finestra);
            controlador.iniciarControlador();
        });

        vista.getJButtonModificar().addActionListener(e -> {
            ModificarProducteFrame finestra = new ModificarProducteFrame();
            ControladorModificacion controlador = new ControladorModificacion(finestra);
            controlador.iniciarControlador();
        });

        vista.getJButtonEliminar().addActionListener(e -> eliminarProducteSeleccionat());

        vista.getJButtonFiltrar().addActionListener(e -> {
            // Aquí puedes implementar filtrado si quieres
            carregarTaulaProductes();
        });
    }

    private void carregarTaulaProductes() {
        List<Producte> productes = daoProducte.llistarProducte();

        DefaultTableModel model = (DefaultTableModel) vista.getJTablePrincipalFruteria().getModel();
        model.setRowCount(0); // limpiamos filas

        for (Producte p : productes) {
            Object[] fila = new Object[] {
                    p.getCodi(),
                    p.getNom(),
                    p.getCategoria() != null ? p.getCategoria().getNombre() : "No categoría",
                    p.getPreu(),
                    p.getTipusPreu(),
                    p.getStock(),
                    p.isOferta()
            };
            model.addRow(fila);
        }
    }

    private void eliminarProducteSeleccionat() {
        JTable taula = vista.getJTablePrincipalFruteria();
        int filaSeleccionada = taula.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int codiProducte = (int) taula.getValueAt(filaSeleccionada, 0);
            daoProducte.eliminarProducte(codiProducte);
            carregarTaulaProductes();
        } else {
            JOptionPane.showMessageDialog(vista, "Selecciona un producte per eliminar-lo.", "Advertència",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void cargarCategoriasEnCombo() {
        List<Categoria> categorias = DAOCategoria.listarCategorias();

        // Primero, limpia los items actuales
        vista.getJComboBoxCategoria().removeAllItems();

        // Añade cada categoría por su nombre
        for (Categoria c : categorias) {
            vista.getJComboBoxCategoria().addItem(c.getNombre());
        }
    }

}
