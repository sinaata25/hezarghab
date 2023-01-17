package ataei.sina.hezarghab.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ataei.sina.hezarghab.Game;
import ataei.sina.hezarghab.R;

public class GridAdapterAns extends BaseAdapter {

    private Context context;
   // private final String[] mobileValues;
    String[] list;
    List<String>guss;



    public GridAdapterAns(String[] list, Context context,List<String>guss){
        this.context=context;
        this.list=list;
        this.guss=guss;
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
            if( guss==null ||  guss.size()==0 ){
                textView.setText(" ");
            }
            else if(!(guss.size()>list.length)) {
                if(position>=guss.size()){
                    textView.setText(" ");
                }else if((position==guss.size()-1) && (position==list.length-1)){
                       textView.setText(guss.get(position));
                        Game.news.win(checkWin(guss,list));

                }
                else {
                    textView.setText(guss.get(position));
                }
            }






        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    private boolean checkWin(List<String>guss,String[]ans) {
        boolean win=true;
        for (int i = 0; i < ans.length; i++) {
            if(!guss.get(i).equals(ans[i])){
                win=false;
                break;
            }
        }
        return win;
    }


    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return list[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



}
