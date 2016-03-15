package eyeofsauron.coreutils.clusterer.semclusterer;

import org.carrot2.core.IClusteringAlgorithm;

import eyeofsauron.domain.utils.config.UtilityConfig;

public class SemanticClustererConfig extends UtilityConfig {

	
	
	private IClusteringAlgorithm algorithm;

	public SemanticClustererConfig(SemanticClusteringAlgorithmEnum e){
		setAlgorithm(e.getAlgorithmObject());
	}

	/**
	 * @return the algorithm
	 */
	public IClusteringAlgorithm getAlgorithm() {
		return algorithm;
	}

	/**
	 * @param algorithm the algorithm to set
	 */
	public void setAlgorithm(IClusteringAlgorithm algorithm) {
		this.algorithm = algorithm;
	}
}
