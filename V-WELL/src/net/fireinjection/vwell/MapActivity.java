package net.fireinjection.vwell;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import net.fireinjection.vwell.model.AroundResponseData;
import net.fireinjection.vwell.model.AroundResponseData.ResponseData.HealthCenters;
import net.fireinjection.vwell.model.AroundResponseData.ResponseData.Hospitals;
import net.fireinjection.vwell.model.Menu;
import net.fireinjection.vwell.service.AroundDataService;
import net.fireinjection.vwell.service.CommonViewService;
import net.fireinjection.vwell.view.MenuAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.ViewById;

@Fullscreen
@EActivity(R.layout.activity_map)
public class MapActivity extends Activity implements MapView.OpenAPIKeyAuthenticationResultListener, MapView.POIItemEventListener {
	@ViewById(R.id.mapMenuLayout) DrawerLayout menuLayout;
	@ViewById(R.id.mapMenuListView) ListView menuListView;
	@ViewById(R.id.mapContentLayout) FrameLayout mapContentLayout;
	
	@Bean MenuAdapter menuAdapter;
	@Bean CommonViewService commonViewService;
	@Bean AroundDataService aroundDataService;
	
	private MapView mapView;

	private final LocationListener locListener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			// GPS 상태 변화에 따른 행동을 적어주면 된다.
			Log.e("#####", "onStatusChanged");
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			// GPS 사용 가능으로 바꼈을 때 행동이다.
			Log.e("#####", "onProviderEnabled");
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			// GPS 사용 불가능으로 바꼈을 때 행동을 적어주면 된다.
			Log.e("#####", "onProviderDisabled");
		}

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			// 바뀐 위치를 수신하였을 경우 수행되는 부분이다.
			Log.e("#####", "onLocationChanged");
		}
	};
	
	@AfterInject
	void afterInject(){
		LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		String provider = LocationManager.GPS_PROVIDER; // GPS_PROVIER 로 설정
		Location loc = locManager.getLastKnownLocation(provider); // 마지막으로 기억하는 위치를 설정한다.
		// 주소를 확인하기 위한 Geocoder KOREA 와 KOREAN 둘다 가능
		Geocoder geoCoder = new Geocoder(this, Locale.KOREAN); 
		// GPS 를 1초(1000)마다 또는 10미터 이동시마다 위치값을 받아온다.
		locManager.requestLocationUpdates(provider, 10000, 10, locListener);
		
		if (loc != null) { // 현제 지역의 값이 있을경우만 아래 코드 실행
			double lat = loc.getLatitude(); // 두 줄의 코드는 현제 위치(loc)의 위/경도를 받아온다.
			double lng = loc.getLongitude();

			Log.e("#####", "loc.getLatitude() : " + loc.getLatitude());
			Log.e("#####", "loc.getLongitude() : " + loc.getLongitude());

			// 위경도를 이용하여 현제 위치의 주소를 받아온다. 세번째 인자는 현제 위치의 주소형태가 여러가지인데 그 중 몇가지를
			// 받아올 것인가를 설정한다. (인천 / 인천시 연수구 / 인천시 연수구 송도동 등으로 여러가지가 나온다. 다음줄에
			// 중단점을 잡고 직접 확인해보면 좋을것이다.
			List<Address> addresses;
			try {
				addresses = geoCoder.getFromLocation(lat, lng, 5);
				if (addresses.size() > 0) { // 받아온 주소가 있을 경우
					// 첫번째 주소를 Address 형식으로 받아온다. 필자는 첫번째(가장 자세한 주소값이 들어가있다) 만 사용한다.
					Address mAddress = addresses.get(0);
					// 아래와 같은 방법으로 빼내면 되며 시/구/동 이 저장된 변수에 대해서는 중단점을 잡고 Address 객체의
					// 값들을 확인해 보면서 하면 되겠다.
					String Area = mAddress.getAdminArea();
					// mAddress 를 가공하여 원하는 주소를 빼내면 되며 밑에 코드는 필자가 String 형식으로 뽑아내는
					// 방법이다.
					// 원래는 Address 안에 국가/도시/구/동 별로 따로 저장되어 있지만 가끔 구 가 빠져있던가 하는 예외가
					// 생기므로
					// (완전 빠졌다는게 아니라 구가 들어가야 하는 변수에 안들어가있다던가 하는 경우)
					// 내가 원하는 방법으로 직접 String을 자른 부분이다.
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@AfterViews
	void afterViews(){
		initActionBar();
	
		mapView = new MapView(this);
		mapView.setDaumMapApiKey("949ac12236291052b84eac91db759f04fb1ee72b");
		mapView.setOpenAPIKeyAuthenticationResultListener(this);
		mapView.setPOIItemEventListener(this);
		
		// 현재 위치, 서현동 신영팰리스타워
		Double lat = 37.38573255;
		Double lnt = 127.12568610;
		
		initMapData(initMapData(lat, lnt));
		
		mapContentLayout.addView(mapView);
		mapView.fitMapViewAreaToShowAllPOIItems();
		mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(lat, lnt), 2, true);
		
	}
	
	private void initMapData(AroundResponseData data) {
		for (HealthCenters healthCenter : data.getResponseData().getHealthCenters()) {
			MapPOIItem mapPOIItem = new MapPOIItem();
			mapPOIItem.setItemName(healthCenter.getName());
			mapPOIItem.setMapPoint(MapPoint.mapPointWithGeoCoord(
					healthCenter.getLocation().getLat(),
					healthCenter.getLocation().getLng()));
			mapPOIItem.setMarkerType(MapPOIItem.MarkerType.RedPin);
			mapPOIItem.setShowAnimationType(MapPOIItem.ShowAnimationType.DropFromHeaven);
			mapPOIItem.setShowCalloutBalloonOnTouch(true);
			mapPOIItem.setDraggable(false);
			mapPOIItem.setTag(153);
			this.mapView.addPOIItem(mapPOIItem);
		}
		for (Hospitals hospitals : data.getResponseData().getHospitals()) {
			MapPOIItem mapPOIItem = new MapPOIItem();
			mapPOIItem.setItemName(hospitals.getName());
			mapPOIItem.setMapPoint(MapPoint.mapPointWithGeoCoord(
					hospitals.getLocation().getLat(),
					hospitals.getLocation().getLng()));
			mapPOIItem.setMarkerType(MapPOIItem.MarkerType.BluePin);
			mapPOIItem.setShowAnimationType(MapPOIItem.ShowAnimationType.DropFromHeaven);
			mapPOIItem.setShowCalloutBalloonOnTouch(true);
			mapPOIItem.setDraggable(false);
			mapPOIItem.setTag(153);
			this.mapView.addPOIItem(mapPOIItem);
		}
	}

	private AroundResponseData initMapData(Double lat, Double lng) {
		return aroundDataService.getDatas(lat, lng);
	}

	private void initActionBar(){
		menuAdapter.setItems(commonViewService.getMenuItems(Menu.ID_LOCATION));
		menuListView.setAdapter(menuAdapter);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setIcon(commonViewService.getListIcon());
		actionBar.setTitle("주변병원찾기");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			if(menuLayout.isDrawerOpen(menuListView)){
				menuLayout.closeDrawer(menuListView);
			}else{
				menuLayout.openDrawer(menuListView);
			}
		}
		
		return true;
	}
	
	@ItemClick
	void mapMenuListViewItemClicked(Menu menu){
		int menuId = menu.getId();
		switch(menuId){
		case Menu.ID_NOTE : 
			startActivity(new Intent(this, HealthActivity_.class));
			break;
		case Menu.ID_SEARCH : 
			startActivity(new Intent(this, SearchActivity_.class));
			break;
		case Menu.ID_HOME :
		default : break;
		}
		
		finish();
	}

	@Override
	public void onCalloutBalloonOfPOIItemTouched(MapView arg0, MapPOIItem arg1) {
		
	}

	@Override
	public void onDraggablePOIItemMoved(MapView arg0, MapPOIItem arg1, MapPoint arg2) {
		
	}

	@Override
	public void onPOIItemSelected(MapView arg0, MapPOIItem arg1) {
		
	}

	@Override
	public void onDaumMapOpenAPIKeyAuthenticationResult(MapView arg0, int arg1, String arg2) {
		
	}

}
