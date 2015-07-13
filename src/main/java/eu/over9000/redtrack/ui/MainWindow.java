package eu.over9000.redtrack.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.stage.StageStyle;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

/**
 * Created by Jan on 12.07.2015.
 */
public class MainWindow extends Application {
	
	private final VBox stopwatchContainer = new VBox();

	private Button transferButton;
	private Stage stage;

	@Override

	public void start(final Stage primaryStage) throws Exception {
		this.stage = primaryStage;
		
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
		transferButton.setOnAction(event -> timeEntryMask.setHours(stopwatch.getDurationForJson()));
		transferContainer.getChildren().add(transferButton);
		transferContainer.setBackground(new Background(new BackgroundFill(Color.valueOf("#F5F5F5"), CornerRadii.EMPTY, Insets.EMPTY)));
		transferContainer.setPadding(new Insets(10));
		transferContainer.setAlignment(Pos.CENTER);
		

		box.getChildren().add(stopwatchContainer);
		box.getChildren().add(transferContainer);
		box.getChildren().add(timeEntryMask);

		final Scene scene = new Scene(box);

		box.setBackground(new Background(new BackgroundFill(Color.valueOf("white"), CornerRadii.EMPTY, Insets.EMPTY)));


		Platform.setImplicitExit(false);

		final Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		primaryStage.setTitle("Redtrack");
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.sizeToScene();
		primaryStage.getIcons().add(new Image(getClass().getResource("/images/icon.png").toExternalForm()));
		primaryStage.show();
		primaryStage.setX(screen.getWidth() - 50 - primaryStage.getWidth());
		primaryStage.setY(screen.getHeight() - 50 - primaryStage.getHeight());
		primaryStage.focusedProperty().addListener(event -> {
			if (!primaryStage.focusedProperty().get()) {
				primaryStage.hide();
			}

		});
		addAppToTray();
	}

	public void onStopwatchStopAndReset(final boolean stop) {
		transferButton.setDisable(!stop);
	}

	private void addAppToTray() {
		try {
			java.awt.Toolkit.getDefaultToolkit();

			if (!java.awt.SystemTray.isSupported()) {
				System.out.println("No system tray support, application exiting.");
				Platform.exit();
			}

			final java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
			final java.awt.TrayIcon trayIcon = new java.awt.TrayIcon(ImageIO.read(getClass().getResource("/images/icon.png")).getScaledInstance(tray.getTrayIconSize().width, tray.getTrayIconSize().height, java.awt.Image.SCALE_SMOOTH));

			trayIcon.addMouseListener(new MouseAdapter() {
				public void mouseClicked(final MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						Platform.runLater(MainWindow.this::showStage);
					}
				}
			});

			final java.awt.MenuItem openItem = new java.awt.MenuItem("Open Redtrack");
			openItem.addActionListener(event -> Platform.runLater(this::showStage));

			final java.awt.MenuItem exitItem = new java.awt.MenuItem("Exit");
			exitItem.addActionListener(event -> {
				Platform.exit();
				tray.remove(trayIcon);
			});

			final java.awt.PopupMenu popup = new java.awt.PopupMenu();
			popup.add(openItem);
			popup.addSeparator();
			popup.add(exitItem);
			trayIcon.setPopupMenu(popup);

			tray.add(trayIcon);
		} catch (java.awt.AWTException | IOException e) {
			System.out.println("Unable to init system tray");
			e.printStackTrace();
		}
	}

	private void showStage() {
		stage.show();
		stage.toFront();
		stage.requestFocus();
	}

}
