package ataei.sina.hezarghab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import ataei.sina.hezarghab.adapters.GridAdapter;

public class Game extends AppCompatActivity {
        GridAdapter gridAdapter;
        GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        setUpViews();
        sets();
    }

    private void sets() {
        List<String >list=new ArrayList<>();
        list.add("ب");list.add("پ");list.add("ک");list.add("ن");list.add("ه");
        list.add("ب");list.add("ب");list.add("ب");list.add("ب");
        list.add("ب");


        gridAdapter=new GridAdapter(list,getApplicationContext());
        gridView.setAdapter(gridAdapter);
    }

    private void setUpViews() {
        gridView=findViewById(R.id.gridview_gozine);
    }
}