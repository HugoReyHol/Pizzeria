package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Cliente;
import model.Pedido;

public class PedidoCtrll {

    @FXML
    private ChoiceBox<String> entradaPizza, entradaBebida;

    @FXML
    private TextField entradaCantidad;

    @FXML
    private ListView<Pedido> listaPedidos;

    private Cliente cliente;


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

    }

    public void onClick(MouseEvent mouseEvent) {
    }

    public void onGuardar(ActionEvent actionEvent) {
    }

    public void onCambiar(ActionEvent actionEvent) {
    }

    public void onSalir(ActionEvent actionEvent) {
        Platform.exit();
    }
}
