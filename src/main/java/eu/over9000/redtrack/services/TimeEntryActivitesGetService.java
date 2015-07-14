package eu.over9000.redtrack.services;

import java.util.List;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import eu.over9000.redtrack.data.timeentry.activities.TimeEntryActivitiesResponse;
import eu.over9000.redtrack.data.timeentry.activities.TimeEntryActivity;
import eu.over9000.redtrack.rest.RestException;
import eu.over9000.redtrack.rest.RestRequestWrapper;

/**
 * Service to retrieve the list of activities
 */
public class TimeEntryActivitesGetService extends Service<List<TimeEntryActivity>> {

	@Override
	protected Task<List<TimeEntryActivity>> createTask() {
		return new Task<List<TimeEntryActivity>>() {
			@Override
			protected List<TimeEntryActivity> call() throws Exception {
				return performServiceTask();
			}
		};
	}
	
	private List<TimeEntryActivity> performServiceTask() throws Exception {

		final RestRequestWrapper wrapper = new RestRequestWrapper();


		try {
			final TimeEntryActivitiesResponse output = wrapper.performGet(TimeEntryActivitiesResponse.class, "enumerations/time_entry_activities.json");
			System.out.println(output);

			return output.getTimeEntryActivities();
		} catch (final RestException e) {
			throw new Exception(e);
		}
	}

}