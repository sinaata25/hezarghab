package ataei.sina.hezarghab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import ataei.sina.hezarghab.adapters.IntroPagerAdapter;
import ataei.sina.hezarghab.fragments.Intro1;
import ataei.sina.hezarghab.fragments.Intro2;
import ataei.sina.hezarghab.fragments.Intro3;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    IntroPagerAdapter introPagerAdapter;
    DotsIndicator dotsIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        sets();
    }

    private void sets() {
        introPagerAdapter=new IntroPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        introPagerAdapter.addfragment(new Intro1());
        introPagerAdapter.addfragment(new Intro2());
        introPagerAdapter.addfragment(new Intro3());
        viewPager.setAdapter(introPagerAdapter);
        dotsIndicator.attachTo(viewPager);
    }

    private void setUpViews() {
        viewPager=findViewById(R.id.viewpager_intro);
        dotsIndicator=findViewById(R.id.dots_indicator_intro);
    }
}