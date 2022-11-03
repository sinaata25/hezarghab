package ataei.sina.hezarghab.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ataei.sina.hezarghab.MainFragmentsHolder;
import ataei.sina.hezarghab.R;

public class Intro3 extends Fragment {
    View view;
    Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.intro3,container,false);
        setUpViews();
        return view;
    }

    private void setUpViews() {
        button=view.findViewById(R.id.btn_lets_go_intro);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putBoolean("first_time",false);
                myEdit.commit();
                Intent intent=new Intent(getActivity(), MainFragmentsHolder.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }


}
