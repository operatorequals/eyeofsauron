/**
 * 
 */
package eyeofsauron.coreutils.searchengine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.carrot2.core.Document;

import eyeofsauron.domain.results.AnalysisResult;
import eyeofsauron.domain.results.visuables.ListCapable;

/**
 * @author john
 *
 */
public class SearchEngineResult extends AnalysisResult<SearchEngineData> implements ListCapable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected SearchEngineResult(List<SearchEngineData> data, String title, String description, String source) {
		super(data, title, description, source);
	}

	protected SearchEngineResult(String title, String description, String source) {
		super(title, description, source);
	}

	
	@Override
	public List<String> getValues() {
		// TODO Auto-generated method stub
		List<String> ret = new ArrayList<String>();
		
		for (SearchEngineData d : data){
			Document doc = d.getData();
			ret.add(doc.getTitle()+" - "+doc.getContentUrl());
		}
		return ret;
	}

	public String toJson(){
		
		
		return null;
	}
}
