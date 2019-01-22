package com.dot.musicbee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Songs extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
        textView=findViewById(R.id.textView);
        textView.setText(Client.arrayList.get(0)+"    "+Client.arrayList.get(1));
    }
}
