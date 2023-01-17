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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
import ataei.sina.hezarghab.models.Game_model;
import ataei.sina.hezarghab.models.User_Item;
import ataei.sina.hezarghab.statics.Keys;
import ataei.sina.hezarghab.statics.Urls;

public class Game extends AppCompatActivity {
        GridAdapter gridAdapter;
        RelativeLayout relativeLayout;
        GridAdapterAns gridAdapterAns;
        GridView gridView;
        int num_q;
        ConstraintLayout constraintLayout;
        GridView gridViewAns;
        String primary,secendary;
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


    }

    private void sets() {
        Game_model gameModel=findQuestion(num_q, RecyclerLevelsItemAdapter.list_data_game);
        Toast.makeText(getApplicationContext(),gameModel.getAnswer(),Toast.LENGTH_SHORT).show();

        gridAdapter=new GridAdapter(gameModel.getAlphabets(),getApplicationContext(),primary,secendary);
        gridView.setAdapter(gridAdapter);
        //
       String[]h= gameModel.getAnswer().split("");
        gridAdapterAns=new GridAdapterAns(h,getApplicationContext());
        gridViewAns.setAdapter(gridAdapterAns);
        //
        setGridViewSize(relativeLayout,h.length,gridViewAns);
        //
        constraintLayout.setBackgroundColor(Color.parseColor(secendary));

    }




    private void setUpViews() {
        gridView=findViewById(R.id.gridview_gozine);
        gridViewAns=findViewById(R.id.gridview_javab);
        relativeLayout=findViewById(R.id.relativeLayout3);
        constraintLayout=findViewById(R.id.game_constraint);
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


}