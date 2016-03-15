package eyeofsauron.controllers.impl.simple;

import java.io.IOException;

import org.carrot2.core.LanguageCode;
import org.carrot2.source.microsoft.MarketOption;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import eyeofsauron.controllers.CoreConfig;
import eyeofsauron.coreutils.clusterer.semclusterer.SemanticClustererConfig;
import eyeofsauron.coreutils.clusterer.semclusterer.SemanticClusteringAlgorithmEnum;
import eyeofsauron.coreutils.searchengine.MetaSearchEngineConfig;
import eyeofsauron.coreutils.searchengine.bing.BingSearchConfig;
import eyeofsauron.coreutils.sentimentanalysis.SentimentAnalysisConfig;

public class SimpleCoreConfig extends CoreConfig{


	private MetaSearchEngineConfig searchConfig;
	private SentimentAnalysisConfig sentConfig;
	private SemanticClustererConfig semClConfig;

	public static SimpleCoreConfig defaultConfig(){
		return new SimpleCoreConfig ("etTFY9Kp+pkKA/oD/m1aaSkNcHpBwqElGgeM2IaC4+8", "greek", "greece", "STC");
	}
	
	public  String getPojoTemplate(){
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, true);
		mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
		mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		String ret = null;
		try {
			SimpleCoreConfigPOJO pojo = new SimpleCoreConfigPOJO();
			ret = mapper.writeValueAsString(pojo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public SimpleCoreConfig(String bingApiKey, String lang, String country, String clAlgorithm){
		String carrotLocale = (lang+"_"+country.replace(" ", "_")).toUpperCase();
		MarketOption market = MarketOption.valueOf(carrotLocale);
		SemanticClusteringAlgorithmEnum algorithm = SemanticClusteringAlgorithmEnum.valueOf(clAlgorithm.toUpperCase());
		
		BingSearchConfig bing = new BingSearchConfig(bingApiKey, market);
		searchConfig = new MetaSearchEngineConfig(36, bing, null);
		semClConfig = new SemanticClustererConfig(algorithm);
		LanguageCode langCode = LanguageCode.valueOf(lang.toUpperCase());
		sentConfig = new SentimentAnalysisConfig(langCode);
	}


	public SimpleCoreConfig(String json) throws JsonParseException, JsonMappingException, IOException{
		
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, true);

		SimpleCoreConfigPOJO pojo =  mapper.readValue(json, SimpleCoreConfigPOJO.class);

		//		this.set
		String lang = pojo.getLanguage();
		String country = pojo.getCountry();
		String bingApiKey = pojo.getApiKey();
		String clAlgorithm = pojo.getAlgorithm();
		int results = pojo.getResults();
		
		
		String carrotLocale = (lang+"_"+country.replace(" ", "_")).toUpperCase();
		MarketOption market = MarketOption.valueOf(carrotLocale);
		SemanticClusteringAlgorithmEnum algorithm = SemanticClusteringAlgorithmEnum.valueOf(clAlgorithm.toUpperCase());
		
		BingSearchConfig bing = new BingSearchConfig(bingApiKey, market);
		searchConfig = new MetaSearchEngineConfig(results, bing, null);
		semClConfig = new SemanticClustererConfig(algorithm);
		LanguageCode langCode = LanguageCode.valueOf(lang.toUpperCase());
		sentConfig = new SentimentAnalysisConfig(langCode);
	}
	
	protected SimpleCoreConfig(SimpleCoreConfigPOJO pojo){
		this(pojo.getApiKey(),pojo.getLanguage(), pojo.getCountry(), pojo.getAlgorithm());
	}
	/**
	 * @return the searchConfig
	 */
	public MetaSearchEngineConfig getSearchConfig() {
		return searchConfig;
	}

	/**
	 * @param searchConfig the searchConfig to set
	 */
	public void setSearchConfig(MetaSearchEngineConfig searchConfig) {
		this.searchConfig = searchConfig;
	}

	/**
	 * @return the sentConfig
	 */
	public SentimentAnalysisConfig getSentConfig() {
		return sentConfig;
	}

	/**
	 * @param sentConfig the sentConfig to set
	 */
	public void setSentConfig(SentimentAnalysisConfig sentConfig) {
		this.sentConfig = sentConfig;
	}

	/**
	 * @return the semClConfig
	 */
	public SemanticClustererConfig getSemClConfig() {
		return semClConfig;
	}

	/**
	 * @param semClConfig the semClConfig to set
	 */
	public void setSemClConfig(SemanticClustererConfig semClConfig) {
		this.semClConfig = semClConfig;
	}

}
