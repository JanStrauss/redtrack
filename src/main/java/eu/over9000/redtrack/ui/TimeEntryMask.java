package eu.over9000.redtrack.ui;

import java.time.LocalDate;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import eu.over9000.redtrack.data.issue.Issue;
import eu.over9000.redtrack.data.timeentry.TimeEntryPost;
import eu.over9000.redtrack.data.timeentry.activities.TimeEntryActivity;
import eu.over9000.redtrack.services.IssuesGetService;
import eu.over9000.redtrack.services.TimeEntriesPostService;
import eu.over9000.redtrack.services.TimeEntryActivitesGetService;
import eu.over9000.redtrack.ui.cells.ActivityCell;
import eu.over9000.redtrack.ui.cells.IssueListCell;
import eu.over9000.redtrack.ui.textfields.LengthRestrictedTextField;
import eu.over9000.redtrack.ui.textfields.NumberTextField;

/**
 * Form to fill in a new time entry
 */
public class TimeEntryMask extends StackPane {
	final ObservableList<Issue> issues = FXCollections.observableArrayList();
	final ObservableList<TimeEntryActivity> activities = FXCollections.observableArrayList();

	private final GridPane grid = new GridPane();
	private final ComboBox<Issue> cbIssues = new ComboBox<>(issues);
	private final ComboBox<TimeEntryActivity> cbActivities = new ComboBox<>(activities);
	private final TextField tfHours = new NumberTextField();
	private final DatePicker dpDate = new DatePicker(LocalDate.now());
	private final TextField tfComment = new LengthRestrictedTextField();
	private final Button btRefreshIssues = new Button();
	private final Button btRefreshActivities = new Button();
	private final Button btCreate = new Button("Create");
	private final ProgressIndicator progressIndicator = new ProgressIndicator();
	private final Label lbStatus = new Label();

	public TimeEntryMask() {
		final Label lbIssues = new Label("Issue ");
		final Label lbActivity = new Label("Activity ");
		final Label lbHours = new Label("Hours ");
		final Label lbDate = new Label("Date ");
		final Label lbComment = new Label("Comment ");

		cbIssues.setMinWidth(200);
		cbIssues.setMaxWidth(200);
		dpDate.setMinWidth(200);
		dpDate.setMaxWidth(200);
		cbActivities.setMinWidth(200);
		cbActivities.setMaxWidth(200);

		progressIndicator.setMaxWidth(64);
		progressIndicator.setMaxHeight(64);
		progressIndicator.setVisible(false);

		btRefreshIssues.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.REFRESH));
		btRefreshActivities.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.REFRESH));
		btCreate.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.SAVE));
		btCreate.setStyle(btCreate.getStyle() + ";-fx-base: #d34224");

		cbIssues.setButtonCell(new IssueListCell());
		cbIssues.setCellFactory(param -> new IssueListCell());
		cbIssues.setDisable(true);

		cbActivities.setButtonCell(new ActivityCell());
		cbActivities.setCellFactory(param -> new ActivityCell());
		cbActivities.setDisable(true);
		cbActivities.setPrefWidth(Control.USE_COMPUTED_SIZE);

		final Runnable issuesCallback = () -> {
			cbIssues.setDisable(false);
			btRefreshIssues.setDisable(false);
		};

		final Runnable activitiesCallback = () -> {
			cbActivities.setDisable(false);
			btRefreshActivities.setDisable(false);
		};


		btRefreshIssues.setDisable(true);
		btRefreshIssues.setOnAction(event -> {
			cbIssues.setDisable(true);
			btRefreshIssues.setDisable(true);
			refreshIssues(issuesCallback);
		});

		btRefreshActivities.setDisable(true);
		btRefreshActivities.setOnAction(event -> {
			cbActivities.setDisable(true);
			btRefreshActivities.setDisable(true);
			refreshActivities(activitiesCallback);
		});

		btCreate.setOnAction(event -> {

			if (tfHours.getText().isEmpty()) {
				lbStatus.setText("no hours set");
				return;
			}

			if (tfComment.getText().isEmpty()) {
				lbStatus.setText("no comment set");
				return;
			}

			TimeEntryPost toPost = new TimeEntryPost();

			toPost.setComments(tfComment.getText());
			toPost.setIssueId(cbIssues.getSelectionModel().getSelectedItem().getId());
			toPost.setActivityId(cbActivities.getSelectionModel().getSelectedItem().getId());
			toPost.setSpendOn(dpDate.getValue());
			toPost.setHours(Float.parseFloat(tfHours.getText()));

			TimeEntriesPostService service = new TimeEntriesPostService(toPost);
			service.setOnSucceeded(event2 -> {
				progressIndicator.setVisible(false);
				grid.setDisable(false);
				lbStatus.setText("time entry created");
				tfComment.clear();
				tfHours.clear();
			});
			service.setOnFailed(event2 -> {
				progressIndicator.setVisible(false);
				grid.setDisable(false);
				lbStatus.setText("time entry creation failed");
			});
			grid.setDisable(true);
			progressIndicator.setVisible(true);
			service.start();
		});

		grid.getColumnConstraints().add(0, new ColumnConstraints(60));
		grid.getColumnConstraints().add(1, new ColumnConstraints(200));
		grid.getColumnConstraints().add(2, new ColumnConstraints(30));

		grid.setPadding(new Insets(10));

		grid.setHgap(10);
		grid.setVgap(10);

		grid.add(lbIssues, 0, 0);
		grid.add(cbIssues, 1, 0);
		grid.add(btRefreshIssues, 2, 0);

		grid.add(lbActivity, 0, 1);
		grid.add(cbActivities, 1, 1);
		grid.add(btRefreshActivities, 2, 1);

		grid.add(lbHours, 0, 2);
		grid.add(tfHours, 1, 2);

		grid.add(lbDate, 0, 3);
		grid.add(dpDate, 1, 3);

		grid.add(lbComment, 0, 4);
		grid.add(tfComment, 1, 4);

		final HBox createBox = new HBox(10, btCreate, lbStatus);
		createBox.setAlignment(Pos.BASELINE_LEFT);
		grid.add(createBox, 1, 5);

		refreshIssues(issuesCallback);
		refreshActivities(activitiesCallback);

		getChildren().add(grid);
		getChildren().add(progressIndicator);
	}

	private void refreshActivities(final Runnable callback) {
		final TimeEntryActivitesGetService s2 = new TimeEntryActivitesGetService();
		s2.setOnSucceeded(event -> {
			activities.clear();
			activities.addAll(s2.getValue());

			final Optional<TimeEntryActivity> defActivity = s2.getValue().stream().filter(activity -> activity.getIsDefault() != null && activity.getIsDefault()).findFirst();
			if (defActivity.isPresent()) {
				cbActivities.getSelectionModel().select(defActivity.get());
			} else {
				cbActivities.getSelectionModel().selectFirst();
			}

			if (callback != null) {
				callback.run();
			}
		});
		s2.start();
	}

	private void refreshIssues(final Runnable callback) {
		final IssuesGetService s1 = new IssuesGetService();
		s1.setOnSucceeded(event -> {
			issues.clear();
			issues.addAll(s1.getValue());
			cbIssues.getSelectionModel().selectFirst();
			if (callback != null) {
				callback.run();
			}
		});
		s1.setOnFailed(event -> {
			s1.getException().printStackTrace();
		});
		s1.start();
	}

	public void setHours(final float hours) {
		tfHours.setText(Float.toString(hours));
	}
}
