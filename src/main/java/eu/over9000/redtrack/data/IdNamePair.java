package eu.over9000.redtrack.data;

/**
 * Created by Jan on 12.07.2015.
 */
public class IdNamePair {
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "IdNamePair{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
