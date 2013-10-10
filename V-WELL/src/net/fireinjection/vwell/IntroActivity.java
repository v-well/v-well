package net.fireinjection.vwell;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.NoTitle;

@Fullscreen
@NoTitle
@EActivity(R.layout.activity_intro)
public class IntroActivity extends Activity {
	
	@AfterViews
	void afterViews() {
		initView();
	}

	private void initView() {
		new Handler().postDelayed(new Runnable() {
			public void run() {
				startActivity(new Intent(IntroActivity.this, MainActivity_.class));
				finish();
			}
		}, 2000);
	}
}
