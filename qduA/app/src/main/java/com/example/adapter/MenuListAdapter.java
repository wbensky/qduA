package com.example.adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app.R;

public class MenuListAdapter extends BaseAdapter {
    View[] itemViews;
    private Context listViewClass;
    private int selectedPosition = 2;

    public MenuListAdapter(String[] itemTitles,
                           int[] itemImageRes,Context listViewClass) {
        itemViews = new View[itemTitles.length];
        this.listViewClass = listViewClass;
        for (int i = 0; i < itemViews.length; i++) {

            itemViews[i] = makeItemView(itemTitles[i],
                    itemImageRes[i]);
            if(i == selectedPosition)
            {
                itemViews[i].setBackgroundColor(Color.parseColor("#227ce7"));
                TextView textView = (TextView)(itemViews[i].findViewById(R.id.itemText));
                textView.setTextColor(Color.parseColor("#ffffff"));

            }
        }
    }

    public int getCount() {
        return itemViews.length;
    }

    public View getItem(int position) {
        return itemViews[position];
    }

    public long getItemId(int position) {
        return position;
    }

    private View makeItemView(String strTitle,  int resId) {
        LayoutInflater inflater = (LayoutInflater) listViewClass
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 使用View的对象itemView与R.layout.item关联
        View itemView = inflater.inflate(R.layout.menu_list_item, null);

        // 通过findViewById()方法实例R.layout.item内各组件
        TextView text = (TextView) itemView.findViewById(R.id.itemText);
        text.setText(strTitle);
        ImageView image = (ImageView) itemView.findViewById(R.id.itemImage);
        image.setImageResource(resId);
        itemView.setId(4);

        return itemView;
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null){
            convertView =  itemViews[position];
        }
        //convertView.setBackgroundColor(Color.parseColor("#227ce7"));
        return convertView;
    }
}
