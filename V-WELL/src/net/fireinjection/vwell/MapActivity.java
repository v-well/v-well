package net.fireinjection.vwell;

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
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
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
	
	@AfterInject
	void afterInject(){
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
