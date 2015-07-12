package eu.over9000.redtrack.data;

/**
 * Created by Jan on 12.07.2015.
 */
public class Id {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Id{" +
				"id=" + id +
				'}';
	}
}
