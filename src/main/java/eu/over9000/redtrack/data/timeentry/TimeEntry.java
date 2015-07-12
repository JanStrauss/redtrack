package eu.over9000.redtrack.data.timeentry;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import eu.over9000.redtrack.data.generic.Id;
import eu.over9000.redtrack.data.generic.IdNamePair;

/**
 * Created by Jan on 12.07.2015.
 */
public class TimeEntry {

	private Integer id;
	private IdNamePair project;
	private Id issue;
	private IdNamePair user;
	private IdNamePair activity;
	private Float hours;
	private String comments;
	private LocalDate spendOn;
	private ZonedDateTime createdOn;
	private ZonedDateTime updatedOn;

	public IdNamePair getActivity() {
		return activity;
	}

	public void setActivity(final IdNamePair activity) {
		this.activity = activity;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

	public ZonedDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(final ZonedDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public Float getHours() {
		return hours;
	}

	public void setHours(final Float hours) {
		this.hours = hours;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Id getIssue() {
		return issue;
	}

	public void setIssue(final Id issue) {
		this.issue = issue;
	}

	public IdNamePair getProject() {
		return project;
	}

	public void setProject(final IdNamePair project) {
		this.project = project;
	}

	public LocalDate getSpendOn() {
		return spendOn;
	}

	public void setSpendOn(final LocalDate spendOn) {
		this.spendOn = spendOn;
	}

	public ZonedDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(final ZonedDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public IdNamePair getUser() {
		return user;
	}

	public void setUser(final IdNamePair user) {
		this.user = user;
	}
}

