package com.example.recycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button myButton ;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private ArrayList<DataModel> dataSet;

    private CustomAdapter adapter;

    private MediaPlayer song = null;

    private Boolean isPause = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.myButton = findViewById(R.id.btn_pause);
        recyclerView = findViewById(R.id.res);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        dataSet = new ArrayList<>();

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        for(int i = 0 ; i < MyData.drawableArray.length ; i++){
            dataSet.add(new DataModel(
                    MyData.nameArray[i],
                    MyData.versionArray[i],
                    MyData.id_[i],
                    MyData.drawableArray[i]
            ));
        }

        adapter = new CustomAdapter(dataSet);
        recyclerView.setAdapter(adapter);

        song = MediaPlayer.create(this, R.raw.sponge_bob_theme);
        song.setLooping(true);
        song.start();

    }

    public void funcPause(View view) {

        if(song != null )
        {
            if(isPause)
            {
                myButton.setBackgroundResource(R.drawable.unpause);
                song.setVolume(0.0f, 0.0f);
            }
            else
            {
                myButton.setBackgroundResource(R.drawable.pause);
                song.setVolume(1.0f, 1.0f);
            }
        }

        isPause = !isPause;
    }
}