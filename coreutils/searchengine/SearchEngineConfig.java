/**
 * 
 */
package eyeofsauron.coreutils.searchengine;

import java.util.HashMap;
import java.util.Map;

/**
 * @author john
 *
 */
public abstract class SearchEngineConfig {

	public Map<String, Object> getAttributeMap(){
		return attributes;
	}
	
	protected Map<String, Object> attributes = new HashMap<String, Object>();
}
