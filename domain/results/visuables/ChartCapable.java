package eyeofsauron.domain.results.visuables;

import java.util.List;
import java.util.Map;

public interface ChartCapable extends Visuable<Map<String, Integer>>{

	
	public float getMultiplier();

	default String getVisualizer(){
		return "chart|core";
	}
}
