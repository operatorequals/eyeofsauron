/**
 * 
 */
package eyeofsauron.controllers;

import eyeofsauron.domain.results.AnalysisData;
import eyeofsauron.domain.results.AnalysisResult;

import java.util.Collection;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

/**
 * @author john
 *
 */
public class CoreData extends AnalysisData<AnalysisResult> {

	public CoreData(AnalysisResult anData, String description) {
		super(anData, description);
	}



}
