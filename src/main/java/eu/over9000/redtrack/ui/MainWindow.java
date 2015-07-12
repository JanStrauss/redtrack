package eu.over9000.redtrack.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import eu.over9000.redtrack.data.Issue;
import eu.over9000.redtrack.services.IssuesRetrieverService;

/**
 * Created by Jan on 12.07.2015.
 */
public class MainWindow extends Application {
	@Override
	public void start(final Stage primaryStage) throws Exception {

		javafx.scene.layout.VBox box = new VBox();
		final ObservableList<Issue> issues = FXCollections.observableArrayList();

		final ComboBox<Issue> cbIssues = new ComboBox<>(issues);
		cbIssues.setButtonCell(new IssueListCell());
		cbIssues.setCellFactory(param -> new IssueListCell());

		box.getChildren().add(cbIssues);

		Scene scene = new Scene(box, 300, 800);

		primaryStage.setTitle("Redtrack");
		primaryStage.setScene(scene);
		primaryStage.show();

		IssuesRetrieverService service = new IssuesRetrieverService();
		service.setOnSucceeded(event -> {
			issues.addAll(service.getValue());
			cbIssues.getSelectionModel().selectFirst();
		});
		service.start();

	}
}
