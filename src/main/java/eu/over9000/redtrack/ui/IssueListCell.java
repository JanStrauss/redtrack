package eu.over9000.redtrack.ui;

import javafx.scene.control.ListCell;

import eu.over9000.redtrack.data.Issue;

/**
 * Created by Jan on 12.07.2015.
 */
public class IssueListCell extends ListCell<Issue> {
	@Override
	protected void updateItem(final Issue item, final boolean empty) {
		super.updateItem(item, empty);
		if (item != null) {
			setText(item.getId() + ": " + item.getSubject());
		}
	}
}
