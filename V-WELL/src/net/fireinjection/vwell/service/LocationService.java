package net.fireinjection.vwell.service;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class LocationService implements LocationListener {
	
	@Override
	public void onLocationChanged(Location location) {
		System.out.println("########### : " + location.getLatitude());
		System.out.println("########### : " + location.getLongitude());
	}

	@Override
	public void onProviderDisabled(String provider) {
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}

}
