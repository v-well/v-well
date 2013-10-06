package net.fireinjection.vwell.service;

import java.sql.SQLException;
import java.util.List;

import net.fireinjection.vwell.HealthActivity;
import net.fireinjection.vwell.model.User;
import net.fireinjection.vwell.persistence.SqliteOpenHelper;
import android.content.Context;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.OrmLiteDao;
import com.googlecode.androidannotations.annotations.RootContext;
import com.googlecode.androidannotations.api.Scope;
import com.j256.ormlite.dao.Dao;

@EBean(scope=Scope.Singleton)
public class UserService {
	@RootContext Context context;
	@OrmLiteDao(helper = SqliteOpenHelper.class, model = User.class) Dao<User, Integer> userDao;

	private HealthActivity healthActivity;
	private User currentUser;
	
	public List<User> getUsers() {
		try {
			return userDao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
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
