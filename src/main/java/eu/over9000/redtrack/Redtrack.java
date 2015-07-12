package eu.over9000.redtrack;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Created by Jan on 12.07.2015.
 */
public class Redtrack extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {

		Circle circ = new Circle(40, 40, 30);
		Group root = new Group(circ);
		Scene scene = new Scene(root, 400, 300);

		primaryStage.setTitle("Redtrack");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}
