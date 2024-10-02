package DAO;


import model.Cliente;
import util.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO {

    public void guardarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Clientes (nombre, telefono, correo, dirreccion, contrasena) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement sentencia = DatabaseManager.getCon().prepareStatement(sql);
        sentencia.setString(1, cliente.getNombre());
        sentencia.setInt(2, cliente.getTelefono());
        sentencia.setString(3, cliente.getCorreo());
        sentencia.setString(4, cliente.getDireccion());
        sentencia.setString(5, cliente.getContrasena());
        sentencia.executeUpdate();
    }

    public void eliminarCliente(Cliente cliente) throws SQLException {
        String sql = "DELETE FROM Clientes WHERE IdCliente = ?";

        PreparedStatement sentencia = DatabaseManager.getCon().prepareStatement(sql);
        sentencia.setInt(1, cliente.getIdCliente());
        sentencia.executeUpdate();
    }

    public void modificarCliente(Cliente clienteAntiguio, Cliente clienteNuevo) throws SQLException {
        String sql = "UPDATE Clientes SET Nombre = ?,Telefono = ?, Correo = ?, Direccion = ?, Contrasena = ? WHERE IdCliente = ?";

        PreparedStatement sentencia = DatabaseManager.getCon().prepareStatement(sql);
        sentencia.setString(1, clienteNuevo.getNombre());
        sentencia.setInt(2, clienteNuevo.getTelefono());
        sentencia.setString(3, clienteNuevo.getCorreo());
        sentencia.setString(4, clienteNuevo.getCorreo());
        sentencia.setString(5, clienteNuevo.getContrasena());
        sentencia.setInt(6, clienteAntiguio.getIdCliente());
        sentencia.executeUpdate();
    }

    public List<Cliente> obtenerClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Clientes";

        PreparedStatement sentencia = DatabaseManager.getCon().prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Cliente cliente = new Cliente();

            cliente.setIdCliente(resultado.getInt(1));
            cliente.setNombre(resultado.getString(2));
            cliente.setTelefono(resultado.getInt(3));
            cliente.setCorreo(resultado.getString(4));
            cliente.setDireccion(resultado.getString(5));
            cliente.setContrasena(resultado.getString(6));

            clientes.add(cliente);
        }

        return clientes;
    }

    public Cliente obtenerCliente(int idCliente) throws SQLException {
        String sql = "SELECT * FROM Clientes WHERE IdCliente = ?";

        PreparedStatement sentencia = DatabaseManager.getCon().prepareStatement(sql);
        sentencia.setInt(1, idCliente);

        ResultSet resultado = sentencia.executeQuery();

        Cliente cliente = new Cliente();

        if (resultado.next()) {
            cliente.setIdCliente(resultado.getInt(1));
            cliente.setNombre(resultado.getString(2));
            cliente.setTelefono(resultado.getInt(3));
            cliente.setCorreo(resultado.getString(4));
            cliente.setDireccion(resultado.getString(5));
            cliente.setContrasena(resultado.getString(6));

        }

        return cliente;
    }

    public Cliente obtenerCliente(String correo) throws SQLException {
        String sql = "SELECT * FROM Clientes WHERE Correo = ?";

        PreparedStatement sentencia = DatabaseManager.getCon().prepareStatement(sql);
        sentencia.setString(1, correo);

        ResultSet resultado = sentencia.executeQuery();

        Cliente cliente = new Cliente();

        if (resultado.next()) {
            cliente.setIdCliente(resultado.getInt(1));
            cliente.setNombre(resultado.getString(2));
            cliente.setTelefono(resultado.getInt(3));
            cliente.setCorreo(resultado.getString(4));
            cliente.setDireccion(resultado.getString(5));
            cliente.setContrasena(resultado.getString(6));

        } else {
            return null;

        }

        return cliente;
    }
}
