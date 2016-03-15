/**
 * 
 */
package eyeofsauron.domain.utils.config;

import java.io.IOException;
import java.util.Iterator;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/**
 * @author john
 *
 */
public abstract class UtilityConfig {

	public String toJson(){
		
		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, true);
		mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
		mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		String ret = null;
		try {
			ret = mapper.writeValueAsString(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	

}
