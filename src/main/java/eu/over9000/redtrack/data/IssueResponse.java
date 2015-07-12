package eu.over9000.redtrack.data;

import java.util.List;

/**
 * Created by Jan on 12.07.2015.
 */
public class IssueResponse {
	private List<Issue> issues;
	private int totalCount;
	private int offset;
	private int limit;

	@Override
	public String toString() {
		return "IssueResponse{" +
				"issues=" + issues +
				", totalCount=" + totalCount +
				", offset=" + offset +
				", limit=" + limit +
				'}';
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(final List<Issue> issues) {
		this.issues = issues;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(final int totalCount) {
		this.totalCount = totalCount;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(final int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(final int limit) {
		this.limit = limit;
	}
}
