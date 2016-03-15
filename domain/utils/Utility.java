package eyeofsauron.domain.utils;


import eyeofsauron.domain.results.AnalysisData;
import eyeofsauron.domain.results.AnalysisResult;
import eyeofsauron.domain.utils.arguments.UtilityArgument;
import eyeofsauron.domain.utils.arguments.UtilityArgumentException;
import eyeofsauron.domain.utils.config.UtilityConfig;

public abstract class Utility <CONFIG extends UtilityConfig, ARGUMENT extends UtilityArgument, RESULT extends AnalysisResult<? extends AnalysisData<?>>>{

//	protected Class<? extends UtilityConfig> configClass;
	protected CONFIG currentConfig;
	
	public abstract boolean configure (CONFIG config);
	
	public abstract RESULT process(ARGUMENT arg) throws UtilityArgumentException;
	
	public abstract UtilityIdentity getUtilityIdentity();


}
