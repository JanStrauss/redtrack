package eu.over9000.redtrack.ui.cells;

import javafx.scene.control.ListCell;

import eu.over9000.redtrack.data.timeentry.activities.TimeEntryActivity;

/**
 * Cell for TimeEntryActivities
 */
public class ActivityCell extends ListCell<TimeEntryActivity> {
	@Override
	protected void updateItem(final TimeEntryActivity item, final boolean empty) {
		super.updateItem(item, empty);

		if (empty || item == null) {
			setText(null);
			setGraphic(null);
		} else {
			setText(item.getName());
		}
	}
}
