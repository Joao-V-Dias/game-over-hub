package com.gameover.hub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Launcher extends Application{
	@Override
	public void start(Stage stage) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("/com/gameover/hub/fxml/TelaLogin.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 565, 295);
		String css = this.getClass().getResource("/com/gameover/hub/css/login.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args){
		launch(args);
	}
}
