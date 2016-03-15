/**
 * 
 */
package eyeofsauron.coreutils.sentimentanalysis;

import eyeofsauron.domain.results.AnalysisData;

/**
 * @author john
 *
 */
public class SentimentAnalysisData extends AnalysisData<double[]> {

	public SentimentAnalysisData(double[] data, String description) {
		super(data,description);
	}


}
