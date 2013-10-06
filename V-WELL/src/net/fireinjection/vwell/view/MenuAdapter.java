package net.fireinjection.vwell.view;

import java.util.ArrayList;
import java.util.List;

import net.fireinjection.vwell.model.Menu;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;
import com.googlecode.androidannotations.api.Scope;

@EBean(scope = Scope.Singleton)
public class MenuAdapter extends BaseAdapter {
	@RootContext Context context;
	private List<Menu> items = new ArrayList<Menu>();
	
	@Override
	public int getCount() {
		return items.size();
	}
	@Override
	public Object getItem(int arg0) {
		return items.get(arg0);
	}
	@Override
	public long getItemId(int arg0) {
		return items.get(arg0).getId();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MenuItemView menuItemView = null;
		if(convertView == null){
			menuItemView = MenuItemView_.build(context);
		}else{
			menuItemView = (MenuItemView) convertView;
		}
		
		Drawable iconicDrawable = items.get(position).getDrawable();
		menuItemView.setIconicDrawable(iconicDrawable);
		return menuItemView;
	}

	public void setItems(List<Menu> items){
		this.items = items;
		notifyDataSetChanged();
	}
}