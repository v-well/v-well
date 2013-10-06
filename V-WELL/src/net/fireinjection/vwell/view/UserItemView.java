package net.fireinjection.vwell.view;

import net.fireinjection.vwell.R;
import net.fireinjection.vwell.model.User;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.view_user_item)
public class UserItemView extends LinearLayout {
	@ViewById(R.id.userImageView) ImageView userImageView;
	@ViewById(R.id.userNameView) TextView userNameView;
	@ViewById(R.id.detailButton) Button detailButton;
	
	private Context context;
	
	public UserItemView(Context context) {
		super(context);
		this.context = context;
	}

	public void setUser(User user) {
		userNameView.setText(user.getName());
	}
	
	@SuppressWarnings("deprecation")
	@AfterViews
	void afterView(){
		IconicFontDrawable rightIcon = new IconicFontDrawable(context);
		rightIcon.setIcon(EntypoIcon.CHEVRON_RIGHT);
		rightIcon.setIconColor(0xff2D4B66);
		
		detailButton.setBackgroundDrawable(rightIcon);
	}

	@Click(R.id.detailButton)
	void clickDetailButton(View view){
		// TODO show detail view...
	}
}
