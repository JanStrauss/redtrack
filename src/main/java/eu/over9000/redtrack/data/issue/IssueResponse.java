package eu.over9000.redtrack.data.issue;

import java.util.List;

import eu.over9000.redtrack.data.generic.PaginatedResponse;

/**
 * IssueResponse JSON object
 */
public class IssueResponse extends PaginatedResponse {
	private List<Issue> issues;

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(final List<Issue> issues) {
		this.issues = issues;
	}

	@Override
	public String toString() {
		return "IssueResponse{" +
				"issues=" + issues +
				"} " + super.toString();
	}
}