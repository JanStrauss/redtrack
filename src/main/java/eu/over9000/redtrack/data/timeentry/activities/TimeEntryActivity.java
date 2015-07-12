package eu.over9000.redtrack.data.timeentry.activities;

/**
 * Created by Jan on 12.07.2015.
 */
public class TimeEntryActivity {
	private Integer id;
	private String name;
	private Boolean isDefault;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(final Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TimeEntryActivity{" +
				"id=" + id +
				", name='" + name + '\'' +
				", isDefault=" + isDefault +
				'}';
	}
}
