package net.fireinjection.vwell;

import net.fireinjection.vwell.model.Menu;
import net.fireinjection.vwell.service.CommonViewService;
import net.fireinjection.vwell.service.UserService;
import net.fireinjection.vwell.view.MenuAdapter;
import net.fireinjection.vwell.view.UserAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.ViewById;

@Fullscreen
@EActivity(R.layout.activity_health)
public class HealthActivity extends Activity {
	@ViewById(R.id.healthMenuLayout) DrawerLayout menuLayout;
	@ViewById(R.id.healthMenuListView) ListView menuListView;
	
	@ViewById(R.id.userListView) ListView userListView;
	@ViewById(R.id.userAddButton) Button userAddButton;
	
	@Bean MenuAdapter menuAdapter;
	@Bean UserAdapter userAdapter;
	@Bean CommonViewService commonViewService;
	@Bean UserService userService;
	
	@AfterInject
	void afterInject(){
		userService.setHealthActivity(this);
	}
	
	@AfterViews
	void afterViews(){
		initActionBar();
		initUserList();
		
//		IconicFontDrawable addIcon = new IconicFontDrawable(this);
//		addIcon.setIcon(EntypoIcon.PLUS);
//		addIcon.setIconColor(0xffff0000);
//		addIcon.setIconPadding(15);
//		userAddButton.setBackgroundDrawable(addIcon);
	}
	
	private void initUserList(){
		userListView.setAdapter(userAdapter);
		userAdapter.setItems(userService.getUsers());
	}
	
	private void initActionBar(){
		menuAdapter.setItems(commonViewService.getMenuItems(Menu.ID_NOTE));
		menuListView.setAdapter(menuAdapter);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setIcon(commonViewService.getListIcon());
		actionBar.setTitle("건강수첩");
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
	void healthMenuListViewItemClicked(Menu menu){
		int menuId = menu.getId();
		switch(menuId){
		case Menu.ID_LOCATION : 
			startActivity(new Intent(this, MapActivity_.class));
			break;
		case Menu.ID_SEARCH : 
			startActivity(new Intent(this, SearchActivity_.class));
			break;
		case Menu.ID_HOME :
		default : break;
		}
		
		finish();
	}
	
	@Click(R.id.userAddButton)
	void clickUserAddButton(View view){
		userService.setCurrentUser(null);
		startUserHealthActivity();
	}

	public void startUserHealthActivity() {
		startActivity(new Intent(this, UserHealthActivity_.class));
	}
}
