package ataei.sina.hezarghab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import ataei.sina.hezarghab.models.Level;
import ataei.sina.hezarghab.models.User_Item;
import ataei.sina.hezarghab.statics.Keys;
import ataei.sina.hezarghab.statics.Urls;

public class LevelItems extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_items);
        setUpviews();
        sets();
        getData();
    }

    private void sets() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));

    }

    private void setUpviews() {
        recyclerView=findViewById(R.id.recycler_item_level);
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
                        System.out.println(list);
                    }
                    System.out.println(list);
                    recyclerView.setAdapter(new RecyclerLevelsItemAdapter(getApplicationContext(),list));

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
}