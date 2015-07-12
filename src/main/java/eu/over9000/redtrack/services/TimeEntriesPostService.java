package eu.over9000.redtrack.services;

import java.util.HashMap;
import java.util.Map;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import eu.over9000.redtrack.data.timeentry.TimeEntryPost;
import eu.over9000.redtrack.rest.RestException;
import eu.over9000.redtrack.rest.RestRequestWrapper;

/**
 * Created by Jan on 12.07.2015.
 */
public class TimeEntriesPostService extends Service<Boolean> {

	private final TimeEntryPost timeEntry;

	public TimeEntriesPostService(final TimeEntryPost timeEntry) {
		this.timeEntry = timeEntry;
	}
	
	@Override
	protected Task<Boolean> createTask() {
		return new Task<Boolean>() {
			@Override
			protected Boolean call() throws Exception {
				return performServiceTask();
			}
		};
	}
	
	private Boolean performServiceTask() throws Exception {

		final RestRequestWrapper wrapper = new RestRequestWrapper();

		try {
			final Map<String, TimeEntryPost> jsonContainer = new HashMap<>();
			jsonContainer.put("time_entry", timeEntry);
			wrapper.performPost(String.class, "time_entries.json", jsonContainer);

		} catch (final RestException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}