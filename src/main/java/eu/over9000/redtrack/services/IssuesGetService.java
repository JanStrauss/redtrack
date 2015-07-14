package eu.over9000.redtrack.services;

import java.util.ArrayList;
import java.util.List;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import eu.over9000.redtrack.data.issue.Issue;
import eu.over9000.redtrack.data.issue.IssueResponse;
import eu.over9000.redtrack.persistence.Configuration;
import eu.over9000.redtrack.rest.RestException;
import eu.over9000.redtrack.rest.RestRequestWrapper;

/**
 * Service to retrieve the open issues
 */
public class IssuesGetService extends Service<List<Issue>> {

	@Override
	protected Task<List<Issue>> createTask() {
		return new Task<List<Issue>>() {
			@Override
			protected List<Issue> call() throws Exception {
				return performServiceTask();
			}
		};
	}

	private List<Issue> performServiceTask() throws Exception {
		final List<Issue> result = new ArrayList<>();

		final RestRequestWrapper wrapper = new RestRequestWrapper();

		final int limit = 100;
		int offset = 0;
		int total = limit;

		String baseResource = "issues.json?status_id=open";

		if (Boolean.parseBoolean(Configuration.getValue("assigned_only"))) {
			baseResource = baseResource + "&assigned_to_id=me";
		}

		try {
			while (offset < total) {

				final IssueResponse output = wrapper.performGet(IssueResponse.class, baseResource + "&offset=" + offset + "&limit=" + limit);

				System.out.println(output);
				result.addAll(output.getIssues());

				total = output.getTotalCount();
				offset += limit;

			}
		} catch (final RestException e) {
			e.printStackTrace();
		}

		return result;
	}
}
