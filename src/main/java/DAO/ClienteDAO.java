package DAO;


import model.Cliente;
import util.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO {

    public static void guardarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Clientes (nombre, contrasena) VALUES (?, ?)";

        PreparedStatement sentencia = DatabaseManager.getCon().prepareStatement(sql);
        sentencia.setString(1, cliente.getNombre());
        sentencia.setString(2, cliente.getContrasena());
        sentencia.executeUpdate();
    }

    public static void eliminarCliente(Cliente cliente) throws SQLException {
        String sql = "DELETE FROM Clientes WHERE IdCliente = ?";

        PreparedStatement sentencia = DatabaseManager.getCon().prepareStatement(sql);
        sentencia.setInt(1, cliente.getIdCliente());
        sentencia.executeUpdate();
    }

    public static void modificarCliente(Cliente clienteAntiguio, Cliente clienteNuevo) throws SQLException {
        String sql = "UPDATE Clientes SET Nombre = ?, Contrasena = ? WHERE IdCliente = ?";

        PreparedStatement sentencia = DatabaseManager.getCon().prepareStatement(sql);
        sentencia.setString(1, clienteNuevo.getNombre());
        sentencia.setString(2, clienteNuevo.getContrasena());
        sentencia.setInt(3, clienteAntiguio.getIdCliente());
        sentencia.executeUpdate();
    }

    public static List<Cliente> obtenerClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Clientes";

        PreparedStatement sentencia = DatabaseManager.getCon().prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Cliente cliente = new Cliente();

            cliente.setIdCliente(resultado.getInt(1));
            cliente.setNombre(resultado.getString(2));
            cliente.setContrasena(resultado.getString(3));

            clientes.add(cliente);
        }

        return clientes;
    }

    public static Cliente obtenerCliente(int idCliente) throws SQLException {
        String sql = "SELECT * FROM Clientes WHERE IdCliente = ?";

        PreparedStatement sentencia = DatabaseManager.getCon().prepareStatement(sql);
        sentencia.setInt(1, idCliente);

        ResultSet resultado = sentencia.executeQuery();

        Cliente cliente = new Cliente();

        if (resultado.next()) {
            cliente.setIdCliente(resultado.getInt(1));
            cliente.setNombre(resultado.getString(2));
            cliente.setContrasena(resultado.getString(3));

        }

        return cliente;
    }

    public static Cliente obtenerCliente(String nombre) throws SQLException {
        String sql = "SELECT * FROM Clientes WHERE Nombre = ?";

        PreparedStatement sentencia = DatabaseManager.getCon().prepareStatement(sql);
        sentencia.setString(1, nombre);

        ResultSet resultado = sentencia.executeQuery();

        Cliente cliente = new Cliente();

        if (resultado.next()) {
            cliente.setIdCliente(resultado.getInt(1));
            cliente.setNombre(resultado.getString(2));
            cliente.setContrasena(resultado.getString(3));

        } else {
            return null;

        }

        return cliente;
    }
}
