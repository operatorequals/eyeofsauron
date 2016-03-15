package eyeofsauron.coreutils.searchengine.bing;


import java.util.Map;

import org.carrot2.source.microsoft.Bing3DocumentSourceDescriptor;
import org.carrot2.source.microsoft.MarketOption;
import org.carrot2.source.microsoft.SourceType;

import eyeofsauron.coreutils.searchengine.SearchEngineConfig;

public class BingSearchConfig extends SearchEngineConfig {

	public BingSearchConfig(String apiKey, MarketOption market) {
		attributes.put(Bing3DocumentSourceDescriptor.Keys.APPID, apiKey);
		attributes.put(Bing3DocumentSourceDescriptor.Keys.MARKET, market);
	}



}
