package application;

import java.io.IOException;

import Vista.VentanaDosController;
import Vista.VentanaPrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {

	private static Stage stagePrincipal;
	private AnchorPane rootPane;

	@Override
	public void start(Stage stagePrincipal) throws Exception {
		Main.stagePrincipal = stagePrincipal;
		mostrarVentanaPrincipal();

	}

	/*
	 * En un proyecto JavaFX el main llama al launch que a su vez llama a start
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * cargamos la ventana principal
	 */
	public void mostrarVentanaPrincipal() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("../Vista/VentanaPrincipal.fxml"));
			rootPane = (AnchorPane) loader.load();
			Scene scene = new Scene(rootPane);
			stagePrincipal.setTitle("Ventana Principal");
			stagePrincipal.setScene(scene);
			/*
			 * Añadidos las llamadas del main al Controlador y del controlador
			 * al main
			 ***/
			VentanaPrincipalController controller = loader.getController();
			controller.setProgramaPrincipal(this);

			stagePrincipal.show();
		} catch (IOException e) {
			// tratar la excepción.
		}
	}

	/*
	 * Este método es llamado cuando se presiona el botón de la ventana
	 * principal Lo llama el controlador de la vista principal
	 */
	public void mostrarVentanaSecundaria() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("../vista/VentanaDos.fxml"));
			AnchorPane ventanaDos = (AnchorPane) loader.load();
			/* Creamos la segunda ventana como otro stage */
			Stage ventana = new Stage();
			ventana.setTitle("Venta Dos");
			/* Le decimos a la ventana quién es la ventana original */
			ventana.initOwner(stagePrincipal);
			Scene scene = new Scene(ventanaDos);
			ventana.setScene(scene);

			VentanaDosController controller2 = loader.getController();
			controller2.setStagePrincipal(ventana);

			ventana.show();

		} catch (Exception e) {
			// tratar la excepción
		}
	}

}