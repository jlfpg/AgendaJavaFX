package Vista;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class VentanaPrincipalController implements Initializable {

	private Main ProgramaPrincipal;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnNew;

	@FXML
	private Button btnEdit;

	public void setProgramaPrincipal(Main ProgramaPrincipal) {
		this.ProgramaPrincipal = ProgramaPrincipal;
	}

	@FXML
	private TableView<Modelo.Persona> Tabla;

	@FXML
	private TableColumn<Modelo.Persona, String> ColName;

	@FXML
	private TableColumn<Modelo.Persona, String> ColApellido;

	@FXML
	private TableColumn<Modelo.Persona, String> ColTel;

	private final ObservableList<Modelo.Persona> data = FXCollections.observableArrayList(
			new Modelo.Persona("David", "Perez", "9999"), new Modelo.Persona("Pedro", "Camacho", "888"));

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Tabla.setItems(this.data);
		ColName.setCellValueFactory(new PropertyValueFactory<Modelo.Persona, String>("nombre"));
		ColApellido.setCellValueFactory(new PropertyValueFactory<Modelo.Persona, String>("apellido"));
		ColTel.setCellValueFactory(new PropertyValueFactory<Modelo.Persona, String>("telefono"));
	}

	@FXML
	private void nuevaVentana() {
		this.ProgramaPrincipal.mostrarVentanaSecundaria();
	}

	@FXML
	private void borrarFila() {
		Modelo.Persona selectedItem = Tabla.getSelectionModel().getSelectedItem();
		if (selectedItem == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error fila no seleccionada!");
			alert.setContentText("Por favor seleccione primero la fila que desea borrar");

			alert.showAndWait();
		} else {
			Tabla.getItems().remove(selectedItem);
		}
	}

	@FXML
	private void editarFila() {
		Modelo.Persona selectedItem = Tabla.getSelectionModel().getSelectedItem();
		if (selectedItem == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error fila no seleccionada!");
			alert.setContentText("Por favor seleccione primero la fila que desea editar");

			alert.showAndWait();
		} else {
			this.ProgramaPrincipal.mostrarVentanaSecundaria();
		}
	}

}