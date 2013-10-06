package net.fireinjection.vwell;

import net.fireinjection.vwell.model.User;
import net.fireinjection.vwell.service.UserService;
import android.app.ActionBar;
import android.app.Activity;

import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Fullscreen;

@Fullscreen
@EActivity(R.layout.activity_health_user)
public class UserHealthActivity extends Activity {
	@Bean UserService userService;
	
	@AfterInject
	void afterInject(){}
	
	@AfterViews
	void afterViews(){
		initActionBar();
	}
	
	private void initActionBar(){
		User user = userService.getCurrentUser();
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setDisplayShowTitleEnabled(true);
		if(user != null){
			actionBar.setTitle(user.getName());
		}else{
			actionBar.setTitle("사용자 등록");
		}
	}

	@Click(R.id.insertUserButton)
	void clickInsertUserButton(){
		User user = null;
		userService.addUser(user);
		finish();
	}

	@Click(R.id.updateUserButton)
	void clickUpdateUserButton(){
		User user = userService.getCurrentUser();
		if(user != null){
			userService.updateUser(user);
		}
		finish();
	}
}
