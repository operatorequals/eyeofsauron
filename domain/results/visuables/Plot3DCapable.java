/**
 * 
 */
package eyeofsauron.domain.results.visuables;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.Triple;

/**
 * @author john
 *
 */
public interface Plot3DCapable extends PlotCapable {

	public Map<String, Set<Triple<Integer,Integer,Integer>>> getPlotValues();
}
