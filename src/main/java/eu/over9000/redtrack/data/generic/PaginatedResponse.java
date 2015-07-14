package eu.over9000.redtrack.data.generic;

/**
 * super class for paginated responses
 */
public abstract class PaginatedResponse {
	protected int totalCount;
	protected int offset;
	protected int limit;

	@Override
	public String toString() {
		return "PaginatedResponse{" +
				"limit=" + limit +
				", totalCount=" + totalCount +
				", offset=" + offset +
				'}';
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
