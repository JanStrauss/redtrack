package eu.over9000.redtrack.data.generic;

/**
 * Id/Name JSON object
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
