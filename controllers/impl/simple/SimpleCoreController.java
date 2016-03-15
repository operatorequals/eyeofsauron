package eyeofsauron.controllers.impl.simple;


import com.cybozu.labs.langdetect.LangDetectException;

import eyeofsauron.controllers.CoreArgument;
import eyeofsauron.controllers.CoreController;
import eyeofsauron.controllers.CoreData;
import eyeofsauron.controllers.CoreResult;
import eyeofsauron.coreutils.clusterer.ClustererArgument;
import eyeofsauron.coreutils.clusterer.ClustererData;
import eyeofsauron.coreutils.clusterer.ClustererResult;
import eyeofsauron.coreutils.clusterer.semclusterer.SemanticClustererImpl;
import eyeofsauron.coreutils.searchengine.MetaSearchEngine;
import eyeofsauron.coreutils.searchengine.MetaSearchEngineImpl;
import eyeofsauron.coreutils.searchengine.SearchEngineArgument;
import eyeofsauron.coreutils.searchengine.SearchEngineResult;
import eyeofsauron.coreutils.searchengine.StubSearchEngineImpl;
import eyeofsauron.coreutils.sentimentanalysis.SentimentAnalysisArgument;
import eyeofsauron.coreutils.sentimentanalysis.SentimentAnalysisResult;
import eyeofsauron.coreutils.sentimentanalysis.SentimentAnalyzerImpl;
import eyeofsauron.domain.utils.UtilityIdentity;
import eyeofsauron.domain.utils.arguments.UtilityArgumentException;

public class SimpleCoreController extends CoreController<SimpleCoreConfig, CoreArgument, CoreResult>{

	MetaSearchEngine searchEngine;
	SemanticClustererImpl clusterer;
	SentimentAnalyzerImpl  analyzer;
	
	public SimpleCoreController() throws Exception{
		
//		searchEngine = new 	StubSearchEngineImpl ();	// NO INTERNET CONNECTION STUB
		searchEngine = new 	MetaSearchEngineImpl ();
		clusterer = new SemanticClustererImpl();
		analyzer = new SentimentAnalyzerImpl();
	}
	
	@Override
	public boolean configure(SimpleCoreConfig config) {
		searchEngine.configure(config.getSearchConfig());
		clusterer.configure(config.getSemClConfig());
		analyzer.configure(config.getSentConfig());
		return true;
	}

	@Override
	public CoreResult process(CoreArgument arg) throws UtilityArgumentException {
		
		
		String query = arg.getQuery();
		CoreResult result = new CoreResult("Core Results", "Results by keyword", query);
		
		SearchEngineResult search = searchEngine.process(new SearchEngineArgument(query));
		
		ClustererArgument clArg = new ClustererArgument();
		clArg.addArgument(search);
		ClustererResult clResult = clusterer.process(clArg);

		SentimentAnalysisResult anResult ;
		for (ClustererData clData:clResult)	{
			anResult = analyzer.process(new SentimentAnalysisArgument(clData.getData()));
			result.addData(new CoreData( anResult, "SentAnalysis of Cluster: "+clData.getData().getLabel()));
		}

		return result;
	}

	@Override
	public UtilityIdentity getUtilityIdentity() {
		// TODO Auto-generated method stub
		return null;
	}

}
