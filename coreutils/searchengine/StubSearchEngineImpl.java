/**
 * 
 */
package eyeofsauron.coreutils.searchengine;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
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
public class StubSearchEngineImpl extends MetaSearchEngine{

	protected Set<SearchEngineEnum> engines;
	private Controller controller;
	protected final static String utilityIdentifier = "Stub Meta-Search Engine";
	protected final static double version = 0.1;

	
//	private MetaSearchEngineConfig currentConfig;
	private static MetaSearchEngineConfig defaultConfig;
	static {
		defaultConfig = MetaSearchEngineConfig.getDefaultConfig();
		//		defaultConfig = new SearchEngineConfig (config1, null);
	
	}
	

	public StubSearchEngineImpl(){

	}

	@Override
	public boolean configure(MetaSearchEngineConfig config) {
		return true;
	}


	@Override
	public SearchEngineResult process(SearchEngineArgument arg) {

		SearchEngineResult ret = null;
		InputStream file;
		try {
			file = new FileInputStream("stub_results.ser");
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream (buffer);
			ret = (SearchEngineResult) input.readObject();
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
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
}
