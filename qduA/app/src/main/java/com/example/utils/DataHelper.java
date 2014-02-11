package com.example.utils;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.models.Hello;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by wben on 14-2-10.
 */
public class DataHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "HelloOrmlite.db";
    private static final int DATABASE_VERSION = 1;
    private Dao<Hello, Integer> helloDao = null;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Hello.class);
            Log.i("wwl","create database success");
        } catch (SQLException e) {
            Log.e(DataHelper.class.getName(), "创建数据库失败", e);
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int arg2,
                          int arg3) {
        try {
            TableUtils.dropTable(connectionSource, Hello.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DataHelper.class.getName(), "更新数据库失败", e);
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
        helloDao = null;
    }

    public Dao<Hello, Integer> getHelloDataDao() throws SQLException, java.sql.SQLException {
        if (helloDao == null) {
            helloDao = getDao(Hello.class);
        }
        return helloDao;
    }

}
