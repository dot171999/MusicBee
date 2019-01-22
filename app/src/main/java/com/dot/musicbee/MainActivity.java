package com.dot.musicbee;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    static String IP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
    }

    public void send_IP(View view){
        IP=editText.getText().toString();
        Intent intent=new Intent(MainActivity.this,Screen.class);
        startActivity(intent);
    }
}
