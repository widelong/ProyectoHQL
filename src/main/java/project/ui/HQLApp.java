package project.ui;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.stage.Stage;

public class HQLApp extends Application {
	ABDController controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new ABDController();
		
		Scene scene = new Scene(controller.getRoot(), 600, 500);

		primaryStage.setTitle("Infernate");
		primaryStage.setScene(scene);
		primaryStage.show();
			
	}

	public static void main(String[] args) {
		launch(args);
	}

}
