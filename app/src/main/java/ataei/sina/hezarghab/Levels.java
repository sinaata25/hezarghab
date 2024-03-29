package ataei.sina.hezarghab;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
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
import ataei.sina.hezarghab.models.Level;
import ataei.sina.hezarghab.statics.Keys;
import ataei.sina.hezarghab.statics.Urls;

public class Levels extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Level>list_levels;

    ConstraintLayout constraintLayout_wait,constraintLayout_level;
    TextView textView_coins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);
        setUpViews();
        sets();
        getData();
    }

    private void sets() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("shared", Context.MODE_PRIVATE);
        String coins=sharedPref.getString("coin","100");
        textView_coins.setText(coins);
    }

    private void getData() {
        list_levels=new ArrayList<>();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.url_get_all_levels, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Level level=new Level();
                        JSONObject jsonObject= (JSONObject) jsonArray.get(i);
                        level.setId(jsonObject.getInt("id"));
                        level.setName(jsonObject.getString("name"));
                        level.setImg(jsonObject.getString("image"));
                        level.setColor_primary(jsonObject.getString("primary_color"));
                        level.setColor_secendary(jsonObject.getString("secendary_color"));
                        list_levels.add(level);
                    }

                    recyclerView.setAdapter(new RecyclerLevelsAdapter(getApplicationContext(),list_levels));
                    constraintLayout_wait.setVisibility(View.GONE);
                    constraintLayout_level.setVisibility(View.VISIBLE);


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
                params.put("key", Keys.key_get_all_levels);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void setUpViews() {
        recyclerView=findViewById(R.id.recycler_levels);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        textView_coins=findViewById(R.id.coin_field_levels);
        constraintLayout_level=findViewById(R.id.constraint_level);
        constraintLayout_wait=findViewById(R.id.constraint_wait_level);
    }

}