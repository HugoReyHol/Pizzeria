import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.AlertUtil;
import util.DatabaseManager;
import util.R;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(R.getUI("inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Pizzeria");
        stage.setScene(scene);
        stage.show();

        try {
            DatabaseManager.conectar();

        } catch (Exception e) {
            AlertUtil.mostrarError("No se puede conectar a la base de datos\n\n" + e);
            System.out.println(e);
            Platform.exit();

        }
    }

    /*
    * Cierra la conexion a la base de datos al cerrar el programa
    * */
    @Override
    public void stop() throws Exception {
        super.stop();

        if (DatabaseManager.existeConexion()) {
            System.out.println("Cerrando la base de datos.");
            DatabaseManager.desconectar();

        }
    }

    public static void main(String[] args) {
        launch();
    }
}
