package controllers;

import DAO.ClienteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Cliente;
import org.apache.commons.codec.digest.DigestUtils;
import util.AlertUtil;

import java.sql.SQLException;


public class InicioCtrll {

    @FXML
    private TextField entradaNombre;

    @FXML
    private PasswordField entradaContrasena;


    public void onIniciar(ActionEvent actionEvent) throws SQLException {
        if (entradaNombre.getText().isEmpty() || entradaContrasena.getText().isEmpty()) {
            AlertUtil.mostrarInfo("Debe rellenar todos los campos");
            return;

        }

        String nombre = entradaNombre.getText();

        if (nombre.length() > 15) {
            AlertUtil.mostrarInfo("El correo debe ser menor a 15 letras");
            return;

        }

        String contrasenaEnc = DigestUtils.sha256Hex(entradaContrasena.getText());

        Cliente cliente = ClienteDAO.obtenerCliente(nombre);

        if (cliente == null) {
            AlertUtil.mostrarInfo("No existe este usuario, pruebe a registrarse");
            return;

        }

        if (!cliente.getContrasena().equals(contrasenaEnc)) {
            AlertUtil.mostrarInfo("Contraseña incorrecta");
            return;

        }

        // TODO cambiar escena

    }

    public void onRegistrarse(ActionEvent actionEvent) {


    }
}
