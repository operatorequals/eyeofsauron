/**
 * 
 */
package eyeofsauron.domain.utils;

/**
 * @author john
 *
 */
public class UtilityIdentity {
	
	private String id;
	private double version;
	/**
	 * @param id
	 * @param version
	 */
	public UtilityIdentity(String id, double version) {
		super();
		this.id = id;
		this.version = version;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the version
	 */
	public double getVersion() {
		return version;
	}

	
	public String toString(){
		String ret = id+" - version: "+version;
		return ret;
	}

}
