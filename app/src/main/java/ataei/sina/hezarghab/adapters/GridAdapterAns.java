package ataei.sina.hezarghab.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ataei.sina.hezarghab.R;

public class GridAdapterAns extends BaseAdapter {

    private Context context;
   // private final String[] mobileValues;
    List<String>list;



    public GridAdapterAns(List<String>list, Context context){
        this.context=context;
        this.list=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.gozine_1, null);

            // set value into textview
            TextView textView = (TextView) gridView.findViewById(R.id.textview_gozine1);
            textView.setText(" ");




        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
