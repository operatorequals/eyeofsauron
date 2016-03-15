/**
 * 
 */
package eyeofsauron.servlet.graph.jswrappers;

import java.awt.Color;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

/**
 * @author john
 *
 */
public interface WebDrawer {

	public String drawChronicityChart(Pair<Date, Double>[] data);
	
	public String drawPieChart(Triple<String, Double, Color> [] data);
	
	public String drawPieChart(Map<String, Long>  data);
	
	public String drawMultiSeriesBar(Pair<String, Double>[] data1, Pair<String, Double>[] data2, double max);
	
	public String drawMultiSeriesBar(Pair<String, Double>[] data1, Pair<String, Double>[] data2);
	
	public String drawMultiSeriesBar(Pair<String, String> headers, Triple<String, Double, Double>[] data);
	
	
}
