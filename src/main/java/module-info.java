module com.gameover.hub {
	requires javafx.controls;
	requires javafx.fxml;

	requires org.controlsfx.controls;
	requires com.dlsc.formsfx;
	requires org.kordamp.bootstrapfx.core;
	requires javafx.graphics;
	requires java.prefs;
	requires java.sql;
	requires static lombok;
	requires org.slf4j;
	requires java.dotenv;

	opens com.gameover.hub to javafx.fxml;
	opens com.gameover.hub.model to javafx.base;
	exports com.gameover.hub;
	exports com.gameover.hub.view;
	exports com.gameover.hub.model;
	opens com.gameover.hub.view to javafx.fxml;
}