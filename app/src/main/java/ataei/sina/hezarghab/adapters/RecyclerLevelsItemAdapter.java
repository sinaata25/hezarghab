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

import ataei.sina.hezarghab.Game;
import ataei.sina.hezarghab.LevelItems;
import ataei.sina.hezarghab.R;
import ataei.sina.hezarghab.models.Game_model;
import ataei.sina.hezarghab.models.Level;
import ataei.sina.hezarghab.models.User_Item;

public class RecyclerLevelsItemAdapter extends RecyclerView.Adapter<RecyclerLevelsItemAdapter.ViewHolder> {

    List<List<User_Item>>list;
    Context context;
    String color;
    String color_secendary;
    int levelId;
    public  static List<Game_model>list_data_game;

  public RecyclerLevelsItemAdapter(Context context, List<List<User_Item>>list,String color,String color_secendary,List<Game_model>list_data_game,int levelId){
      this.list=list;
      this.context=context;
      this.color=color;
      this.color_secendary=color_secendary;
      this.list_data_game=list_data_game;
      this.levelId=levelId;
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

        holder.card_1.setCardBackgroundColor(Color.parseColor(color));
        holder.card_2.setCardBackgroundColor(Color.parseColor(color));
        holder.card_3.setCardBackgroundColor(Color.parseColor(color));




        if (sec_list.get(0).getAnswered().equals("0")){
            holder.num_level1.setVisibility(View.GONE);
            holder.lock1.setVisibility(View.VISIBLE);
        }else {
            if(Integer.parseInt(sec_list.get(0).getStars())==3){
                holder.star_1_1.setImageResource(R.drawable.ic_star);
                holder.star_1_2.setImageResource(R.drawable.ic_star);
                holder.star_1_3.setImageResource(R.drawable.ic_star);

            }else if(Integer.parseInt(sec_list.get(0).getStars())==2){
                holder.star_1_1.setImageResource(R.drawable.ic_star);
                holder.star_1_2.setImageResource(R.drawable.ic_star);
            }else if(Integer.parseInt(sec_list.get(0).getStars())==1) {
                holder.star_1_1.setImageResource(R.drawable.ic_star);
            }
        }
        if (sec_list.get(1).getAnswered().equals("0")){
            holder.num_level2.setVisibility(View.GONE);
            holder.lock2.setVisibility(View.VISIBLE);

        }else {
            if(Integer.parseInt(sec_list.get(1).getStars())==3){
                holder.star_2_1.setImageResource(R.drawable.ic_star);
                holder.star_2_2.setImageResource(R.drawable.ic_star);
                holder.star_2_3.setImageResource(R.drawable.ic_star);

            }else if(Integer.parseInt(sec_list.get(1).getStars())==2){
                holder.star_2_1.setImageResource(R.drawable.ic_star);
                holder.star_2_2.setImageResource(R.drawable.ic_star);
            }else if(Integer.parseInt(sec_list.get(1).getStars())==1) {
                holder.star_2_1.setImageResource(R.drawable.ic_star);
            }
        }
        if (sec_list.get(2).getAnswered().equals("0")){
            holder.num_level3.setVisibility(View.GONE);
            holder.lock3.setVisibility(View.VISIBLE);

        }else {
            if(Integer.parseInt(sec_list.get(2).getStars())==3){
                holder.star_3_1.setImageResource(R.drawable.ic_star);
                holder.star_3_2.setImageResource(R.drawable.ic_star);
                holder.star_3_3.setImageResource(R.drawable.ic_star);

            }else if(Integer.parseInt(sec_list.get(2).getStars())==2){
                holder.star_3_1.setImageResource(R.drawable.ic_star);
                holder.star_3_2.setImageResource(R.drawable.ic_star);
            }else if(Integer.parseInt(sec_list.get(2).getStars())==1) {
                holder.star_3_1.setImageResource(R.drawable.ic_star);
            }
        }

        if (!sec_list.get(0).getAnswered().equals("0")){
            holder.card_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num_q=Integer.parseInt(sec_list.get(0).getNum_question());
                    Intent intent=new Intent(context, Game.class);
                    intent.putExtra("primary",color);
                    intent.putExtra("num_q",num_q);
                    intent.putExtra("secendary",color_secendary);
                    intent.putExtra("levelId",levelId);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
        if (!sec_list.get(1).getAnswered().equals("0")){
            holder.card_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num_q=Integer.parseInt(sec_list.get(1).getNum_question());
                    Intent intent=new Intent(context, Game.class);
                    intent.putExtra("primary",color);
                    intent.putExtra("num_q",num_q);
                    intent.putExtra("secendary",color_secendary);
                    intent.putExtra("levelId",levelId);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.startActivity(intent);

                }
            });
        }
        if (!sec_list.get(2).getAnswered().equals("0")){
            holder.card_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num_q=Integer.parseInt(sec_list.get(2).getNum_question());
                    Intent intent=new Intent(context, Game.class);
                    intent.putExtra("primary",color);
                    intent.putExtra("num_q",num_q);
                    intent.putExtra("secendary",color_secendary);
                    intent.putExtra("levelId",levelId);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
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
