package ataei.sina.hezarghab.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ataei.sina.hezarghab.LevelItems;
import ataei.sina.hezarghab.R;
import ataei.sina.hezarghab.models.Level;
import ataei.sina.hezarghab.models.User_Item;

public class RecyclerLevelsItemAdapter extends RecyclerView.Adapter<RecyclerLevelsItemAdapter.ViewHolder> {

    List<List<User_Item>>list;
    Context context;

  public RecyclerLevelsItemAdapter(Context context, List<List<User_Item>>list){
      this.list=list;
      this.context=context;
  }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_rcycler_level_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       List<User_Item>sec_list;
        sec_list=list.get(position);

        holder.num_level1.setText(sec_list.get(0).getNum_question());
        holder.num_level2.setText(sec_list.get(1).getNum_question());
        holder.num_level3.setText(sec_list.get(2).getNum_question());

        if (sec_list.get(0).getAnswered().equals("0")){
            holder.num_level1.setVisibility(View.GONE);
            holder.lock1.setVisibility(View.VISIBLE);
        }
        if (sec_list.get(1).getAnswered().equals("0")){
            holder.num_level2.setVisibility(View.GONE);
            holder.lock2.setVisibility(View.VISIBLE);
        }
        if (sec_list.get(2).getAnswered().equals("0")){
            holder.num_level3.setVisibility(View.GONE);
            holder.lock3.setVisibility(View.VISIBLE);
        }





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView star_1_1,star_1_2,star_1_3;
        ImageView star_2_1,star_2_2,star_2_3;
        ImageView star_3_1,star_3_2,star_3_3;
        ImageView lock1,lock2,lock3;
        TextView num_level1,num_level2,num_level3;
        CardView card_1,card_2,card_3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            star_1_1=itemView.findViewById(R.id.star_1_1);
            star_1_2=itemView.findViewById(R.id.star_1_2);
            star_1_3=itemView.findViewById(R.id.star_1_3);
            star_2_1=itemView.findViewById(R.id.star_2_1);
            star_2_2=itemView.findViewById(R.id.star_2_2);
            star_2_3=itemView.findViewById(R.id.star_2_3);
            star_3_1=itemView.findViewById(R.id.star_3_1);
            star_3_2=itemView.findViewById(R.id.star_3_2);
            star_3_3=itemView.findViewById(R.id.star_3_3);
            card_1=itemView.findViewById(R.id.card1);
            card_2=itemView.findViewById(R.id.card2);
            card_3=itemView.findViewById(R.id.card3);
            num_level1=itemView.findViewById(R.id.text_1);
            num_level2=itemView.findViewById(R.id.text_2);
            num_level3=itemView.findViewById(R.id.text_3);
            lock1=itemView.findViewById(R.id.lock_1);
            lock2=itemView.findViewById(R.id.lock_2);
            lock3=itemView.findViewById(R.id.lock_3);

        }
    }

}