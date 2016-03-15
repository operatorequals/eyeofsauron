package eyeofsauron.coreutils.clusterer;

import java.util.ArrayList;
import java.util.List;

import eyeofsauron.domain.results.AnalysisResult;
import eyeofsauron.domain.results.visuables.ListCapable;

public class ClustererResult extends AnalysisResult<ClustererData> implements ListCapable{

	protected ClustererResult(List<ClustererData> data, String title, String description,
			String source) {
		super(data, title, description, source);

	}

	public ClustererResult(String title, String description,
			String source) {
		super(title, description, source);

	}

	@Override
	public List<String> getValues() {
		
//		List<String> ret = new ArrayList<String>();
//		for (ClustererData d : data){
//
//			
//		}
		return null;
	}

}
