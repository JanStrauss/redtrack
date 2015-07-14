package eu.over9000.redtrack.data.timeentry;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * TimeEntry POST JSON object
 */
public class TimeEntryPost {

	private Integer issueId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDate spendOn;
	private Float hours;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer activityId;
	private String comments;

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(final Integer activityId) {
		this.activityId = activityId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

	public Float getHours() {
		return hours;
	}

	public void setHours(final Float hours) {
		this.hours = hours;
	}

	public Integer getIssueId() {
		return issueId;
	}

	public void setIssueId(final Integer issueId) {
		this.issueId = issueId;
	}

	public LocalDate getSpendOn() {
		return spendOn;
	}

	public void setSpendOn(final LocalDate spendOn) {
		this.spendOn = spendOn;
	}
}
