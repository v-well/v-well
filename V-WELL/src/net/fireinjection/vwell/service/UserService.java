package net.fireinjection.vwell.service;

import java.sql.SQLException;
import java.util.List;

import net.fireinjection.vwell.HealthActivity;
import net.fireinjection.vwell.model.User;
import net.fireinjection.vwell.persistence.SqliteOpenHelper;
import net.fireinjection.vwell.view.UserAdapter;
import android.content.Context;

import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.OrmLiteDao;
import com.googlecode.androidannotations.annotations.RootContext;
import com.googlecode.androidannotations.api.Scope;
import com.j256.ormlite.dao.Dao;

@EBean(scope=Scope.Singleton)
public class UserService {
	@RootContext Context context;
	@OrmLiteDao(helper = SqliteOpenHelper.class, model = User.class) Dao<User, Integer> userDao;

	@Bean UserAdapter userAdapter;
	
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

	public void addUser(User user) {
		user = new User();
		user.setName("이한솔");
		
		try {
			userDao.create(user);
			userAdapter.setItems(userDao.queryForAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(User user) {
		user.setName("수정..");
		
		try {
			userDao.update(user);
			userAdapter.setItems(userDao.queryForAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
