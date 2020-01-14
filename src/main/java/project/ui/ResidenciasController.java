package project.ui;

import java.io.IOException;
import java.net.URL;

import java.sql.Connection;

import java.util.ArrayList;

import java.util.ResourceBundle;



import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import project.model.Residencias;


public class ResidenciasController implements Initializable{
	@FXML
	AnchorPane root;
	@FXML 
	TableView<Residencias> table;
	@FXML
	Button insertButton;
	@FXML
	Button deleteButton;
	@FXML
	Button modificarButton;
	@FXML 
	Button insertProcButton;
	@FXML
	Button estanciaProcButton;
	@FXML
	Button precioProcButton;
	@FXML
	Button fechaFuncButton;
	
	TextField residenText;
	TextField univerText;
	ComboBox<String> univerCombo;
	TextField precioMesText;
	RadioButton comedorSi;
	RadioButton comedorNo;
	ToggleGroup comedorGroup;
	Button insertValorButton;
	Button modiValorButton;
	Stage secondaryStage;
	
	
	private ArrayList<Residencias> residencias = new ArrayList<Residencias>();
	
	public BooleanProperty checkForUpdate = new SimpleBooleanProperty();
	Connection connection = null;
	public StringProperty tipoBase = new SimpleStringProperty();
	
	public ResidenciasController() throws IOException {		 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HQLResidencias.fxml"));
 		loader.setController(this);
		loader.load();
	}
	
	public AnchorPane getRoot() {
		return root;
	}

	
	//aqui comprobar si es sql o mysql con la stringproperty
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//crear la tabla
		TableColumn<Residencias, Integer> col1 = new TableColumn<>("Codigo Residencia");
		TableColumn<Residencias, String> col2 = new TableColumn<>("Nombre Residencia");
		TableColumn<Residencias, String> col3 = new TableColumn<>("Nombre Universidad");
		TableColumn<Residencias, Integer> col4 = new TableColumn<>("Precio");
		TableColumn<Residencias, Boolean> col5 = new TableColumn<>("Comedor");
		table.getColumns().add(col1);
		table.getColumns().add(col2);
		table.getColumns().add(col3);
		table.getColumns().add(col4);
		table.getColumns().add(col5);
		col1.setCellValueFactory(new PropertyValueFactory<>("codResidencia"));
		col2.setCellValueFactory(new PropertyValueFactory<>("nomResidencia"));
		col3.setCellValueFactory(new PropertyValueFactory<>("nomUniversidad"));
		col4.setCellValueFactory(new PropertyValueFactory<>("precioMensual"));
		col5.setCellValueFactory(new PropertyValueFactory<>("comedor"));
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
	}

	
	public void rellenarTabla() {
		//preparar statement hql como vaya
		residencias.clear();		
		//recogida de datos en el array como vaya
		table.getItems().clear();
		for (int i = 0; i < residencias.size(); i++) {
			/*table.getItems().add(new Residencias(residencias.get(i).getCodResidencia()SEGUIR COMO CON ESTE
			 * , nomResidencia.get(i), nomUniversidad.get(i),
					precioMensual.get(i), comedor.get(i)));*/
		}
	}

	
}

