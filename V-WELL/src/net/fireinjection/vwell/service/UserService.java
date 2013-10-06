package net.fireinjection.vwell.service;

import java.util.ArrayList;
import java.util.List;

import net.fireinjection.vwell.HealthActivity;
import net.fireinjection.vwell.model.User;
import android.content.Context;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;
import com.googlecode.androidannotations.api.Scope;

@EBean(scope=Scope.Singleton)
public class UserService {
	@RootContext Context context;

	private HealthActivity healthActivity;
	private User currentUser;
	
	public List<User> getUsers() {
		// TODO using dao..
		
		List<User> list = new ArrayList<User>();

		list.add(new User(1, "이재경", null));
		list.add(new User(2, "한준구", null));
		list.add(new User(3, "김영필", null));
		list.add(new User(4, "이한솔", null));
		list.add(new User(5, "이재경", null));
		list.add(new User(6, "한준구", null));
		list.add(new User(7, "김영필", null));
		list.add(new User(8, "이한솔", null));
		list.add(new User(9, "이재경", null));
		list.add(new User(10, "한준구", null));
		list.add(new User(11, "김영필", null));
		list.add(new User(12, "이한솔", null));
		
		return list;
	}

	public void setCurrentUser(User user) {
		this.currentUser = user;
	}
	
	public User getCurrentUser(){
		return this.currentUser;
	}

	public void setHealthActivity(HealthActivity healthActivity) {
		this.healthActivity = healthActivity;
	}

	public void openUserHealthActivity() {
		healthActivity.startUserHealthActivity();
	}
	
}
