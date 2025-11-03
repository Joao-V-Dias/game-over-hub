package com.gameover.hub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Launcher extends Application{
	@Override
	public void start(Stage stage) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("/com/gameover/hub/fxml/TelaLogin.fxml"));
		stage.setTitle("Game Over");
		stage.getIcons().add(new Image(Objects.requireNonNull(Launcher.class.getResourceAsStream("/com/gameover/hub/img/logo.png"))));
		Scene scene = new Scene(fxmlLoader.load(), 565, 295);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args){
		launch(args);
	}
}
