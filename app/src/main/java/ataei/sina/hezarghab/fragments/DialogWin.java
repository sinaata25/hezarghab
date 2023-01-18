package ataei.sina.hezarghab.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.squareup.picasso.Picasso;

import ataei.sina.hezarghab.Game;
import ataei.sina.hezarghab.R;
import ataei.sina.hezarghab.models.Game_model;

public class DialogWin extends DialogFragment {
    Game_model gameModel;
    View view;
    ImageView imageView;
    TextView txt;
    Button btn,exit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dialog_win,container,false);
        setUpViews();
        sets();
        handle();
        return view;
    }

    private void handle() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.news.nextRound();
                dismiss();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Game.news.exit();

            }
        });
    }



    private void sets() {
        Picasso.get()
                .load(gameModel.getImage())
                .fit()
                .into(imageView);

        txt.setText(gameModel.getAnswer());
    }

    public DialogWin(Game_model gameModel){
        this.gameModel=gameModel;

    }
    void setUpViews(){
        imageView=view.findViewById(R.id.win_imgview);
        txt=view.findViewById(R.id.text_dialog);
        btn=view.findViewById(R.id.win_next);
        exit=view.findViewById(R.id.exit_win);
    }


}
