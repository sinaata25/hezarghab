package ataei.sina.hezarghab.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.util.List;

import ataei.sina.hezarghab.Game;
import ataei.sina.hezarghab.R;

public class GridAdapter extends BaseAdapter {

    private Context context;

    List<String>list;
    String primary,secendary;


    public GridAdapter(List<String>list,Context context,String primary,String secendary){
        this.context=context;
        this.list=list;
        this.primary=primary;
        this.secendary=secendary;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.gozine, null);

            // set value into textview
            TextView textView = (TextView) gridView.findViewById(R.id.textview_gozine);
            textView.setText(list.get(position));
            CardView cardView=gridView.findViewById(R.id.card_gozine);
            cardView.setCardBackgroundColor(Color.parseColor(primary));

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Game.news.clicked(list.get(position));
                }
            });


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
