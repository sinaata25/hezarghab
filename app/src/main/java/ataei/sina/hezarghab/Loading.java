package ataei.sina.hezarghab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.mackhartley.roundedprogressbar.RoundedProgressBar;

import java.util.Timer;
import java.util.TimerTask;

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
                        if(prgrs==100){
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

    private void setUpViews() {
        roundedProgressBar=findViewById(R.id.progress_loading);
    }
}