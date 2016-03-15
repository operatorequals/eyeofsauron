package eyeofsauron.controllers;

import java.util.List;

import eyeofsauron.domain.utils.arguments.UtilityArgument;

public class CoreArgument extends UtilityArgument {

	@Override
	protected void setValidArgumentClasses() {

		this.addValidArgumentClass(String.class);
	}

	public String getQuery(){
		List<String> strs = this.getArgumentsByClass(String.class);
		if (strs.isEmpty())	return "";
		else
			return strs.get(0);
	}}
