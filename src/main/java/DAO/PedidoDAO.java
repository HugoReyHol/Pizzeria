package DAO;


import model.Cliente;
import util.DatabaseManager;
import model.Pedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PedidoDAO {

    public void guardarPedido(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO Pedidos (cliente, pizza, cantidad, bebida) VALUES (?, ?, ?, ?)";

        PreparedStatement sentencia = DatabaseManager.getCon().prepareStatement(sql);
        sentencia.setInt(1, pedido.getCliente().getIdCliente());
        sentencia.setString(2, pedido.getPizza());
        sentencia.setInt(3, pedido.getCantidad());
        sentencia.setString(4, pedido.getBebida());
        sentencia.executeUpdate();
    }

    public void eliminarPedido(Pedido pedido) throws SQLException {
        String sql = "DELETE FROM Pedidos WHERE IdPedido = ?";

        PreparedStatement sentencia = DatabaseManager.getCon().prepareStatement(sql);
        sentencia.setInt(1, pedido.getIdPedido());
        sentencia.executeUpdate();
    }

    public void modificarPedido(Pedido pedidoAntiguo, Pedido pedidoNuevo) throws SQLException {
        String sql = "UPDATE Pedidos SET Cliente = ?, Pizza = ?, Cantidad = ?, Bebida = ? WHERE IdPedido = ?";

        PreparedStatement sentencia = DatabaseManager.getCon().prepareStatement(sql);
        sentencia.setInt(1, pedidoNuevo.getCliente().getIdCliente());
        sentencia.setString(2, pedidoNuevo.getPizza());
        sentencia.setInt(3, pedidoNuevo.getCantidad());
        sentencia.setString(4, pedidoNuevo.getBebida());
        sentencia.setInt(5, pedidoAntiguo.getIdPedido());
        sentencia.executeUpdate();
    }

    public List<Pedido> obtenerPedidos() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedidos";

        PreparedStatement sentencia = DatabaseManager.getCon().prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();

        ClienteDAO clienteDAO = new ClienteDAO();
        while (resultado.next()) {
            Pedido pedido = new Pedido();

            pedido.setIdPedido(resultado.getInt(1));
            pedido.setCliente(clienteDAO.obtenerCliente(resultado.getInt(2)));
            pedido.setPizza(resultado.getString(3));
            pedido.setCantidad(resultado.getInt(4));
            pedido.setBebida(resultado.getString(5));

            pedidos.add(pedido);
        }

        return pedidos;
    }

    public List<Pedido> obtenerPedidos(Cliente cliente) throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedidos WHERE cliente = ?";

        PreparedStatement sentencia = DatabaseManager.getCon().prepareStatement(sql);
        sentencia.setInt(cliente.getIdCliente(), 1);

        ResultSet resultado = sentencia.executeQuery();

        ClienteDAO clienteDAO = new ClienteDAO();
        while (resultado.next()) {
            Pedido pedido = new Pedido();

            pedido.setIdPedido(resultado.getInt(1));
            pedido.setCliente(clienteDAO.obtenerCliente(resultado.getInt(2)));
            pedido.setPizza(resultado.getString(3));
            pedido.setCantidad(resultado.getInt(4));
            pedido.setBebida(resultado.getString(5));

            pedidos.add(pedido);
        }

        return pedidos;
    }
}
