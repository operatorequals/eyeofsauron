package eyeofsauron.domain.results;

import java.io.Serializable;

public abstract class AnalysisData<T> implements Serializable
{

	protected T anData;
	protected String description;


	/**
	 * @param anData
	 * @param description
	 */
	public AnalysisData(T anData,  String description) {
		this.anData = anData;
		this.description = description;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return anData;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.anData = data;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AnalysisData data=");
		builder.append(anData);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}
