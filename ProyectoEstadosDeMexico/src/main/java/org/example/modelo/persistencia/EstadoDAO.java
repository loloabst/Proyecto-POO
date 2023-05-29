package org.example.modelo.persistencia;

import org.example.modelo.Estado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EstadoDAO implements InterfazDAO {
    public EstadoDAO() {
    }

    @Override
    public boolean insertar(Object obj) throws SQLException {
        // Método para insertar un estado en la base de datos
        String sqlInsert = "INSERT INTO estados(nombre, capital, municipio, poblacion, URL) VALUES(?, ?, ?, ?, ?)";
        int rowCount = 0; // para saber el numero de filas afectadas
        PreparedStatement pstm = ConexionSingleton.getInstance("estadosDB.db").getConnection().prepareStatement(sqlInsert);
        pstm.setString(1, ((Estado) obj).getNombreEdo());
        pstm.setString(2, ((Estado) obj).getCapital());
        pstm.setString(3, ((Estado) obj).getMunicipio());
        pstm.setString(4, ((Estado) obj).getPoblacion());
        pstm.setString(5, ((Estado) obj).getURL());
        rowCount = pstm.executeUpdate();
        return rowCount > 0;
    }

    @Override
    public boolean update(Object estado, String index) throws SQLException {
        // Método para actualizar un estado en la base de datos
        String sqlUpdate = "UPDATE estados SET nombre = ?, capital = ?, municipio = ?, poblacion = ?, URL = ? WHERE id = ? ; ";
        int rowCount = 0;
        System.out.println("EstadoDAO dice: " + estado);
        PreparedStatement pstm = ConexionSingleton.getInstance("estadosDB.db").getConnection().prepareStatement(sqlUpdate);
        pstm.setString(1, ((Estado) estado).getNombreEdo());
        pstm.setString(2, ((Estado) estado).getCapital());
        pstm.setString(3, ((Estado) estado).getMunicipio());
        pstm.setString(4, ((Estado) estado).getPoblacion());
        pstm.setString(5, ((Estado) estado).getURL());
        pstm.setString(6, index);
        System.out.println("EstadoDAO dice: " + pstm);
        rowCount = pstm.executeUpdate();
        return rowCount > 0;
    }

    @Override
    public boolean delete(String index) throws SQLException {
        // Método para eliminar un estado de la base de datos
        System.out.println("EstadoDAO dice: " + index);
        String sqlDelete = "DELETE FROM estados WHERE id = ?;";
        int rowCount = 0;
        PreparedStatement pstm = ConexionSingleton.getInstance("estadosDB.db").getConnection().prepareStatement(sqlDelete);
        pstm.setInt(1, Integer.parseInt(index));
        System.out.println("valor pstm : " + index);
        rowCount = pstm.executeUpdate(); // numero de filas afectadas
        System.out.println("valor rowcount : " +rowCount);

        return rowCount > 0;
    }

    @Override
    public ArrayList obtenerTodo() throws SQLException {
        // Método para obtener todos los estados de la base de datos
        String sql = "SELECT * FROM estados";
        ArrayList<Estado> resultado = new ArrayList<>();

        Statement stm = ConexionSingleton.getInstance("estadosDB.db").getConnection().createStatement();
        ResultSet rst = stm.executeQuery(sql);
        while (rst.next()){
            resultado.add(new Estado(rst.getInt(1),rst.getString(2), rst.getString(3), rst.getString(4),rst.getString(5),rst.getString(6)));
        }

        return resultado;
    }
}
