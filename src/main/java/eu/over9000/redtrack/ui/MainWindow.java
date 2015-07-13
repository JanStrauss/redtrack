package eu.over9000.redtrack.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

/**
 * Created by Jan on 12.07.2015.
 */
public class MainWindow extends Application {
	
	private final VBox stopwatchContainer = new VBox();

	private Button transferButton;

	@Override

	public void start(final Stage primaryStage) throws Exception {
		
		final javafx.scene.layout.VBox box = new VBox();

		final TimeEntryMask timeEntryMask = new TimeEntryMask();
		
		final Stopwatch stopwatch = new Stopwatch(this);
		stopwatchContainer.getChildren().add(stopwatch);
		final BackgroundImage myBI = new BackgroundImage(new Image(getClass().getResource("/images/bg_tile.png").toExternalForm()), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		stopwatchContainer.setBackground(new Background(myBI));
		
		final VBox transferContainer = new VBox();
		transferButton = new Button();
		transferButton.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.ANGLE_DOUBLE_DOWN));
		transferButton.setDisable(true);
		transferButton.setFont(Font.font(24));
		transferButton.setOnAction(event -> {
			timeEntryMask.setHours(stopwatch.getDurationForJson());
		});
		transferContainer.getChildren().add(transferButton);
		transferContainer.setBackground(new Background(new BackgroundFill(Color.valueOf("#F5F5F5"), CornerRadii.EMPTY, Insets.EMPTY)));
		transferContainer.setPadding(new Insets(10));
		transferContainer.setAlignment(Pos.CENTER);
		

		box.getChildren().add(stopwatchContainer);
		box.getChildren().add(transferContainer);
		box.getChildren().add(timeEntryMask);


		final Scene scene = new Scene(box);

		box.setBackground(new Background(new BackgroundFill(Color.valueOf("white"), CornerRadii.EMPTY, Insets.EMPTY)));


		//Platform.setImplicitExit(false);

		final Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		primaryStage.setTitle("Redtrack");
		primaryStage.setScene(scene);
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.sizeToScene();
		primaryStage.show();
		//		primaryStage.focusedProperty().addListener(event -> {
		//			primaryStage.hide();
		//		});
	}

	public void onStopwatchStopAndReset(final boolean stop) {
		transferButton.setDisable(!stop);
	}
}
