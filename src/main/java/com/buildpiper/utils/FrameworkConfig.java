package com.buildpiper.utils;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
	    "system:properties",
        "system:env",
		"classpath:project.properties" 
        }
)
public interface FrameworkConfig extends Config {

	@DefaultValue("sandbox")
	String environment();

	@Key("${environment}.username")
	String username();

	@Key("${environment}.password")
	String password();

	@Key("${environment}.url")
	String url();

}
