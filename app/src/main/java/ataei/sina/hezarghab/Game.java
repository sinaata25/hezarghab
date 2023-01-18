package ataei.sina.hezarghab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ataei.sina.hezarghab.adapters.GridAdapter;
import ataei.sina.hezarghab.adapters.GridAdapterAns;
import ataei.sina.hezarghab.adapters.RecyclerLevelsItemAdapter;
import ataei.sina.hezarghab.fragments.DialogWin;
import ataei.sina.hezarghab.models.Game_model;
import ataei.sina.hezarghab.models.User_Item;
import ataei.sina.hezarghab.statics.Keys;
import ataei.sina.hezarghab.statics.Urls;
import jp.wasabeef.picasso.transformations.BlurTransformation;

public class Game extends AppCompatActivity {
        GridAdapter gridAdapter;
        RelativeLayout relativeLayout;
        GridAdapterAns gridAdapterAns;
        GridView gridView;
        ImageView imageView_game;
        int num_q;
        TextView txt_coin;
        ConstraintLayout constraintLayout;
        GridView gridViewAns;
        String primary,secendary;
        public static News news;
        public  static List<String>guss_q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        setUpViews();
        getIntents();
        sets();
        handle();


    }

    private void handle() {
        Game.news=new Game.News() {
            @Override
            public void clicked(String ch) {
                setAnsAdapter(ch);
            }

            @Override
            public void win(boolean w) {
                if(w){
                    Game_model gameModel=findQuestion(num_q, RecyclerLevelsItemAdapter.list_data_game);
                    DialogWin dialogWin=new DialogWin(gameModel);
                    dialogWin.show(getSupportFragmentManager(),"");
                }else {
                    Toast.makeText(getApplicationContext(),"wrong!",Toast.LENGTH_SHORT).show();

                }
            }
        };

    }

    private void sets() {
        Game_model gameModel=findQuestion(num_q, RecyclerLevelsItemAdapter.list_data_game);
        loadImage(gameModel.getImage(),3);
        //
        gridAdapter=new GridAdapter(gameModel.getAlphabets(),getApplicationContext(),primary,secendary);
        gridView.setAdapter(gridAdapter);
        //
       String[]h= gameModel.getAnswer().split("");
        gridAdapterAns=new GridAdapterAns(h,getApplicationContext(),guss_q);
        gridViewAns.setAdapter(gridAdapterAns);
        //
        setGridViewSize(relativeLayout,h.length,gridViewAns);
        //
        constraintLayout.setBackgroundColor(Color.parseColor(secendary));
        //set coins
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("shared", Context.MODE_PRIVATE);
        String coins=sharedPref.getString("coin","100");
        txt_coin.setText(coins);
    }



    void setAnsAdapter(String gss){
        guss_q.add(gss);
        Game_model gameModel=findQuestion(num_q, RecyclerLevelsItemAdapter.list_data_game);
        String[]h= gameModel.getAnswer().split("");
        if(guss_q.size()<=h.length){
            gridAdapterAns=new GridAdapterAns(h,getApplicationContext(),guss_q);
            gridViewAns.setAdapter(gridAdapterAns);
        }else {
            guss_q.clear();
            gridAdapterAns=new GridAdapterAns(h,getApplicationContext(),guss_q);
            gridViewAns.setAdapter(gridAdapterAns);
        }
    }




    private void setUpViews() {
        guss_q=new ArrayList<>();
        gridView=findViewById(R.id.gridview_gozine);
        gridViewAns=findViewById(R.id.gridview_javab);
        relativeLayout=findViewById(R.id.relativeLayout3);
        constraintLayout=findViewById(R.id.game_constraint);
        imageView_game=findViewById(R.id.image_view_game);
        txt_coin=findViewById(R.id.coin_field_game);
    }

    void setGridViewSize(RelativeLayout relativeLayout,int itemNum,GridView gridView){
     if(itemNum<=3){
         ViewGroup.LayoutParams params = relativeLayout.getLayoutParams();
         params.width = 180*3;
         relativeLayout.setLayoutParams(params);
         gridView.setNumColumns(itemNum);
     }else if(itemNum>3 && itemNum<=5){
         ViewGroup.LayoutParams params = relativeLayout.getLayoutParams();
         params.width = 260*3;
         relativeLayout.setLayoutParams(params);
         gridView.setNumColumns(itemNum);
     }else {
         ViewGroup.LayoutParams params = relativeLayout.getLayoutParams();
         params.width = 260*3;
         relativeLayout.setLayoutParams(params);
         gridView.setNumColumns(5);
     }
    }


    void getIntents(){
        Intent intent=getIntent();
        primary=intent.getStringExtra("primary");
        num_q=intent.getIntExtra("num_q",1);
        secendary=intent.getStringExtra("secendary");
    }

    Game_model  findQuestion(int num,List<Game_model>list){
        Game_model gameModel=new Game_model();
        for (int i = 0; i < list.size(); i++) {
            if(num==list.get(i).getNum()){
                    gameModel=list.get(i);
                    break;
            }
        }
        return gameModel;
    }



    void loadImage(String url,int hardness){

        Picasso.get()
                .load(url)
                .transform(new BlurTransformation(getApplicationContext(), 25, hardness))
                .into(imageView_game, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }




    public interface News{
        void clicked(String ch);
        void win(boolean w);
    }








}