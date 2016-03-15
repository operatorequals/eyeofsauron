/**
 * 
 */
package eyeofsauron.coreutils.clusterer.semclusterer;

import org.carrot2.clustering.kmeans.BisectingKMeansClusteringAlgorithm;
import org.carrot2.clustering.lingo.LingoClusteringAlgorithm;
import org.carrot2.clustering.stc.STCClusteringAlgorithm;
import org.carrot2.core.IClusteringAlgorithm;
import org.codehaus.jackson.annotate.JsonIgnore;
/**
 * @author john
 *
 */
public enum SemanticClusteringAlgorithmEnum {

	LINGO,
	STC,
	K_MEANS;
	
	@JsonIgnore 
	public IClusteringAlgorithm getAlgorithmObject(){
		
		switch(this){
		case LINGO:
			return new LingoClusteringAlgorithm();
		case STC:
			return new STCClusteringAlgorithm();
		case K_MEANS:
			return new BisectingKMeansClusteringAlgorithm();
		
		default:
			throw new IllegalArgumentException();
		}
	}


	public static SemanticClusteringAlgorithmEnum getAlgorithmEnum(String val){
		
		if (val.equalsIgnoreCase("Lingo"))	return SemanticClusteringAlgorithmEnum.LINGO;
		if (val.equalsIgnoreCase("STC"))	return SemanticClusteringAlgorithmEnum.STC;
		if (val.equalsIgnoreCase("KMeans"))	return SemanticClusteringAlgorithmEnum.K_MEANS;
		if (val.equalsIgnoreCase("K-Means"))	return SemanticClusteringAlgorithmEnum.K_MEANS;

		throw new IllegalArgumentException("\""+val+"\" is not a valid algorithm.");

	}
	
}
