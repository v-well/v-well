package net.fireinjection.vwell.service;

import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;

import com.googlecode.androidannotations.annotations.rest.Accept;
import com.googlecode.androidannotations.annotations.rest.Get;
import com.googlecode.androidannotations.annotations.rest.Rest;
import com.googlecode.androidannotations.api.rest.MediaType;

@Rest(converters={StringHttpMessageConverter.class, ByteArrayHttpMessageConverter.class})
public interface RestClient {
	@Get("")
	@Accept(MediaType.TEXT_PLAIN)
	public String getInfo();

	void setRootUrl(String rootUrl);
}
