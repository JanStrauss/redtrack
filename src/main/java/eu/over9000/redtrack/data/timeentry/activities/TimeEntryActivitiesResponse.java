package eu.over9000.redtrack.data.timeentry.activities;

import java.util.List;

/**
 * Created by Jan on 12.07.2015.
 */
public class TimeEntryActivitiesResponse {
	private List<TimeEntryActivity> timeEntryActivities;

	public List<TimeEntryActivity> getTimeEntryActivities() {
		return timeEntryActivities;
	}

	public void setTimeEntryActivities(final List<TimeEntryActivity> timeEntryActivities) {
		this.timeEntryActivities = timeEntryActivities;
	}

	@Override
	public String toString() {
		return "TimeEntryActivitiesResponse{" +
				"timeEntryActivities=" + timeEntryActivities +
				'}';
	}
}
