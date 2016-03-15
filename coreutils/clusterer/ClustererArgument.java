/**
 * 
 */
package eyeofsauron.coreutils.clusterer;

import java.util.Collection;
import java.util.List;

import org.carrot2.core.Document;

import eyeofsauron.coreutils.searchengine.SearchEngineData;
import eyeofsauron.coreutils.searchengine.SearchEngineResult;
import eyeofsauron.domain.utils.arguments.UtilityArgument;
import eyeofsauron.domain.utils.arguments.UtilityArgumentException;

/**
 * @author john
 *
 */
public class ClustererArgument extends UtilityArgument {

	/* (non-Javadoc)
	 * @see eyeofsauron.domain.utils.arguments.UtilityArgument#setValidArgumentClasses()
	 */
	@Override
	protected void setValidArgumentClasses() {

		this.addValidArgumentClass(Document.class);
		this.addValidArgumentClass(String.class);
		this.addValidArgumentClass(SearchEngineResult.class);
	}

	/**
	 * 
	 */

	public String getKeyword(){
		List<String> strs = this.getArgumentsByClass(String.class);
		if (strs.isEmpty())	return "";
		else
			return strs.get(0);
	}

	/* (non-Javadoc)
	 * @see eyeofsauron.domain.utils.arguments.UtilityArgument#addArgument(java.lang.Object)
	 */
	@Override
	public void addArgument(Object arg) throws UtilityArgumentException {

		if (!arg.getClass().equals(SearchEngineResult.class))
			super.addArgument(arg);

		SearchEngineResult res = (SearchEngineResult) arg;
		for (SearchEngineData d : res)
			super.addArgument(d.getData());
		super.addArgument(res.getDescription());	// the keyword
		
	}
}
