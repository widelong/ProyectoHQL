package project.ui;

import java.io.IOException;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ABDController {
	//view
	TabPane root;
	Tab inserciones;
	Tab residencias;
	
	//conexion con otras pestañas
	InsercionesController insertControl;
	ResidenciasController resiControl;
	
	
	public ABDController() throws IOException {
		insertControl = new InsercionesController();
		inserciones = new Tab();
		inserciones.setContent(insertControl.getRoot());
		inserciones.setText("Inserciones");
		inserciones.setId("inserciones");
		
		resiControl = new ResidenciasController();
		residencias = new Tab();
		residencias.setContent(resiControl.getRoot());
		residencias.setText("Residencias");
		residencias.setId("residencias");
		
		root = new TabPane(inserciones, residencias);
		
	}
	
@SuppressWarnings("unused")
private void actualizaTablas() {
		resiControl.rellenarTabla();
		
	}



	public TabPane getRoot() {
		return root;
	}
	
}
