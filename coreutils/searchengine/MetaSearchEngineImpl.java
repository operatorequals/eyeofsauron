/**
 * 
 */
package eyeofsauron.coreutils.searchengine;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;

import org.carrot2.core.Controller;
import org.carrot2.core.ControllerFactory;
import org.carrot2.core.Document;
import org.carrot2.core.ProcessingResult;
import org.carrot2.source.SearchEngineBase;
import org.carrot2.source.etools.EToolsDocumentSource;
import org.carrot2.source.google.GoogleDocumentSource;
import org.carrot2.source.microsoft.Bing3DocumentSource;
import org.carrot2.source.microsoft.Bing3DocumentSourceDescriptor;
import org.carrot2.source.microsoft.Bing3WebDocumentSource;
import org.carrot2.source.microsoft.MarketOption;


import eyeofsauron.domain.utils.Utility;
import eyeofsauron.domain.utils.UtilityIdentity;




/**
 * @author john
 *
 */
public class MetaSearchEngineImpl extends MetaSearchEngine{

	protected Set<SearchEngineEnum> engines;
	private Controller controller;
	protected final static String utilityIdentifier = "Core Meta-Search Engine";
	protected final static double version = 0.1;

	
//	private MetaSearchEngineConfig currentConfig;
	private static MetaSearchEngineConfig defaultConfig;
	static {
		defaultConfig = MetaSearchEngineConfig.getDefaultConfig();
		//		defaultConfig = new SearchEngineConfig (config1, null);
	
	}
	

	public MetaSearchEngineImpl(){
		controller = ControllerFactory.createSimple();
		configure(defaultConfig);
	}

	@Override
	public boolean configure(MetaSearchEngineConfig config) {
		currentConfig = config;

		engines = currentConfig.getSearchEngineClasses();
		return true;
	}


	@Override
	public SearchEngineResult process(SearchEngineArgument arg) {
		String keyword = "";
		for (Object s : arg)
			keyword += s+" | ";
		keyword = keyword.substring(0, keyword.length()-3);

		SearchEngineResult result = new SearchEngineResult("Search Results", keyword, getUtilityIdentity().toString());
		List <Document> docs = new ArrayList<Document>();
		currentConfig.setQuery(keyword);
		
		for (SearchEngineEnum engine: engines){
			docs.addAll( searchWithEngine(engine));
		}
		
		for (Document doc : docs)
			result.addData(new SearchEngineData(doc,""));
		serializeResults(result);
		return result;
	}


	protected List<Document> searchWithEngine(SearchEngineEnum s){
		Map<String, Object> attributes = currentConfig.getAttributeMap(s);
		if (attributes == null)	return new ArrayList<Document>();
System.out.println(attributes);		
		ProcessingResult pr = controller.process(attributes, s.getSearchEngineClass());
		
		return pr.getDocuments();
	}
	

	@Override
	public UtilityIdentity getUtilityIdentity() {
		return new UtilityIdentity(utilityIdentifier, version);
	}
	
	
	private void serializeResults(SearchEngineResult res){
		
		try{
	      OutputStream file = new FileOutputStream("stub_results.ser");
	      OutputStream buffer = new BufferedOutputStream(file);
	      ObjectOutput output = new ObjectOutputStream(buffer);
	      output.writeObject(res);
	      output.close();
		}catch(Exception e){
		
			System.err.println("not serialized: "+e);
			
		}
	}
}
