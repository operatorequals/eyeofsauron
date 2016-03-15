/**
 * 
 */
package eyeofsauron.domain.utils.arguments;



import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;



/**
 * @author john
 *
 */
public abstract class UtilityArgument implements Iterable<Object>{

	protected List<Object> argumentQueue = new ArrayList<Object>();
	protected Set<Class<?>> validArgumentTypes = new HashSet<Class<?>>();
	
	
	protected UtilityArgument(){
		setValidArgumentClasses();
	}

	protected UtilityArgument(Object obj) throws UtilityArgumentException{
		setValidArgumentClasses();
		addArgument(obj);
	}

	
	protected UtilityArgument (Collection <Object> c )throws UtilityArgumentException{
		this();
		for (Object obj : c)
			addArgument(obj);
	}
	
	public void addArgument(Object arg) throws UtilityArgumentException{
		if (!isValidArgument(arg)) throw new UtilityArgumentException("Argument: "+arg+" cannot be processed by utility");
//		Pair<Object, Class<?>>  tempPair = Pair.of(arg, arg.getClass());
		argumentQueue.add(arg);
	}
	
	
	@SuppressWarnings("unchecked")
	public <CLASS> List<CLASS> getArgumentsByClass(Class<CLASS> c){
		
		if (!validArgumentTypes.contains(c)) return null;
		List<CLASS> ret = new ArrayList<CLASS>();
		for (Object arg : argumentQueue)
			if (arg.getClass().equals(c))
				ret.add((CLASS) arg);

		return ret;
		
	}
	
	public Object getArgumentByIndex(int i){
		return argumentQueue.get(i);
	}
	
	public int size(){
		return argumentQueue.size();
	}
	
	@Override
	public Iterator<Object> iterator() {
	    return argumentQueue.iterator();
	}
	
	protected void addValidArgumentClass(Class<?> c){
		validArgumentTypes.add(c);
	}
	
	protected boolean isValidArgument(Object arg){
		
		if (validArgumentTypes.contains((arg.getClass())))	return true;
		return false;
	}

	public Set<Class<?>> getValidArgumentClasses(){
		return validArgumentTypes;
	}
	
	protected abstract void setValidArgumentClasses();
}
