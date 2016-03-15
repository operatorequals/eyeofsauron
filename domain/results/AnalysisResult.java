/**
 * 
 */
package eyeofsauron.domain.results;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/**
 * @author john
 *
 */
public abstract class AnalysisResult <DATA extends AnalysisData<?>> implements Iterable<DATA>,Serializable{
	protected List<DATA> data;
	protected String source;
	protected String description;
	protected String title;
	
	/**
	 * @param data
	 * @param source
	 * @param description
	 * @param title
	 */
	protected AnalysisResult(List<DATA> data, String title,  String description, String source) {

		setTitle(title);
		setData(data);
		setSource(source);
		setDescription(description);
	}
	/**
	 * @param source
	 * @param description
	 */
	protected AnalysisResult(String title,  String description, String source){
		
		this( new ArrayList<DATA>(), title, description, source);
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		if (title == null || title.isEmpty())
			throw new NullPointerException();
		this.title = title;
	}

	/**
	 * @return the data
	 */
	public List<DATA> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<DATA> data) {
		this.data = data;
	}

	public void addData(DATA d) {
		this.data.add(d);
	}
	
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String toString(){
		
		String ret = "";
		if (getTitle() != null && !getTitle().isEmpty())
			ret+= "Title: "+getTitle()+System.lineSeparator();
		if (getDescription() != null && !getDescription().isEmpty())
			ret+= "Description: "+getDescription()+System.lineSeparator();
		if (getSource() != null && !getSource().isEmpty())
			ret+= "Source: "+getSource()+System.lineSeparator();

		for (AnalysisData<?> result: getData()){
			ret+= result.toString();
		}
			return ret;
	}
	
	public String toJson(){
		
		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, true);
		mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
		String ret = null;
		try {
			ret = mapper.writeValueAsString(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	public Iterator<DATA> iterator(){
		return data.iterator();
	}

	public Class<? extends AnalysisResult> getClassType(){
		return this.getClass();
	}
}
