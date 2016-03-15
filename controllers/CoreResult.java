package eyeofsauron.controllers;

import java.util.Collection;
import java.util.List;

import eyeofsauron.coreutils.sentimentanalysis.SentimentAnalysisResult;
import eyeofsauron.domain.results.AnalysisResult;

public class CoreResult extends AnalysisResult<CoreData> {

	protected CoreResult(List<CoreData> data, String title, String description, String source) {
		super(data, title, description, source);
	}

	
	public CoreResult(String title, String description, String source) {
		super(title, description, source);
	}


	
//	public void addData(Object c, String plot, String source, String description){
//		super.addData( new CoreData(c, plot, source, description));
//	}

}
