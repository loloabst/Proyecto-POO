package org.example.modelo;

import org.example.modelo.persistencia.EstadoDAO;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeloTablaEstado implements TableModel {
    public static final int COLUMNS = 6;
    private ArrayList<Estado> datos;
    private EstadoDAO ldao;

    public ModeloTablaEstado() {
        ldao = new EstadoDAO();
        datos = new ArrayList<>();
    }
    // Implementación de los métodos de la interfaz TableModel
    public ModeloTablaEstado(ArrayList<Estado> datos) {
        this.datos = datos;
        ldao = new EstadoDAO();
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }// Retorna el número de filas en la tabla

    @Override
    public int getColumnCount() {
        return COLUMNS;
    }// Retorna el número de columnas en la tabla

    @Override
    public String getColumnName(int rowIndex) {// Retorna el nombre de la columna en el índice dado
        switch (rowIndex) {
            case 0:
                return "id";
            case 1:
                return "nombreEdo";
            case 2:
                return "Capital";
            case 3:
                return "Municipio";
            case 4:
                return "Poblacion";
            case 5:
                return "URL";

        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int rowIndex) {// Retorna la clase de datos
        // para la columna en el índice dado
        switch (rowIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;

        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {// Indica si una celda
        // en particular es editable o no. en este caso, si
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {  // Retorna el valor de la celda
        // en la fila y columna especificadas
        Estado tmp = datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tmp.getId();
            case 1:
                return tmp.getNombreEdo();
            case 2:
                return tmp.getCapital();
            case 3:
                return tmp.getMunicipio();
            case 4:
                return tmp.getPoblacion();
            case 5:
                return tmp.getURL();

        }
        return null;
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int colIndex) {// Establece el valor
        // de la celda en la fila y columna especificadas
        switch (colIndex) {
            case 0:
            // No se modifica nada para la columna 'id'
                break;
            case 1:
                datos.get(rowIndex).setNombreEdo((String) o);
                break;
            case 2:
                datos.get(rowIndex).setCapital((String) o);
                break;
            case 3:
                datos.get(rowIndex).setMunicipio((String) o);
                break;
            case 4:
                datos.get(rowIndex).setPoblacion((String) o);
                break;
            case 5:
                datos.get(rowIndex).setURL((String) o);
                break;

            default:
                System.out.println("no se modifica nada");
        }

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    public void cargarDatos() { // Carga los datos desde la capa de acceso
        // a datos (EstadoDAO) al ArrayList de datos
        try {
            ArrayList<Estado> tirar = ldao.obtenerTodo();
            System.out.println(tirar);
            datos = ldao.obtenerTodo();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

    }

    public boolean agregarEstado(Estado estado) {// Agrega un nuevo Estado a
        // la tabla y a la capa de acceso a datos
        boolean resultado = false;
        try {
            if (ldao.insertar(estado)) {
                datos.add(estado);
                resultado = true;
            } else {
                resultado = false;
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return resultado;
    }
    public Estado getEstadoAtIndex(int idx){ // Retorna el
        // objeto Estado en el índice especificado
        return datos.get(idx);
    }
    public  boolean editarEstado(Estado estado, String index){// Edita un Estado existente en
        // la tabla y en la capa de acceso a datos
        System.out.println("modelo tabla dice " + estado);
        boolean resultado = false;
        try {
            if (ldao.update(estado,index)) {
                resultado = true;
            } else {
                resultado = false;
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return resultado;
    }
    public boolean borrarEstado(String index) {// Borra un Estado de
        // la tabla y de la capa de acceso a datos
        System.out.println("modelo tabla dice: " + index);
        System.out.println("modelo tabla dice: " + index.getClass().getSimpleName());
        boolean resultado = false;
        try {
            if (ldao.delete(index)) {
                resultado = true;
            } else {
                resultado = false;
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return resultado;
    }
}