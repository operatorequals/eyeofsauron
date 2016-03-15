/**
 * 
 */
package eyeofsauron.domain.results.visuables;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author john
 *
 */
public interface Plot2DCapable extends PlotCapable {

	public Map<String, Set<Pair<Integer,Integer>>> getPlotValues();
}
