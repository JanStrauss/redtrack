package eu.over9000.redtrack.data.timeentry;

import java.util.List;

import eu.over9000.redtrack.data.generic.PaginatedResponse;

/**
 * Created by Jan on 12.07.2015.
 */
public class TimeEntryResponse extends PaginatedResponse {
	private List<TimeEntry> timeEntries;

	public List<TimeEntry> getTimeEntries() {
		return timeEntries;
	}

	public void setTimeEntries(final List<TimeEntry> timeEntries) {
		this.timeEntries = timeEntries;
	}

	@Override
	public String toString() {
		return "TimeEntryResponse{" +
				"timeEntries=" + timeEntries +
				"} " + super.toString();
	}
}
