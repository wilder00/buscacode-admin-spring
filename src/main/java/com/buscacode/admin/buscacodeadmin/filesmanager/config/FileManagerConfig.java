package com.buscacode.admin.buscacodeadmin.filesmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
	@PropertySource(value="classpath:files.properties", encoding = "UTF-8"),
})
public class FileManagerConfig {

}
