package net.fireinjection.vwell.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

public class MappingJacksonHttpMessageConverter2 extends MappingJacksonHttpMessageConverter {

	public MappingJacksonHttpMessageConverter2() {
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
		supportedMediaTypes.add(MediaType.ALL);
		super.setSupportedMediaTypes(supportedMediaTypes);
	}
}
