package ataei.sina.hezarghab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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

import ataei.sina.hezarghab.adapters.RecyclerLevelsAdapter;
import ataei.sina.hezarghab.adapters.RecyclerLevelsItemAdapter;

import ataei.sina.hezarghab.models.Game_model;
import ataei.sina.hezarghab.models.Level;
import ataei.sina.hezarghab.models.User_Item;
import ataei.sina.hezarghab.statics.Keys;
import ataei.sina.hezarghab.statics.Urls;

public class LevelItems extends AppCompatActivity {

    RecyclerView recyclerView;
    String secendary;
    String name;
    int levelId;
    TextView coin_txt;
    ConstraintLayout constraintLayout,wait_level_items;
    String primary;
    TextView textView;
   public static List<Game_model>list_game_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_items);
        setUpviews();
        sets();
        getGameDataFromServer();


    }

    private void sets() {
        list_game_data=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        Intent intent = getIntent();
        primary=intent.getStringExtra("primary_color");
        secendary=intent.getStringExtra("secendary_color");
        name=intent.getStringExtra("name");
        levelId=intent.getIntExtra("id",0);
        constraintLayout.setBackgroundColor(Color.parseColor(secendary));
        textView.setText(name);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("shared", Context.MODE_PRIVATE);
        String coins=sharedPref.getString("coin","100");
        coin_txt.setText(coins);

    }

    private void setUpviews() {
        recyclerView=findViewById(R.id.recycler_item_level);
        constraintLayout=findViewById(R.id.constraint_lvel_item);
        textView=findViewById(R.id.textView6);
        wait_level_items=findViewById(R.id.constraint_wait_level_items);
        coin_txt=findViewById(R.id.coin_field_levels_item);
    }

    private void getData() {
        List<List<User_Item>>list=new ArrayList<>();
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("shared", Context.MODE_PRIVATE);
       String id= sharedPref.getString("id","");

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.url_get_level_item, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    List<User_Item>sec_list=new ArrayList<>();
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                            User_Item user_item=new User_Item();
                            user_item.setId(jsonObject.getString("id"));
                            user_item.setNum_question(jsonObject.getString("num_question"));
                            user_item.setAnswered(jsonObject.getString("answered"));
                            user_item.setStars(jsonObject.getString("stars"));
                            sec_list.add(user_item);
                            if((i+1)%3==0){
                                list.add(sec_list);
                                sec_list=new ArrayList<>();
                            }
                    }

                    recyclerView.setAdapter(new RecyclerLevelsItemAdapter(getApplicationContext(),list,primary,secendary,list_game_data));
                    wait_level_items.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
                params.put("user_id", id);
                params.put("key", Keys.key_get_level_item);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }




    String key = null;
    List<Game_model> getGameDataFromServer(){
        String url = null;
        ////handle games data from here
        if(levelId==1){
            url=Urls.url_get_khale;
            key=Keys.key_get_khale;
        }


        List<Game_model>list=new ArrayList<>();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Game_model gameModel=new Game_model();
                        gameModel.setId(jsonObject.getInt("id"));
                        gameModel.setNum(jsonObject.getInt("num"));
                        gameModel.setImage(jsonObject.getString("image"));
                        gameModel.setAnswer(jsonObject.getString("answer"));
                        String[] splited=jsonObject.getString("alphabets").split("/");
                        List<String>stringList=new ArrayList<>();
                        for (int j = 0; j < splited.length; j++) {
                            stringList.add(splited[j]);
                        }
                        gameModel.setAlphabets(stringList);
                        list.add(gameModel);
                    }
                    list_game_data=list;

                    getData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }



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
                params.put("key", key);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        return list;
    }







}