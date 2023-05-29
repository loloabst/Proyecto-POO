package org.example.modelo.persistencia;


import java.sql.SQLException;
import java.util.ArrayList;

public interface InterfazDAO {
    public abstract boolean insertar(Object obj) throws SQLException;
    public abstract boolean update(Object obj, String index) throws SQLException;
    public abstract boolean delete(String id) throws SQLException;
    public  abstract ArrayList obtenerTodo() throws SQLException;

}