/**
 * 
 */
package eyeofsauron.coreutils.sentimentanalysis;

import java.util.Collection;

import org.carrot2.core.Cluster;
import org.carrot2.core.Document;

import eyeofsauron.coreutils.clusterer.ClustererData;
import eyeofsauron.coreutils.clusterer.ClustererResult;
import eyeofsauron.coreutils.searchengine.SearchEngineData;
import eyeofsauron.coreutils.searchengine.SearchEngineResult;
import eyeofsauron.domain.utils.arguments.UtilityArgument;
import eyeofsauron.domain.utils.arguments.UtilityArgumentException;

/**
 * @author john
 *
 */
public class SentimentAnalysisArgument extends UtilityArgument {

	protected void setValidArgumentClasses(){
		this.addValidArgumentClass(String.class);
		this.addValidArgumentClass(Document.class);
		this.addValidArgumentClass(Cluster.class);
		this.addValidArgumentClass(ClustererResult.class);
	}

	/**
	 * 
	 */
	public SentimentAnalysisArgument() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param obj
	 * @throws UtilityArgumentException
	 */
	public SentimentAnalysisArgument(Object obj) throws UtilityArgumentException {
		super(obj);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param c
	 * @throws UtilityArgumentException
	 */
	public SentimentAnalysisArgument(Collection<Object> c) throws UtilityArgumentException {
		super(c);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see eyeofsauron.domain.utils.arguments.UtilityArgument#addArgument(java.lang.Object)
	 */
	@Override
	public void addArgument(Object arg) throws UtilityArgumentException {

		if (!arg.getClass().equals(ClustererResult.class)){
			super.addArgument(arg);
			return;
		}
		ClustererResult res = (ClustererResult) arg;
		for (ClustererData d : res)
			super.addArgument(d.getData());
		
	}



}
