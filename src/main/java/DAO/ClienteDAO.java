package DAO;


import model.Cliente;
import util.R;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ClienteDAO {

    private Connection conexion;

    public void conectar() throws ClassNotFoundException, SQLException, IOException {
        Properties configuration = new Properties();
        configuration.load(R.getProperties("database.properties"));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
    }

    public void desconectar() throws SQLException {
        conexion.close();
    }



    public void guardarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Clientes (nombre, telefono, correo, dirreccion) VALUES (?, ?, ?, ?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, cliente.getNombre());
        sentencia.setInt(2, cliente.getTelefono());
        sentencia.setString(3, cliente.getCorreo());
        sentencia.setString(4, cliente.getDireccion());
        sentencia.executeUpdate();
    }

    public void eliminarCliente(Cliente cliente) throws SQLException {
        String sql = "DELETE FROM Clientes WHERE IdCliente = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, cliente.getIdCliente());
        sentencia.executeUpdate();
    }

    public void modificarCliente(Cliente clienteAntiguio, Cliente clienteNuevo) throws SQLException {
        String sql = "UPDATE Clientes SET Nombre = ?,Telefono = ?, Correo = ?, Direccion = ? WHERE IdCliente = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, clienteNuevo.getNombre());
        sentencia.setInt(2, clienteNuevo.getTelefono());
        sentencia.setString(3, clienteNuevo.getCorreo());
        sentencia.setString(4, clienteNuevo.getCorreo());
        sentencia.setInt(5, clienteAntiguio.getIdCliente());
        sentencia.executeUpdate();
    }

    public List<Cliente> obtenerClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Clientes";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Cliente cliente = new Cliente();

            cliente.setIdCliente(resultado.getInt(1));
            cliente.setNombre(resultado.getString(2));
            cliente.setTelefono(resultado.getInt(3));
            cliente.setCorreo(resultado.getString(4));
            cliente.setDireccion(resultado.getString(5));

            clientes.add(cliente);
        }

        return clientes;
    }

    public Cliente obtenerCliente(int idCliente) throws SQLException {
        String sql = "SELECT * FROM Clientes WHERE IdCliente = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, idCliente);

        ResultSet resultado = sentencia.executeQuery();

        Cliente cliente = new Cliente();

        if (resultado.next()) {
            cliente.setIdCliente(resultado.getInt(1));
            cliente.setNombre(resultado.getString(2));
            cliente.setTelefono(resultado.getInt(3));
            cliente.setCorreo(resultado.getString(4));
            cliente.setDireccion(resultado.getString(5));

        }

        return cliente;
    }

    public boolean existeCliente(String correo) throws SQLException {
        String sql = "SELECT * FROM Clientes WHERE correo = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, correo);

        ResultSet resultado = sentencia.executeQuery();

        return resultado.next();
    }
}
