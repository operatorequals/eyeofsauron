/**
 * 
 */
package eyeofsauron.domain.plugins;

import java.net.URL;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eyeofsauron.domain.utils.arguments.UtilityArgument;

/**
 * @author john
 *
 */
public abstract class PluginGenericArgument extends UtilityArgument {

	/* (non-Javadoc)
	 * @see eyeofsauron.domain.utils.arguments.UtilityArgument#setValidArgumentClasses()
	 */
	protected Set<Pattern> urlPatterns;

	
	@Override
	protected void setValidArgumentClasses() {

		this.addValidArgumentClass(URL.class);
//		this.addValidArgumentClass(URL[].class);
		
	}


	/* (non-Javadoc)
	 * @see eyeofsauron.domain.utils.arguments.UtilityArgument#isValidArgument(java.lang.Object)
	 */
	@Override
	protected boolean isValidArgument(Object arg) {


		if (!super.isValidArgument(arg))	return false;
		
		Matcher matcher;
		for (Pattern p : urlPatterns){
			matcher = p.matcher(arg.toString());
			if (matcher.matches())	return true;
		}
		
		return false;
	}
	
	
	protected void addUrlPattern(String regex){
		
		Pattern p = Pattern.compile(regex);
		urlPatterns.add(p);
	}
	
}
