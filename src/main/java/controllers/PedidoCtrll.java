package controllers;

import DAO.PedidoDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Cliente;
import model.Pedido;
import util.AlertUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class PedidoCtrll implements Initializable {

    @FXML
    private ChoiceBox<String> entradaPizza, entradaBebida;

    @FXML
    private TextField entradaCantidad;

    @FXML
    private ListView<Pedido> listaPedidos;

    private Cliente cliente;
    private Pedido pedidoCargado;

    private final ObservableList<Pedido> pedidos = FXCollections.observableArrayList();


    public PedidoCtrll(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] pizzas = {"Barbacoa", "4 Quesos", "Margarita"};
        String[] bebidas = {"Agua","CocaCola","Te"};

        entradaPizza.setItems(FXCollections.observableArrayList(pizzas));
        entradaBebida.setItems(FXCollections.observableArrayList(bebidas));

        try {
            pedidos.addAll(PedidoDAO.obtenerPedidos(cliente));

            listaPedidos.setItems(pedidos);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            Platform.exit();

        }

    }

    public void onClick(MouseEvent mouseEvent) {
        pedidoCargado = listaPedidos.getSelectionModel().getSelectedItem();

        entradaPizza.setValue(pedidoCargado.getPizza());
        entradaBebida.setValue(pedidoCargado.getBebida());
        entradaCantidad.setText(String.valueOf(pedidoCargado.getCantidad()));

    }

    public void onActualizar(ActionEvent actionEvent) {
        if (pedidoCargado == null) {
            AlertUtil.mostrarInfo("Seleccione el pedido a modificar");
            return;

        }

        Pedido pedidoNuevo = new Pedido();

        pedidoNuevo.setPizza(entradaPizza.getValue());
        pedidoNuevo.setBebida(entradaBebida.getValue());
        pedidoNuevo.setCliente(cliente);

        try {
            pedidoNuevo.setCantidad(Integer.parseInt(entradaCantidad.getText()));

            if (pedidoNuevo.getCantidad() <= 0) {
                AlertUtil.mostrarInfo("El numero de pizzas compradas debe ser mayor a 0");
                return;
            }

            PedidoDAO.modificarPedido(pedidoCargado, pedidoNuevo);

            pedidoCargado.setPizza(pedidoNuevo.getPizza());
            pedidoCargado.setBebida(pedidoNuevo.getBebida());
            pedidoCargado.setCantidad(pedidoNuevo.getCantidad());

            listaPedidos.refresh();

        } catch (NumberFormatException e) {
            AlertUtil.mostrarInfo("La cantidad debe ser un numero entero");
            entradaCantidad.setText("");

        } catch (SQLException e) {
            AlertUtil.mostrarInfo("No se ha podido modificar el pedido\n\n" + e.getMessage());

        }

    }

    public void onNuevo(ActionEvent actionEvent) {
        Pedido pedidoNuevo = new Pedido();

        pedidoNuevo.setPizza(entradaPizza.getValue());
        pedidoNuevo.setBebida(entradaBebida.getValue());
        pedidoNuevo.setCliente(cliente);

        try {
            pedidoNuevo.setCantidad(Integer.parseInt(entradaCantidad.getText()));

            if (pedidoNuevo.getCantidad() <= 0) {
                AlertUtil.mostrarInfo("El numero de pizzas compradas debe ser mayor a 0");
                return;
            }

            PedidoDAO.guardarPedido(pedidoNuevo);

            pedidos.add(PedidoDAO.obtenerPedidos(cliente).getLast());

        } catch (NumberFormatException e) {
            AlertUtil.mostrarInfo("La cantidad debe ser un numero entero");
            entradaCantidad.setText("");
            return;

        } catch (SQLException e) {
            AlertUtil.mostrarInfo("No se ha podido guardar el pedido\n\n" + e.getMessage());
            return;

        }

        pedidoCargado = pedidos.getLast();
    }

    public void onSalir(ActionEvent actionEvent) {
        Platform.exit();
    }
}
