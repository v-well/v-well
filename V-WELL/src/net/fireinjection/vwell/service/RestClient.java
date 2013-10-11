package net.fireinjection.vwell.service;

import net.fireinjection.vwell.model.AroundResponseData;
import net.fireinjection.vwell.support.MappingJacksonHttpMessageConverter2;

import org.springframework.http.converter.StringHttpMessageConverter;

import com.googlecode.androidannotations.annotations.rest.Accept;
import com.googlecode.androidannotations.annotations.rest.Get;
import com.googlecode.androidannotations.annotations.rest.Rest;
import com.googlecode.androidannotations.api.rest.MediaType;

@Rest(converters={StringHttpMessageConverter.class, MappingJacksonHttpMessageConverter2.class})
public interface RestClient {
	
	@Get("")
	@Accept(MediaType.APPLICATION_JSON)
	public AroundResponseData getAroundResponseData();
	
	void setRootUrl(String rootUrl);
}
