/**
 * 
 */
package eyeofsauron.coreutils.clusterer.semclusterer;

import java.util.List;

import org.carrot2.core.Cluster;
import org.carrot2.core.Controller;
import org.carrot2.core.ControllerFactory;
import org.carrot2.core.Document;
import org.carrot2.core.IClusteringAlgorithm;
import org.carrot2.core.ProcessingResult;

import eyeofsauron.coreutils.clusterer.ClustererArgument;
import eyeofsauron.coreutils.clusterer.ClustererData;
import eyeofsauron.coreutils.clusterer.ClustererResult;
import eyeofsauron.domain.utils.Utility;
import eyeofsauron.domain.utils.UtilityIdentity;
import eyeofsauron.helperutils.langguesser.LanguageGuesser;

//import eyeofsauron.domain.

/**
 * @author john
 *
 */
public class SemanticClustererImpl extends Utility<SemanticClustererConfig, ClustererArgument, ClustererResult> {

	private IClusteringAlgorithm algorithm;
	protected final static String utilityIdentifier = "Core Semantic Clusterer";
	protected final static double version = 0.1;
	private LanguageGuesser guesser;
	
	private final Controller controller;
	protected static SemanticClustererConfig defaultConfig;
	static{
		defaultConfig = new SemanticClustererConfig(SemanticClusteringAlgorithmEnum.LINGO);
	}
	
	public SemanticClustererImpl(){

		controller = ControllerFactory.createSimple();
		guesser = new LanguageGuesser();
		configure(defaultConfig);
	}


	@Override
	public boolean configure(SemanticClustererConfig config) {

		currentConfig = config;
		algorithm = currentConfig.getAlgorithm();
		
		return true;
	}

	@Override
	public ClustererResult process(ClustererArgument arg) {
		
		String keyword = arg.getKeyword();
		ClustererResult result = new ClustererResult("\""+keyword+"\" Clusters", "", getUtilityIdentity().toString());
		
		List<Document> docs = arg.getArgumentsByClass(Document.class);

//		List<String> strs = arg.getArgumentsByClass(String.class);
//		for (String str : strs)
//			docs.add(new Document(str));

		if (docs.isEmpty())	return null;
//for (Document d : docs)
//	System.out.println(d.getStringId()+" "+ d.getTitle());
		for (Document l : docs){
			l.setLanguage(guesser.proccess(l.getSummary()));
//			System.out.println(l.getLanguage());
		}	
		ProcessingResult pr =  controller.process(docs, keyword,  algorithm.getClass());
		List<Cluster> clusters = pr.getClusters();

		for (Cluster cl : clusters){
			ClustererData tempData = new ClustererData(cl, cl.getLabel());
			result.addData(tempData);
		}
		return result;
	}

	@Override
	public UtilityIdentity getUtilityIdentity() {
		return new UtilityIdentity(utilityIdentifier, version);
	}


}
