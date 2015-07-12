package eu.over9000.redtrack.ui;

import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import eu.over9000.redtrack.data.issue.Issue;
import eu.over9000.redtrack.data.timeentry.activities.TimeEntryActivity;
import eu.over9000.redtrack.services.IssuesGetService;
import eu.over9000.redtrack.services.TimeEntryActivitesGetService;

/**
 * Created by Jan on 12.07.2015.
 */
public class MainWindow extends Application {
	@Override
	public void start(final Stage primaryStage) throws Exception {

		final javafx.scene.layout.VBox box = new VBox();
		final ObservableList<Issue> issues = FXCollections.observableArrayList();
		final ObservableList<TimeEntryActivity> activities = FXCollections.observableArrayList();

		final ComboBox<Issue> cbIssues = new ComboBox<>(issues);
		final ComboBox<TimeEntryActivity> cbActivities = new ComboBox<>(activities);

		cbIssues.setButtonCell(new IssueListCell());
		cbIssues.setCellFactory(param -> new IssueListCell());

		cbActivities.setButtonCell(new ActivityCell());
		cbActivities.setCellFactory(param -> new ActivityCell());

		box.getChildren().add(cbIssues);
		box.getChildren().add(cbActivities);

		final Scene scene = new Scene(box, 300, 800);

		primaryStage.setTitle("Redtrack");
		primaryStage.setScene(scene);
		primaryStage.show();

		final IssuesGetService s1 = new IssuesGetService();
		s1.setOnSucceeded(event -> {
			issues.addAll(s1.getValue());
			cbIssues.getSelectionModel().selectFirst();
		});
		s1.start();

		final TimeEntryActivitesGetService s2 = new TimeEntryActivitesGetService();
		s2.setOnSucceeded(event -> {
			activities.addAll(s2.getValue());

			final Optional<TimeEntryActivity> defActivity = s2.getValue().stream().filter(activity -> activity.getIsDefault() != null && activity.getIsDefault()).findFirst();
			if (defActivity.isPresent()) {
				cbActivities.getSelectionModel().select(defActivity.get());
			} else {
				cbActivities.getSelectionModel().selectFirst();
			}
		});
		s2.start();

	}
}
