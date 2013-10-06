package net.fireinjection.vwell;

import net.fireinjection.vwell.service.DataInitService;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.NoTitle;

@NoTitle
@Fullscreen
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	@Bean DataInitService dataInitService;
	
	@AfterInject
	void afterInject(){
		dataInitService.cleanData();
		dataInitService.initializeUsers();
	}
	
	@Click(R.id.healthButton)
	void clickHealthButton(View view){
		Intent intent = new Intent(this, HealthActivity_.class);
		this.startActivity(intent);
	}
	
	@Click(R.id.mapButton)
	void clickMapButton(View view){
		Intent intent = new Intent(this, MapActivity_.class);
		this.startActivity(intent);
	}
	
	@Click(R.id.searchButton)
	void clickSearchButton(View view){
		Intent intent = new Intent(this, SearchActivity_.class);
		this.startActivity(intent);
	}
}