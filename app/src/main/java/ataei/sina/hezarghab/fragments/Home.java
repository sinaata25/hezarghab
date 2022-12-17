package ataei.sina.hezarghab.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ataei.sina.hezarghab.Levels;
import ataei.sina.hezarghab.R;

public class Home extends Fragment {
    View view;
    Button button_start;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.home,container,false);
       setUpViews();
       handles();
       return view;
    }

    private void handles() {
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Levels.class);
                startActivity(intent);
            }
        });
    }

    private void setUpViews() {
        button_start=view.findViewById(R.id.start_button);
    }
}
