package net.fireinjection.vwell;

import net.fireinjection.vwell.model.Menu;
import net.fireinjection.vwell.service.CommonViewService;
import net.fireinjection.vwell.view.MenuAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
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
public class MapActivity extends Activity {
	@ViewById(R.id.mapMenuLayout) DrawerLayout menuLayout;
	@ViewById(R.id.mapMenuListView) ListView menuListView;
	
	@Bean MenuAdapter menuAdapter;
	@Bean CommonViewService commonViewService;
	
	@AfterInject
	void afterInject(){}
	
	@AfterViews
	void afterViews(){
		initActionBar();
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

}
