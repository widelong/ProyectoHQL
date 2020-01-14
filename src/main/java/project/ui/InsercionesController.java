package project.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;

import basictest.HibernateUtil;
import basictest.TestClass1;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import project.model.Residencias;
import project.model.Universidades;


public class InsercionesController implements Initializable {
	@FXML
    private AnchorPane root;

    @FXML
    private Button insertResButton;

    @FXML
    private Button insertResObButton;

    @FXML
    private Button insertEstButton;

    @FXML
    private Button insertUniButton;

    @FXML
    private Button insertEstuButton;

    @FXML
    private Button deleteResButton;

    @FXML
    private Button updateResButton;

	public InsercionesController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HQLInserciones.fxml"));
		loader.setController(this);
		loader.load();
	}

	public AnchorPane getRoot() {
		return root;
	}

	// aqui comprobar si es sql o mysql con la stringproperty

	@Override
	public void initialize(URL location, ResourceBundle resources) {
			insertResButton.setOnAction(e -> onInsertRes());
		
	}

	@SuppressWarnings("deprecation")
	private void onInsertRes() {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.beginTransaction();
		Dialog<Residencias> dialog = new Dialog<>();
		dialog.setTitle("Nueva residencia");
		dialog.setHeaderText("Nueva residencia");

		// Set the button types.
		ButtonType addButton = new ButtonType("Añadir", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		
		Label nombreLabel = new Label("Nombre: ");
		TextField nombreText = new TextField();

		Label universidadLabel = new Label("Universidad: ");
		ComboBox<String> universidadesBox = new ComboBox<String>();
		universidadesBox.setPromptText("Elige tipo");
		Session getUnis = HibernateUtil.getSessionFactory().openSession();
		Query q = getUnis.createQuery("from Universidades "); //recoger los codigos en una lista y luego usar la universidad con 
		//ese codigo al crear la residencia
		List<Universidades> universidades = q.list();
		ArrayList<String> codigosUni = new ArrayList<String>();
		ArrayList<String> nombresUni = new ArrayList<String>();
		for(int i = 0; i < universidades.size(); i++) {
			codigosUni.add(universidades.get(i).getCodUniversidad());
			nombresUni.add(universidades.get(i).getNomUniversidad());
		}
		universidadesBox.getItems().addAll(nombresUni);
		
		Label precioLabel = new Label("Precio mensual: ");
		TextField precioText = new TextField();
		
		Label comedorLabel = new Label("Comedor: ");
		RadioButton comedorSi;
		RadioButton comedorNo;
		ToggleGroup comedorGroup;
		comedorSi = new RadioButton("Si");
		comedorNo = new RadioButton("No");
		comedorGroup = new ToggleGroup();
		comedorGroup.getToggles().addAll(comedorNo, comedorSi);
		
		grid.add(nombreLabel, 0, 0);
		grid.add(nombreText, 1, 0);
		grid.add(universidadLabel, 0, 1);
		grid.add(universidadesBox, 1, 1);
		grid.add(precioLabel, 0, 2);
		grid.add(precioText, 1, 2);
		grid.add(comedorLabel, 0, 3);
		grid.add(comedorSi, 0, 4);
		grid.add(comedorNo, 1, 4);
		
		dialog.getDialogPane().setContent(grid);
		getUnis.close();

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == addButton) {
		        Residencias residencia = new Residencias();
		        String codUniversidad = codigosUni.get(nombresUni.indexOf(universidadesBox.getSelectionModel().getSelectedItem()));
		        Session 
		        residencia.setCodUniversidad(codUniversidad);
		        return residencia;
		    }
		    return null;
		});
		
		Residencias residencia = dialog.showAndWait().get();
//		
//		
//		
//		
//		sesion.save(residencia);
//		sesion.getTransaction().commit();
//		sesion.close();
		
	}


}

