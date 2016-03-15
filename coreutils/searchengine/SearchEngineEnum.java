/**
 * 
 */
package eyeofsauron.coreutils.searchengine;

import org.carrot2.source.SearchEngineBase;
import org.carrot2.source.google.GoogleDocumentSource;
import org.carrot2.source.microsoft.Bing3DocumentSource;
import org.carrot2.source.microsoft.Bing3NewsDocumentSource;
import org.carrot2.source.microsoft.Bing3WebDocumentSource;

/**
 * @author john
 *
 */
public enum SearchEngineEnum {

	GOOGLE,
	BING,
	BING_NEWS,
//	BING_IMAGES,
	OPEN_SEARCH,
	ETOOLS;


	public Class<? extends SearchEngineBase> getSearchEngineClass(){
		
		switch(this){
		case GOOGLE:
			return GoogleDocumentSource.class;
		case BING:
			return Bing3WebDocumentSource.class;
		case BING_NEWS:
			return Bing3NewsDocumentSource.class;
			
		// TODO
		}
		return null;
	}
}
