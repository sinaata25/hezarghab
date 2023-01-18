package ataei.sina.hezarghab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
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
        int num_wrong=0;
        TextView txt_coin;
        int levelId=0;
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
                    addWinIntoServer();
                    Game_model gameModel=findQuestion(num_q, RecyclerLevelsItemAdapter.list_data_game);
                    DialogWin dialogWin=new DialogWin(gameModel);
                    dialogWin.show(getSupportFragmentManager(),"");
                }else {
                    num_wrong++;

                }
            }

            @Override
            public void nextRound() {
                num_q++;
                guss_q.clear();
                setUpNewGame();
            }

            @Override
            public void exit() {
                finish();
            }

            @Override
            public void clean() {
                if(guss_q.size()!=0){
                guss_q.remove(guss_q.size()-1);
                Game_model gameModel=findQuestion(num_q, RecyclerLevelsItemAdapter.list_data_game);
                String[]h= gameModel.getAnswer().split("");
                gridAdapterAns=new GridAdapterAns(h,getApplicationContext(),guss_q);
                gridViewAns.setAdapter(gridAdapterAns);
                //
                setGridViewSize(relativeLayout,h.length,gridViewAns);
            }}
        };

    }

    private void sets() {
        setUpNewGame();
        //
        constraintLayout.setBackgroundColor(Color.parseColor(secendary));
        //set coins
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("shared", Context.MODE_PRIVATE);
        String coins=sharedPref.getString("coin","100");
        txt_coin.setText(coins);
    }

    void  setUpNewGame(){
        Game_model gameModel=findQuestion(num_q, RecyclerLevelsItemAdapter.list_data_game);
        loadImage(gameModel.getImage(),1);
        //
        gridAdapter=new GridAdapter(gameModel.getAlphabets(),getApplicationContext(),primary,secendary);
        gridView.setAdapter(gridAdapter);
        //
        String[]h= gameModel.getAnswer().split("");
        gridAdapterAns=new GridAdapterAns(h,getApplicationContext(),guss_q);
        gridViewAns.setAdapter(gridAdapterAns);
        //
        setGridViewSize(relativeLayout,h.length,gridViewAns);
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

    void addWinIntoServer(){
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("shared", Context.MODE_PRIVATE);
        int id_user=Integer.parseInt(sharedPref.getString("id","0"));
        int star=set_star();
        int num_question=num_q;


            StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.url_update_user_level, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                LevelItems.levInterface.refresh();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "خطا در اتصال به سرور", Toast.LENGTH_SHORT).show();
                }
            }){
                Map<String, String> params;
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    params = new HashMap<>();
                    params.put("id_user", String.valueOf(id_user));
                    params.put("num_q", String.valueOf(num_question));
                    params.put("mode", String.valueOf(levelId));
                    params.put("star", String.valueOf(star));
                    params.put("key", Keys.key_update_user_level);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

    }

    int set_star(){
        int str=0;
        if(num_wrong<=2){
            str=3;
        }else if(num_wrong>2 && num_wrong<=6){
            str=2;
        }else {
            str=1;
        }
        return str;
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
        levelId=intent.getIntExtra("levelId",0);
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
        void nextRound();
        void exit();
        void clean();
    }








}