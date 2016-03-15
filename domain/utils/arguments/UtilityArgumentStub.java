package eyeofsauron.domain.utils.arguments;




import java.util.Collection;

import org.carrot2.core.Cluster;
import org.carrot2.core.Document;

/**
 * @author john
 *
 */
public class UtilityArgumentStub extends UtilityArgument{

	public UtilityArgumentStub(){
		super();
	}

	public UtilityArgumentStub(Collection <Object >c) throws UtilityArgumentException{
		super(c);
	}

	@Override
	protected void setValidArgumentClasses() {
		this.addValidArgumentClass(String.class);
		this.addValidArgumentClass(Document.class);
		this.addValidArgumentClass(Cluster.class);
		
	}

	
	
}