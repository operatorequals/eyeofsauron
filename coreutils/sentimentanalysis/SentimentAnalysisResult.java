/**
 * 
 */
package eyeofsauron.coreutils.sentimentanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import eyeofsauron.domain.results.visuables.ChartCapable;
import eyeofsauron.domain.results.*;
/**
 * @author john
 *
 */
public class SentimentAnalysisResult extends AnalysisResult<SentimentAnalysisData> implements ChartCapable{

	public static final String POS = "Positive";
	public static final String NEU = "Neutral";
	public static final String NEG = "Negative";
	
	protected static int neutralThreshold = 0;
	
	protected SentimentResultTypeEnum type;
	

	public static void setNeutralThreshold(int i){
		if (i < 0 || i >4)	throw new IllegalArgumentException("NeutralThreshold is integer in [0,4]");
		neutralThreshold = i;		
	}
	
	public SentimentAnalysisResult(String title, String description,
			SentimentResultTypeEnum sentimentResultTypeEnum, String source) {
		super((new ArrayList<SentimentAnalysisData>()), title, description, source);
		this.type = sentimentResultTypeEnum;
	}
	
	@Override
	public Map<String, Integer> getValues() {
		Map<String, Integer> ret = new HashMap<String, Integer>();
		ret.put(POS, 0);
		ret.put(NEU, 0);
		ret.put(NEG, 0);
		
		for (SentimentAnalysisData d: getData()){
			double[] data = d.getData();
			int total = data.length-1;
			
			if (data[total] > neutralThreshold)
				ret.put(POS, (ret.get(POS).intValue()+1));
			else if (data[total] < -neutralThreshold)
				ret.put(NEG, (ret.get(NEG).intValue()+1));
			else
				ret.put(NEU, (ret.get(NEU).intValue()+1));
		}
		return ret;
	}

	@Override
	public float getMultiplier() {
		return 1;
	}

	/**
	 * @return the type
	 */
	public SentimentResultTypeEnum getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(SentimentResultTypeEnum type) {
		this.type = type;
	}

	
}
