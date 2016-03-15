package eyeofsauron.coreutils.clusterer;

import java.util.List;

import org.carrot2.core.Cluster;
import org.carrot2.core.Document;


import eyeofsauron.domain.results.AnalysisData;

public class ClustererData extends AnalysisData<Cluster> {

	public ClustererData(Cluster anData, String description) {
		super(anData, description);
		// TODO Auto-generated constructor stub
	}


	public List<Document> getDocuments(){
		return anData.getAllDocuments();
	}

	
}
