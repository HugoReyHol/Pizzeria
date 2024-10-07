package controllers;

import DAO.ClienteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import org.apache.commons.codec.digest.DigestUtils;
import util.AlertUtil;
import util.R;

import java.io.IOException;
import java.sql.SQLException;


public class InicioCtrll {

    @FXML
    private TextField entradaNombre;

    @FXML
    private PasswordField entradaContrasena;


    public void onIniciar(ActionEvent actionEvent) throws SQLException, IOException {
        Cliente clienteEntrada = validarEntrada();

        if (clienteEntrada == null) {
            return;
        }

        Cliente cliente = ClienteDAO.obtenerCliente(clienteEntrada.getNombre());

        if (cliente == null) {
            AlertUtil.mostrarInfo("No existe este usuario, pruebe a registrarse");
            return;

        }

        if (!cliente.getContrasena().equals(clienteEntrada.getContrasena())) {
            AlertUtil.mostrarInfo("Contraseña incorrecta");
            return;

        }

        cambiarEscena(cliente);

    }

    public void onRegistrarse(ActionEvent actionEvent) throws IOException, SQLException {
        Cliente clienteEntrada = validarEntrada();

        if (clienteEntrada == null) {
            return;
        }

        Cliente cliente = ClienteDAO.obtenerCliente(clienteEntrada.getNombre());

        if (cliente != null) {
            AlertUtil.mostrarInfo("Ese nombre ya esta registrado");
            return;
        }

        ClienteDAO.guardarCliente(clienteEntrada);

        cambiarEscena(clienteEntrada);

    }

    private Cliente validarEntrada() {
        if (entradaNombre.getText().isEmpty() || entradaContrasena.getText().isEmpty()) {
            AlertUtil.mostrarInfo("Debe rellenar todos los campos");
            return null;

        }

        String nombre = entradaNombre.getText();

        if (nombre.length() > 15) {
            AlertUtil.mostrarInfo("El correo debe ser menor a 15 letras");
            return null;

        }

        String contrasenaEnc = DigestUtils.sha256Hex(entradaContrasena.getText());

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setContrasena(contrasenaEnc);

        return cliente;
    }

    private void cambiarEscena(Cliente cliente) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(R.getUI("pedido.fxml"));

        // Pasa el cliente actual al siguiente controller
        PedidoCtrll ctrll = new PedidoCtrll();
        ctrll.setCliente(cliente);

        fxmlLoader.setController(ctrll);

        // Cambia la escena
        Stage stage = (Stage) entradaNombre.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));

    }
}
