package ataei.sina.hezarghab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import ataei.sina.hezarghab.adapters.GridAdapter;
import ataei.sina.hezarghab.adapters.GridAdapterAns;

public class Game extends AppCompatActivity {
        GridAdapter gridAdapter;
        RelativeLayout relativeLayout;
        GridAdapterAns gridAdapterAns;
        GridView gridView;
        GridView gridViewAns;
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
        //
        List<String >list1=new ArrayList<>();
        list1.add("ب");list1.add("پ");list1.add("ک");
        list1.add("ک");

        gridAdapterAns=new GridAdapterAns(list1,getApplicationContext());
        gridViewAns.setAdapter(gridAdapterAns);
        //
            setGridViewSize(relativeLayout,list1.size(),gridViewAns);
    }

    private void setUpViews() {
        gridView=findViewById(R.id.gridview_gozine);
        gridViewAns=findViewById(R.id.gridview_javab);
        relativeLayout=findViewById(R.id.relativeLayout3);
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

}