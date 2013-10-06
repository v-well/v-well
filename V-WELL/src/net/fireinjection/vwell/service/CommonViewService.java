package net.fireinjection.vwell.service;

import java.util.ArrayList;
import java.util.List;

import net.fireinjection.vwell.model.Menu;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;
import com.googlecode.androidannotations.api.Scope;

@EBean(scope=Scope.Singleton)
public class CommonViewService {
	@RootContext Context context;
	
	public Drawable getListIcon(){
		IconicFontDrawable listIcon = new IconicFontDrawable(context);
		listIcon.setIcon(EntypoIcon.LIST);
		listIcon.setIconColor(0xFFFFFFFF);
		listIcon.setIntrinsicHeight(35);
		listIcon.setIntrinsicWidth(35);
		
		return listIcon;
	}
	
	public List<Menu> getMenuItems(int exceptId){
		List<Menu> menuIcons = new ArrayList<Menu>();

		IconicFontDrawable homeIcon = new IconicFontDrawable(context);
		homeIcon.setIcon(EntypoIcon.HOME);
		homeIcon.setIconColor(0xFFFFFFFF);
		
		IconicFontDrawable noteIcon = new IconicFontDrawable(context);
		noteIcon.setIcon(EntypoIcon.OPEN_BOOK);
		noteIcon.setIconColor(0xFFFFFFFF);
		
		IconicFontDrawable locationIcon = new IconicFontDrawable(context);
		locationIcon.setIcon(EntypoIcon.LOCATION);
		locationIcon.setIconColor(0xFFFFFFFF);
		
		IconicFontDrawable searchIcon = new IconicFontDrawable(context);
		searchIcon.setIcon(EntypoIcon.SEARCH);
		searchIcon.setIconColor(0xFFFFFFFF);
		
		if (exceptId != Menu.ID_HOME) {
			menuIcons.add(new Menu(Menu.ID_HOME, homeIcon));
		}
		if (exceptId != Menu.ID_NOTE) {
			menuIcons.add(new Menu(Menu.ID_NOTE, noteIcon));
		}
		if (exceptId != Menu.ID_LOCATION) {
			menuIcons.add(new Menu(Menu.ID_LOCATION, locationIcon));
		}
		if (exceptId != Menu.ID_SEARCH) {
			menuIcons.add(new Menu(Menu.ID_SEARCH, searchIcon));
		}
		
		return menuIcons;
	}
}
