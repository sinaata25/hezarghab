package ataei.sina.hezarghab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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
import com.mackhartley.roundedprogressbar.RoundedProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import ataei.sina.hezarghab.adapters.RecyclerLevelsAdapter;
import ataei.sina.hezarghab.models.Level;
import ataei.sina.hezarghab.statics.Keys;
import ataei.sina.hezarghab.statics.Urls;

public class Loading extends AppCompatActivity {
    RoundedProgressBar roundedProgressBar;
    int prgrs=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        setUpViews();
        sets();

    }

    private void sets() {
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        roundedProgressBar.setProgressPercentage(prgrs,false);
                        if(prgrs==20){
                            checklogin();
                        }
                        else if(prgrs==100){
                            timer.cancel();
                            timer.purge();
                            SharedPreferences sh = getSharedPreferences("shared", Context.MODE_PRIVATE);
                            boolean h=sh.getBoolean("first_time",true);
                            if(h==false){
                                Intent intent=new Intent(getApplicationContext(),MainFragmentsHolder.class);
                                startActivity(intent);
                            }else {
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }

                            finish();
                        }
                        prgrs+=1;
                    }
                });
            }
        },30,30);




    }

   String creatRandomUser(){
        Random random=new Random();
        int result = random.nextInt(99999999-1) + 1;
        String r="guest"+result;
        return r;
    }

    void checklogin(){
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("shared", Context.MODE_PRIVATE);
        String username= sharedPref.getString("username",creatRandomUser());
        requestServer(username);

    }

   void requestServer(String user){


       StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.url_lugup, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               try {
                   String id="";
                   String username="";
                   String coin="";
                   JSONArray jsonArray=new JSONArray(response);
                   JSONObject jsonObject= (JSONObject) jsonArray.get(0);
                   id=jsonObject.getString("id");
                   username=jsonObject.getString("username");
                   coin=jsonObject.getString("coin");
                   SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("shared", Context.MODE_PRIVATE);
                   SharedPreferences.Editor editor = sharedPref.edit();
                   editor.putString("id",id);
                   editor.putString("username",username);
                   editor.putString("coin",coin);
                   editor.apply();
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
               params.put("key", Keys.key_logup);
               params.put("username",user);
               return params;
           }

       };

       RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
       requestQueue.add(stringRequest);
   }


    private void setUpViews() {
        roundedProgressBar=findViewById(R.id.progress_loading);
    }
}