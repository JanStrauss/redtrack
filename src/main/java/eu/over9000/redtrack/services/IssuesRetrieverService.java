package eu.over9000.redtrack.services;

import java.util.ArrayList;
import java.util.List;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import eu.over9000.redtrack.data.Issue;
import eu.over9000.redtrack.data.IssueResponse;
import eu.over9000.redtrack.persistence.Configuration;
import eu.over9000.redtrack.rest.RestException;
import eu.over9000.redtrack.rest.RestRequestWrapper;

/**
 * Service to retrieve the open issues for the current user
 */
public class IssuesRetrieverService extends Service<List<Issue>> {

	@Override
	protected Task<List<Issue>> createTask() {
		return new Task<List<Issue>>() {
			@Override
			protected List<Issue> call() throws Exception {
				return performServiceTask();
			}
		};
	}

	private static List<Issue> performServiceTask() throws Exception {
		final List<Issue> result = new ArrayList<>();

		RestRequestWrapper wrapper = new RestRequestWrapper();

		int offset = 0;
		int limit = 100;
		int total = limit;

		while (offset < total) {
			try {
				IssueResponse output = wrapper.performRequest(IssueResponse.class, "issues.json?status_id=open&assigned_to_id=me&offset=" + offset + "&limit=" + limit);
				System.out.println(output);
				result.addAll(output.getIssues());

				total = output.getTotalCount();
				offset += limit;

			} catch (RestException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public static void main(String[] args) throws Exception {
		Configuration.load();

		performServiceTask();
	}
}
