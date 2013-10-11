package net.fireinjection.vwell.service;

import net.fireinjection.vwell.model.AroundResponseData;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.rest.RestService;
import com.googlecode.androidannotations.api.Scope;

@EBean(scope = Scope.Singleton)
public class AroundDataService {
	
	private static final String URL_AROUND_DATA = "http://14.63.217.9:8080/vwell/around.json?";
	
	@RestService RestClient restClient;
	
	interface RestCallback<T>{ T call(); }
	private <T> T fetchRest(String url, RestCallback<T> callback){
		try {
			synchronized(restClient){
				restClient.setRootUrl(url);
				return callback.call();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private AroundResponseData getAroundResponseData(Double lat, Double lng){
		return this.fetchRest(URL_AROUND_DATA + "lat=" + lat +"&" + "lng=" + lng, new RestCallback<AroundResponseData>(){
			@Override
			public AroundResponseData call() {
				return restClient.getAroundResponseData();
			}
		});
	}
	
	public AroundResponseData getDatas(Double lat, Double lng){
		AroundResponseData data = getAroundResponseData(lat, lng);
		if(data != null){
			return data;
		}
		return null;
	}
	
}