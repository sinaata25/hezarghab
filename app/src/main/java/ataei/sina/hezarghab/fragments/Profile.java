package ataei.sina.hezarghab.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ataei.sina.hezarghab.R;

public class Profile extends Fragment {
    View view;
    EditText editText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.profile,container,false);
       setupViews();
       sets();
       return view;
    }

    private void sets() {
        SharedPreferences sharedPref = getContext().getSharedPreferences("shared", Context.MODE_PRIVATE);
        String user=sharedPref.getString("username","");
        editText.setText(user);
    }

    private void setupViews() {
        editText=view.findViewById(R.id.editTextuser);
    }
}
