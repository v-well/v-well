package net.fireinjection.vwell.view;

import net.fireinjection.vwell.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.view_menu_item)
public class MenuItemView extends LinearLayout {
	@ViewById(R.id.menuIcon) TextView menuIcon;
	
	public MenuItemView(Context context) {
		super(context);
	}

	@SuppressWarnings("deprecation")
	public void setIconicDrawable(Drawable iconicDrawable) {
		menuIcon.setBackgroundDrawable(iconicDrawable);
	}
	
}
