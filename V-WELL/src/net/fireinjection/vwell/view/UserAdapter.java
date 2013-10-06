package net.fireinjection.vwell.view;

import java.util.ArrayList;
import java.util.List;

import net.fireinjection.vwell.model.User;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;
import com.googlecode.androidannotations.api.Scope;

@EBean(scope = Scope.Singleton)
public class UserAdapter extends BaseAdapter {
	@RootContext Context context;
	private List<User> items = new ArrayList<User>();
	
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
		UserItemView userItemView = null;
		if(convertView == null){
			userItemView = UserItemView_.build(context);
		}else{
			userItemView = (UserItemView) convertView;
		}

		User user = items.get(position);
		userItemView.setUser(user);
		
		return userItemView;
	}
	
	public void setItems(List<User> items){
		this.items = items;
		notifyDataSetChanged();
	}
}
