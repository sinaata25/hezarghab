package ataei.sina.hezarghab.adapters;

import android.content.Context;
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

import java.util.List;

import ataei.sina.hezarghab.R;
import ataei.sina.hezarghab.models.Level;

public class RecyclerLevelsAdapter extends RecyclerView.Adapter<RecyclerLevelsAdapter.ViewHolder> {

    List<Level>list;
    Context context;

  public  RecyclerLevelsAdapter(Context context, List<Level>list){
      this.list=list;
      this.context=context;
  }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_recycler_levels,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Level level=list.get(position);
        holder.name.setText(level.getName());
         //   holder.star.setText(level.getName());
            holder.name.setText(level.getName());
            System.out.println(level.getColor_secendary());
            System.out.println(level.getColor_primary());
            holder.cardItem.setCardBackgroundColor(Color.parseColor(level.getColor_secendary()));
            holder.cardGo.setCardBackgroundColor(Color.parseColor(level.getColor_primary()));
            Picasso.get()
                    .load(level.getImg())
                    .fit()
                    .into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name,star;
        CardView cardItem,cardGo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imagview_item_level);
            name=itemView.findViewById(R.id.textView5);
            star=itemView.findViewById(R.id.textview_star_level);
            cardItem=itemView.findViewById(R.id.cardview_item_level);
            cardGo=itemView.findViewById(R.id.cardview_go_level);

        }
    }

}
