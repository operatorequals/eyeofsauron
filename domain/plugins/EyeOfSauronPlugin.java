package eyeofsauron.domain.plugins;

import eyeofsauron.domain.results.AnalysisData;
import eyeofsauron.domain.results.AnalysisResult;
import eyeofsauron.domain.utils.Utility;
import eyeofsauron.domain.utils.config.UtilityConfig;

public interface EyeOfSauronPlugin 
	<T extends Utility <C,A,R>, C extends UtilityConfig, A extends PluginGenericArgument, R extends AnalysisResult<? extends AnalysisData<?>>>
		{ 
	
	public T getUtilityModule();
	
	public R getResultModule();
	
	public A getArgumentModule();
	
	public C getConfigModule();
}
