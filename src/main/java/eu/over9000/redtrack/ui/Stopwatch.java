package eu.over9000.redtrack.ui;

import java.time.Duration;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

/**
 * Created by Jan on 12.07.2015.
 */
public class Stopwatch extends VBox {
	
	public static final Font FONT_DISPLAY = Font.font(48);
	public static final Font FONT_BUTTONS = Font.font(24);
	private final Label display = new Label();
	
	private final Button btnStopReset;
	private final Button btnStartPause;
	private final MainWindow mainWindow;
	
	private enum State {INITIALIZED, RUNNING, PAUSED, FINISHED}
	
	private State state;
	
	private long running_duration = 0;
	
	private final DurationDiffProvider diffProvider;
	
	private final Timeline timeline;
	
	public Stopwatch(final MainWindow mainWindow) {
		this.mainWindow = mainWindow;

		timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.getKeyFrames().setAll(new KeyFrame(javafx.util.Duration.seconds(1), ae -> updateDisplay()));
		diffProvider = new DurationDiffProvider();
		state = State.INITIALIZED;
		
		getChildren().add(display);

		btnStopReset = new Button();
		btnStartPause = new Button();

		btnStopReset.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.STOP));
		btnStartPause.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.PLAY));

		btnStartPause.setFont(FONT_BUTTONS);
		btnStopReset.setFont(FONT_BUTTONS);
		btnStopReset.setDisable(true);
		
		final HBox hBox = new HBox(btnStartPause, btnStopReset);
		hBox.setPadding(new Insets(10));
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.CENTER);
		getChildren().add(hBox);
		
		btnStartPause.setOnAction(event -> startOrPause());
		btnStopReset.setOnAction(event -> stopOrReset());
		
		display.setFont(FONT_DISPLAY);
		display.setStyle("-fx-text-fill: white");
		updateDisplay();
		
		setPadding(new Insets(10));
		setAlignment(Pos.CENTER);
	}
	
	public void startOrPause() {
		
		if (state == State.PAUSED || state == State.INITIALIZED) {
			diffProvider.getDiff();
			btnStartPause.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.PAUSE));
			btnStopReset.setDisable(false);
			timeline.play();
			state = State.RUNNING;

		} else if (state == State.RUNNING) {
			running_duration += diffProvider.getDiff();
			btnStartPause.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.PLAY));
			timeline.pause();
			state = State.PAUSED;
		}
		updateDisplay();
	}
	
	public void stopOrReset() {
		
		if (state == State.FINISHED) {
			running_duration = 0;
			btnStopReset.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.STOP));
			btnStartPause.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.PLAY));
			btnStartPause.setDisable(false);
			btnStopReset.setDisable(true);
			mainWindow.onStopwatchStopAndReset(false);
			state = State.INITIALIZED;

		} else if (state == State.RUNNING || state == State.PAUSED) {
			running_duration += diffProvider.getDiff();
			timeline.stop();
			btnStopReset.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.REPEAT));
			btnStartPause.setDisable(true);
			mainWindow.onStopwatchStopAndReset(true);
			state = State.FINISHED;

		}
		
		updateDisplay();
	}
	
	private void updateDisplay() {
		if (state == State.RUNNING) {
			running_duration += diffProvider.getDiff();
		}
		
		final Duration duration = Duration.ofMillis(running_duration);
		
		final long hours = duration.toHours();
		final long minutes = duration.minusHours(hours).toMinutes();
		final long seconds = duration.minusMinutes(minutes).getSeconds();
		
		display.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
	}
	
	public float getDurationForJson() {
		return Duration.ofMillis(running_duration).getSeconds() / 3600.0f;
	}
	
	private class DurationDiffProvider {
		private long last = System.currentTimeMillis();
		
		public long getDiff() {
			final long current = System.currentTimeMillis();
			final long diff = current - last;
			last = current;
			return diff;
		}
	}
}
