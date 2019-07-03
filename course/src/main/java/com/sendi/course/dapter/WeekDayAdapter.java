package com.sendi.course.dapter;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sendi.course.activity.ICurrentWeek;
import com.sendi.course.R;

import java.util.List;

/**
 * 周数下拉框适配器
 * Created by EsauLu on 2016-10-04.
 */

public class WeekDayAdapter extends ArrayAdapter<String> {

    private FragmentActivity context;
    private int resource;

    public WeekDayAdapter(FragmentActivity context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout view;

        if(convertView == null){
            view = new LinearLayout(getContext());
            LayoutInflater li = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            li.inflate(resource, view, true);
        }
        else{
            view = (LinearLayout)convertView;
        }

        String item = getItem(position);
        long id=getItemId(position);
        TextView weekNum= view.findViewById(R.id.tv_week_num);

        int currWeek=0;
        if (context  instanceof ICurrentWeek) {
            currWeek = ((ICurrentWeek)context).getCurrentWeek();
        }

        if(id+1==currWeek){
            item+="(本周)";
        }else{
            item=item+"(非本周)";
        }
        weekNum.setText(item);

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LinearLayout view;
        if(convertView == null){
            view = new LinearLayout(getContext());
            LayoutInflater li = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            li.inflate(R.layout.week_day_drop_item_layout, view, true);
        }
        else{
            view = (LinearLayout)convertView;
        }

        String item = getItem(position);
        long id=getItemId(position);
        TextView weekNum =  view.findViewById(R.id.tv_week_item);
        int currWeek=0;
        if (context  instanceof ICurrentWeek) {
            currWeek = ((ICurrentWeek)context).getCurrentWeek();
        }
        if(id+1==currWeek){
            item+="(本周)";
            weekNum.setBackgroundResource(R.drawable.bg1);
            weekNum.setTextColor(Color.WHITE);
        }else{
            weekNum.setBackgroundColor(Color.WHITE);
            weekNum.setTextColor(0xff2e94da);
        }
        weekNum.setText(item);

        return view;
    }
}

























