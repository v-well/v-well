package net.fireinjection.vwell.service;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import net.fireinjection.vwell.model.User;
import net.fireinjection.vwell.persistence.SqliteOpenHelper;
import android.content.Context;

import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.OrmLiteDao;
import com.googlecode.androidannotations.annotations.RootContext;
import com.googlecode.androidannotations.api.Scope;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;

@EBean(scope=Scope.Singleton)
public class DataInitService {
	@RootContext Context context;

	@OrmLiteDao(helper = SqliteOpenHelper.class, model = User.class) Dao<User, Integer> userDao;
	
	@AfterInject
	void afterInject(){
		
	}
	
	public void cleanData(){
		try {
			userDao.deleteBuilder().delete();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void initializeUsers(){
		initializeData(userDao, getUserList());
	}
	
	private <T> void initializeData(Dao<T, Integer> dao, List<T> list){
		Savepoint savePoint = null;
		DatabaseConnection conn = null;
		ConnectionSource connectionSource = dao.getConnectionSource();
		
		try {
			conn = connectionSource.getReadWriteConnection();
			conn.setAutoCommit(false);
			savePoint = conn.setSavePoint("savePoint");
			
			for(T t : list){
				dao.create(t);
			}
			conn.commit(savePoint);
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			try {conn.rollback(savePoint);} catch (SQLException e1) {}
			e.printStackTrace();
		} finally {
			try {connectionSource.releaseConnection(conn);} catch (SQLException e) {}
		}
	}
	
	private List<User> getUserList(){
		List<User> list = new ArrayList<User>();

		list.add(new User("이재경", null));
		list.add(new User("한준구", null));
		list.add(new User("김영필", null));
		
		return list;
	}
}
