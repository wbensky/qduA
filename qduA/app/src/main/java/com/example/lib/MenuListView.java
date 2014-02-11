package com.example.lib;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.adapter.MenuListAdapter;
import com.example.app.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wben on 14-2-7.
 */
public class MenuListView {
    String[] titles={"早起签到","周边消息","课程表","找教室"};

    int[] resIds={R.drawable.ic_action_good,
            R.drawable.ic_action_event,
            R.drawable.ic_action_go_to_today,
            R.drawable.ic_action_search};

    public MenuListView (ListView list,Context context)
    {
       MenuListAdapter listAdapter = new MenuListAdapter(titles, resIds, context);

       list.setAdapter(listAdapter);

       list.setOnItemClickListener(new ItemTouchEvent());

        //生成动态数组，并且转载数据
     }

    class ItemTouchEvent implements AdapterView.OnItemClickListener{

        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
            ListView listView = (ListView)parent;
            Log.i("wwl", "" + position + "  " + id +" " + view.getId());

        }
    }

}
