package eu.over9000.redtrack.data.issue;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import eu.over9000.redtrack.data.generic.Id;
import eu.over9000.redtrack.data.generic.IdNamePair;

/**
 * Created by Jan on 12.07.2015.
 */
public class Issue {

	private Integer id;

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
	private Integer doneRatio;
	private Integer estimatedHours;
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

	public IdNamePair getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(final IdNamePair assignedTo) {
		this.assignedTo = assignedTo;
	}

	public IdNamePair getAuthor() {
		return author;
	}

	public void setAuthor(final IdNamePair author) {
		this.author = author;
	}

	public IdNamePair getCategory() {
		return category;
	}

	public void setCategory(final IdNamePair category) {
		this.category = category;
	}

	public ZonedDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(final ZonedDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Integer getDoneRatio() {
		return doneRatio;
	}

	public void setDoneRatio(final Integer doneRatio) {
		this.doneRatio = doneRatio;
	}

	public Integer getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(final Integer estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Id getParent() {
		return parent;
	}

	public void setParent(final Id parent) {
		this.parent = parent;
	}

	public IdNamePair getPriority() {
		return priority;
	}

	public void setPriority(final IdNamePair priority) {
		this.priority = priority;
	}

	public IdNamePair getProject() {
		return project;
	}

	public void setProject(final IdNamePair project) {
		this.project = project;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(final LocalDate startDate) {
		this.startDate = startDate;
	}

	public IdNamePair getStatus() {
		return status;
	}

	public void setStatus(final IdNamePair status) {
		this.status = status;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public IdNamePair getTracker() {
		return tracker;
	}

	public void setTracker(final IdNamePair tracker) {
		this.tracker = tracker;
	}

	public ZonedDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(final ZonedDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
}