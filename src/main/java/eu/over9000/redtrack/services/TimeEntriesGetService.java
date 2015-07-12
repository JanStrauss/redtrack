package eu.over9000.redtrack.services;

import java.util.ArrayList;
import java.util.List;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import eu.over9000.redtrack.data.timeentry.TimeEntry;
import eu.over9000.redtrack.data.timeentry.TimeEntryResponse;
import eu.over9000.redtrack.rest.RestException;
import eu.over9000.redtrack.rest.RestRequestWrapper;

/**
 * Created by Jan on 12.07.2015.
 */
public class TimeEntriesGetService extends Service<List<TimeEntry>> {
	
	private final int issueId;
	
	public TimeEntriesGetService(final int issueId) {
		this.issueId = issueId;
	}
	
	@Override
	protected Task<List<TimeEntry>> createTask() {
		return new Task<List<TimeEntry>>() {
			@Override
			protected List<TimeEntry> call() throws Exception {
				return performServiceTask();
			}
		};
	}
	
	private List<TimeEntry> performServiceTask() throws Exception {
		final List<TimeEntry> result = new ArrayList<>();

		final RestRequestWrapper wrapper = new RestRequestWrapper();

		int offset = 0;
		final int limit = 100;
		int total = limit;

		while (offset < total) {
			try {
				final TimeEntryResponse output = wrapper.performGet(TimeEntryResponse.class, "time_entries.json?issue_id=" + issueId + "&offset=" + offset + "&limit=" + limit);
				System.out.println(output);
				result.addAll(output.getTimeEntries());

				total = output.getTotalCount();
				offset += limit;

			} catch (final RestException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}