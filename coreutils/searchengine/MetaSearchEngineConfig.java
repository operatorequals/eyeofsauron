/**
 * 
 */
package eyeofsauron.coreutils.searchengine;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.carrot2.core.DocumentSourceDescriptor;
import org.carrot2.source.SearchEngineBaseDescriptor;
import org.carrot2.source.microsoft.Bing3DocumentSourceDescriptor;
import org.carrot2.source.microsoft.MarketOption;
import org.carrot2.source.microsoft.SourceType;

import eyeofsauron.coreutils.searchengine.bing.BingSearchConfig;
import eyeofsauron.coreutils.searchengine.bing.GoogleSearchConfig;
import eyeofsauron.domain.utils.config.UtilityConfig;

/**
 * @author john
 *
 */
public class MetaSearchEngineConfig extends UtilityConfig {

	protected MetaSearchEngineConfig(){}

	
	protected static BingSearchConfig defaultBingConfig ;

	static{
		defaultBingConfig  = new BingSearchConfig("etTFY9Kp+pkKA/oD/m1aaSkNcHpBwqElGgeM2IaC4+8", MarketOption.ENGLISH_UNITED_STATES);
//		defaultBingConfig  = new BingSearchConfig("etTFY9Kp+pkKA/oD/m1aaSkNcHpBwqElGgeM2IaC4+8", MarketOption.GREEK_GREECE);
	}
	
	protected static MetaSearchEngineConfig getDefaultConfig(){
		
		return new MetaSearchEngineConfig(36, defaultBingConfig, null);
	}

	protected Map<String, Object> commonAttributes = new HashMap<String, Object>();
	
	private Map<SearchEngineEnum, SearchEngineConfig> engineConfigs = new HashMap<SearchEngineEnum, SearchEngineConfig>();


	public MetaSearchEngineConfig(int results){	//TODO
	
		this(results, defaultBingConfig, null);
	}
	
	public MetaSearchEngineConfig(int results, BingSearchConfig b, GoogleSearchConfig g){	//TODO
//		bing = b;
		this.setResultTotal(results);
		if (b != null)
			engineConfigs.put(SearchEngineEnum.BING , b);
		if (g != null)
			engineConfigs.put(SearchEngineEnum.GOOGLE , g);
		
	}
	
	
	protected Set<SearchEngineEnum> getSearchEngineClasses(){
		return engineConfigs.keySet();
	}
	


	
	public int getResultTotal() {
		return (int) commonAttributes.get( SearchEngineBaseDescriptor.Keys.RESULTS);
	}

	
	public void setResultTotal(int val) {
		commonAttributes.put( SearchEngineBaseDescriptor.Keys.RESULTS, val);
	}

	
	public void setQuery(String val) {
		commonAttributes.put( SearchEngineBaseDescriptor.Keys.QUERY, val);
	}

	public Map<String, Object> getAttributeMap(SearchEngineEnum eng){
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.putAll(commonAttributes);
		SearchEngineConfig tempConf = engineConfigs.get(eng);
		ret.putAll(tempConf.getAttributeMap());
		
		return ret;
		
		
	}

}
