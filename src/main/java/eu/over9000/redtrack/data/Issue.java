package eu.over9000.redtrack.data;

import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * Created by Jan on 12.07.2015.
 */
public class Issue {

	private int id;

	private IdNamePair project;
	private IdNamePair tracker;
	private IdNamePair status;
	private IdNamePair priority;
	private IdNamePair author;
	private IdNamePair assignedTo;
	private IdNamePair category;

	private Id parent;
	private String subject;
	private String description;
	private int doneRatio;
	private int estimatedHours;
	private LocalDate startDate;
	private ZonedDateTime createdOn;
	private ZonedDateTime updatedOn;

	@Override
	public String toString() {
		return "Issue{" +
				"id=" + id +
				", project=" + project +
				", tracker=" + tracker +
				", status=" + status +
				", priority=" + priority +
				", author=" + author +
				", assignedTo=" + assignedTo +
				", category=" + category +
				", parent=" + parent +
				", subject='" + subject + '\'' +
				", description='" + description + '\'' +
				", doneRatio=" + doneRatio +
				", estimatedHours=" + estimatedHours +
				", startDate=" + startDate +
				", createdOn=" + createdOn +
				", updatedOn=" + updatedOn +
				'}';
	}

	public IdNamePair getCategory() {
		return category;
	}

	public void setCategory(final IdNamePair category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public IdNamePair getProject() {
		return project;
	}

	public void setProject(final IdNamePair project) {
		this.project = project;
	}

	public IdNamePair getTracker() {
		return tracker;
	}

	public void setTracker(final IdNamePair tracker) {
		this.tracker = tracker;
	}

	public IdNamePair getStatus() {
		return status;
	}

	public void setStatus(final IdNamePair status) {
		this.status = status;
	}

	public IdNamePair getPriority() {
		return priority;
	}

	public void setPriority(final IdNamePair priority) {
		this.priority = priority;
	}

	public IdNamePair getAuthor() {
		return author;
	}

	public void setAuthor(final IdNamePair author) {
		this.author = author;
	}

	public IdNamePair getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(final IdNamePair assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Id getParent() {
		return parent;
	}

	public void setParent(final Id parent) {
		this.parent = parent;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public int getDoneRatio() {
		return doneRatio;
	}

	public void setDoneRatio(final int doneRatio) {
		this.doneRatio = doneRatio;
	}

	public int getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(final int estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(final LocalDate startDate) {
		this.startDate = startDate;
	}

	public ZonedDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(final ZonedDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public ZonedDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(final ZonedDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
}