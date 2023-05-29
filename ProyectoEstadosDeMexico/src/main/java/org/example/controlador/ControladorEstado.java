package org.example.controlador;

import org.example.modelo.Estado;
import org.example.modelo.ModeloTablaEstado;
import org.example.vista.VentanaEdo;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;

public class ControladorEstado extends MouseAdapter {
    private VentanaEdo view;
    private ModeloTablaEstado modelo;

    public ControladorEstado(VentanaEdo view) {
        this.view = view;
        modelo = new ModeloTablaEstado();
        this.view.getTblEstados().setModel(modelo);
        this.view.getBtnCargar().addMouseListener(this);
        this.view.getBtnAgregar().addMouseListener(this);
        this.view.getTblEstados().addMouseListener(this);
        this.view.getBtnBorrar().addMouseListener(this);
        this.view.getBtnActualizar().addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) { //todos los eventos de cuando se da click con el mouse
        if (e.getSource() == this.view.getBtnCargar()) { // cuando se presiona el boton cargar del panel 1
            // Evento del botón "Cargar"
            modelo.cargarDatos();
            this.view.getTblEstados().setModel(modelo);
            this.view.getTblEstados().updateUI();
        }
        if (e.getSource() == this.view.getBtnAgregar()) {
            // Evento del botón "Agregar"
            Estado estado = new Estado();
            estado.setId(0);
            estado.setNombreEdo(this.view.getTxtEstado().getText());
            estado.setCapital(this.view.getTxtCapital().getText());
            estado.setMunicipio(this.view.getTxtMunicipio().getText());
            estado.setPoblacion(this.view.getTxtPoblacion().getText());
            estado.setURL(this.view.getTxtUrl().getText());
            // se agregan a un

            if (modelo.agregarEstado(estado)) {
                JOptionPane.showMessageDialog(view, "se agrego correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                this.view.getTblEstados().updateUI();
            } else {
                JOptionPane.showMessageDialog(view,
                        "No se pudo agregar a la base de datos. por favor revise su conexion"
                        , "Error al insertar"
                        , JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == view.getTblEstados()) {
            // Evento de clic en la tabla de estados
            System.out.println("evento sobre la tabla");
            int index = this.view.getTblEstados().getSelectedRow(); // se ve la fila en donde fue presionado
            Estado tmp = modelo.getEstadoAtIndex(index);//se obtiene el indice
            try {
                this.view.getImagenEstado().setIcon(tmp.getImagen()); // saldra la imagen del link
            } catch (MalformedURLException mfue) {
                System.out.println(mfue.toString()); // por si el link ess invalido
            }
        }
        if (e.getSource() == this.view.getBtnActualizar()) {
            // Evento del botón "Actualizar"
            int ind = this.view.getTblEstados().getSelectedRow(); // se obtiene el indice de la fila seleccionada
            Estado estado = modelo.getEstadoAtIndex(ind); // se obtiene el objeto estado del indice seleccionado
            String index = String.valueOf(estado.getId()); // se obtiene el indice y se convierte a string
            System.out.println("ControladorEstado dice: " + index);
            estado.setNombreEdo(this.view.getTxtEstadoE().getText());
            estado.setCapital(this.view.getTxtCapitalE().getText());
            estado.setMunicipio(this.view.getTxtMunicipioE().getText());
            estado.setPoblacion(this.view.getTxtPoblacionE().getText());
            estado.setURL(this.view.getTxtUrlE().getText());
            // se actualizan las propiedades del estado con los valores ingresados
            System.out.println("controladorEstado dice " + estado);
            if (modelo.editarEstado(estado, index)) {
                JOptionPane.showMessageDialog(view, "se editó correctamente"
                        , "Aviso", JOptionPane.INFORMATION_MESSAGE);// si la edicion es exitosa, saldra un mensaje de texto
                this.view.getTblEstados().updateUI(); // se actualiza la tabla
            } else {
                JOptionPane.showMessageDialog(view,
                        "No se pudo editar la base de datos. por favor revise su conexion"
                        , "Error al editar"// mensaje de error
                        , JOptionPane.ERROR_MESSAGE);
            }
        }


        if (e.getSource() == this.view.getBtnBorrar()) {
            // Evento del botón "Borrar"
            int respuesta = JOptionPane.showConfirmDialog(view,
                    "estas seguro de borrar el registro?",
                    "confirmacion",
                    JOptionPane.YES_NO_OPTION
            );// para dos opcions, si y no
            if (respuesta == JOptionPane.YES_NO_OPTION){ // si el usuario presiona "si"
                view.getLblResultado().setText("Elegiste la opcion SI");
                int ind = this.view.getTblEstados().getSelectedRow();// se obtiene el indice de la fila seleccionada
                Estado estado = modelo.getEstadoAtIndex(ind); //se obtiene el objeto Estado correspondiente
                // al índice seleccionado
                String index = String.valueOf(estado.getId()); // Se obtiene el índice del estado y
                // se lo convierte a una cadena de texto
                System.out.println("ControladorEstado dice: " +index);
                System.out.println("ControladorEstado dice: " + index.getClass().getSimpleName());

                if (modelo.borrarEstado(index)) { // Se llama al método del objeto modelo
                    // para eliminar el estado de la base de datos.
                    // Si se borra correctamente, se muestra un mensaje de éxito en un cuadro de diálogo
                    JOptionPane.showMessageDialog(view, "se borró correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    this.view.getTblEstados().updateUI();
                } else {
                    JOptionPane.showMessageDialog(view,
                            "No se pudo borrar la base de datos. por favor revise su conexion"
                            , "Error al borrar"
                            , JOptionPane.ERROR_MESSAGE);
                }

            }else {
                view.getLblResultado().setText("Elegiste la opcion NO");
            }

        }
        this.view.limpiar();
    }
}