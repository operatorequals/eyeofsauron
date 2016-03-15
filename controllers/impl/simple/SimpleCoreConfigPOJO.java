package eyeofsauron.controllers.impl.simple;


class SimpleCoreConfigPOJO{
	
	String language, country, apiKey, algorithm;
	int results;
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the apiKey
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @param apiKey the apiKey to set
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * @return the algorithm
	 */
	public String getAlgorithm() {
		return algorithm;
	}

	/**
	 * @param algorithm the algorithm to set
	 */
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * @return the results
	 */
	public int getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(int results) {
		if (results < 1)throw new IllegalArgumentException("Search Results should be more than 1");
		this.results = results;
	}
}