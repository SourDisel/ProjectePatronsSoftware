package Controller;

import Data.DAOProducte;
import Data.DAOCategoria;
import Model.Categoria;
import Model.Producte;
import View.ModificarProducteFrame;
import View.PrincipalFrame;
import View.AfegirProducteFrame;
import View.AfegirCategoriaFrame;
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
            ControladorProducte controlador = new ControladorProducte(finestra, vista, this);
            controlador.iniciarControlador();
            vista.setVisible(false);
        });

        vista.getJButtonAfegirCategoria().addActionListener(e -> {
            AfegirCategoriaFrame finestra = new AfegirCategoriaFrame();
            ControladorCategoria controlador = new ControladorCategoria(finestra, vista, this);
            controlador.iniciarControlador();
            vista.setVisible(false);
        });

        vista.getJButtonModificar().addActionListener(e -> {
            int filaSeleccionada = vista.getJTablePrincipalFruteria().getSelectedRow();
            if (filaSeleccionada >= 0) {
                int codiProducte = (int) vista.getJTablePrincipalFruteria().getValueAt(filaSeleccionada, 0);
                Producte productoSeleccionado = daoProducte.buscarProducte(codiProducte);
                if (productoSeleccionado != null) {
                    ModificarProducteFrame finestraModificar = new ModificarProducteFrame();
                    ControladorModificacion controladorMod = new ControladorModificacion(finestraModificar, vista,
                            this);
                    controladorMod.cargarProducto(productoSeleccionado); // carga datos en el formulario
                    controladorMod.iniciarControlador();
                    vista.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(vista, "No se pudo cargar el producto seleccionado.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Selecciona un producto para modificar.", "Atención",
                        JOptionPane.WARNING_MESSAGE);
            }
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

        vista.getJComboBoxCategoria().removeAllItems();

        for (Categoria c : categorias) {
            vista.getJComboBoxCategoria().addItem(c.getNombre());
        }
    }

    public void recargarTabla() {
        carregarTaulaProductes();
    }
}
