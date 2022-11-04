package ataei.sina.hezarghab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import ataei.sina.hezarghab.adapters.MainPagerAdapter;
import ataei.sina.hezarghab.fragments.Home;
import ataei.sina.hezarghab.fragments.Information;
import ataei.sina.hezarghab.fragments.Profile;
import ataei.sina.hezarghab.fragments.Setting;
import ataei.sina.hezarghab.fragments.Shop;

public class MainFragmentsHolder extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    Button home,profile,info,setting,shop;
    MainPagerAdapter mainPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragments_holder);
        setUpViews();
        sets();
        handleCostumTabLayout();

    }

    private void sets() {
        mainPagerAdapter=new MainPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mainPagerAdapter.addfragment(new Setting());
        mainPagerAdapter.addfragment(new Information());
        mainPagerAdapter.addfragment(new Home());
        mainPagerAdapter.addfragment(new Shop());
        mainPagerAdapter.addfragment(new Profile());



        viewPager.setAdapter(mainPagerAdapter);
        viewPager.setCurrentItem(2);
        viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

    }




    private void setUpViews() {
    viewPager=findViewById(R.id.viewpager_main);
    tabLayout=findViewById(R.id.tablayout_main);
    home=findViewById(R.id.btn_home_home);
    profile=findViewById(R.id.btn_profile_home);
    setting=findViewById(R.id.btn_setting_home);
    info=findViewById(R.id.btn_info_home);
    shop=findViewById(R.id.btn_shop_home);
    }

void buttonSets(int i){
    if(i==0){
        setting.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),105);
        setting.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),100);
        //reset
        home.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        home.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
        shop.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        shop.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
        info.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        info.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
        profile.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        profile.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
    }else if(i==3){
        shop.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),105);
        shop.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),100);
        //reset
        home.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        home.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
        setting.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        setting.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
        info.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        info.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
        profile.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        profile.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
    }else if(i==2){
        home.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),105);
        home.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),100);
        //reset
        setting.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        setting.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
        shop.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        shop.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
        info.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        info.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
        profile.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        profile.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
    }else if(i==1){
        info.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),105);
        info.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),100);
        //reset
        home.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        home.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
        shop.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        shop.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
        setting.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        setting.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
        profile.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        profile.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
    }else if(i==4){
        profile.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),105);
        profile.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),100);
        //reset
        home.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        home.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
        shop.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        shop.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
        info.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        info.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
        setting.getLayoutParams().height= (int) pxFromDp(getApplicationContext(),70);
        setting.getLayoutParams().width=(int) pxFromDp(getApplicationContext(),65);
    }
}
    private void click(int i){
        viewPager.setCurrentItem(i);
        buttonSets(i);

    }


    private void handleCostumTabLayout(){
        profile.setOnClickListener(V->click(4));
        shop.setOnClickListener(V->click(3));
        home.setOnClickListener(V->click(2));
        info.setOnClickListener(V->click(1));
        setting.setOnClickListener(V->click(0));

    }


    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }



}