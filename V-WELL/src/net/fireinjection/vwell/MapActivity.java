package net.fireinjection.vwell;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import net.fireinjection.vwell.model.Menu;
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
	
	private MapView mapView;

	@AfterInject
	void afterInject(){}
	
	@AfterViews
	void afterViews(){
		initActionBar();
	
		mapView = new MapView(this);
		mapView.setDaumMapApiKey("869c84e170c5368debc24cd86791c6b6531c3193");
		mapView.setOpenAPIKeyAuthenticationResultListener(this);
		mapView.setPOIItemEventListener(this);
		
//		mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.38573255, 127.12568610), true);
//		mapView.setZoomLevel(4, true);
		mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.38573255, 127.12568610), 1, true);
		
		MapPOIItem poiItem1 = new MapPOIItem();
		poiItem1.setItemName("신영팰리스타워");
		poiItem1.setMapPoint(MapPoint.mapPointWithGeoCoord(37.38573255, 127.12568610));
		poiItem1.setMarkerType(MapPOIItem.MarkerType.BluePin);
		poiItem1.setShowAnimationType(MapPOIItem.ShowAnimationType.DropFromHeaven);
		poiItem1.setShowCalloutBalloonOnTouch(true);
		poiItem1.setDraggable(true);
		poiItem1.setTag(153);
		mapView.addPOIItem(poiItem1);
		
		mapView.fitMapViewAreaToShowAllPOIItems();
		
		mapContentLayout.addView(mapView);
		
		mapView.fitMapViewAreaToShowAllPOIItems();
	
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
