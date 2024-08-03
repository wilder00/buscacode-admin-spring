package com.buscacode.admin.buscacodeadmin;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
    @PropertySource(value = "classpath:values.properties", encoding = "UTF-8"), // agregar un nuevo archivo de
    @PropertySource(value = "classpath:messages.properties", encoding = "UTF-8"), // agregar un nuevo archivo de
    @PropertySource(value = "classpath:cronjob.properties", encoding = "UTF-8") // agregar un nuevo archivo de
// Aquí puedes agregar más properties
})
public class ValuesConfig {

}
