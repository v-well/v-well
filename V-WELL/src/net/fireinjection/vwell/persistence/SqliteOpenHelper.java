package net.fireinjection.vwell.persistence;

import net.fireinjection.vwell.model.User;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class SqliteOpenHelper extends OrmLiteSqliteOpenHelper {
	private static final String DATABASE_NAME = "vwell";
	private static final int DATABASE_VERSION = 1;
	protected Context context;

	public SqliteOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}
	
	public SqliteOpenHelper(Context context, String databaseName, CursorFactory factory, int databaseVersion) {
		super(context, databaseName, factory, databaseVersion);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, User.class, true);
			TableUtils.createTable(connectionSource, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}