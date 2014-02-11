package com.example.app;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lib.AutoNumber;
import com.example.lib.MenuListView;
import com.example.models.Hello;
import com.example.utils.DataHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import java.sql.SQLException;
import android.os.Handler;
import java.util.logging.LogRecord;

import static android.app.PendingIntent.getActivity;
import static android.widget.Toast.makeText;

public class MainActivity extends ActionBarActivity implements  View.OnClickListener {


    android.app.ActionBar actionBar;
    private ListView listView;
    TextView bottomText;
    TextView mytext;
    private DataHelper databaseHelper = null;

    //slider menu
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;



    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView)findViewById(R.id.left_drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open,R.string.drawer_close){

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle("关闭");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle("打开");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        MenuListView l = new MenuListView(mDrawerList, this);
        int titleId = Resources.getSystem().getIdentifier("action_bar_title","id","android");
        TextView title = (TextView)findViewById(titleId);
        title.setTextColor(Color.parseColor("#227ce7"));
        try {
            Dao<Hello,Integer>  helloDap = getHelper().getHelloDataDao();
            Hello hello = new Hello("test");
            helloDap.create(hello);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        //test num ++
        AutoNumber numberChange = (AutoNumber)findViewById(R.id.above_number);
        Handler handler = new Handler();
        String str = "1000";
        numberChange.setText(str, TextView.BufferType.NORMAL, handler);




    }


    private DataHelper getHelper()
    {
        if(databaseHelper == null)
        {
            databaseHelper = OpenHelperManager.getHelper(this,DataHelper.class);

        }
        return databaseHelper;

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        Log.i("test1", "1");

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }



    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.above_bottom_text:
            {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                MainActivity.this.startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            default:
                break;
        }
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
