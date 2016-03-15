/**
 * 
 */
package eyeofsauron.coreutils.searchengine;

import eyeofsauron.domain.utils.arguments.UtilityArgument;
import eyeofsauron.domain.utils.arguments.UtilityArgumentException;

/**
 * @author john
 *
 */
public class SearchEngineArgument extends UtilityArgument {

	/* (non-Javadoc)
	 * @see eyeofsauron.domain.utils.arguments.UtilityArgument#setValidArgumentClasses()
	 */
	@Override
	protected void setValidArgumentClasses() {
		// TODO Auto-generated method stub
		this.addValidArgumentClass(String.class);
	}

	public SearchEngineArgument(String query) throws UtilityArgumentException{
		
		this.addArgument(query);
	}
}
