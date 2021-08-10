package com.example.serviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnclick;
    private EditText enter;
    private BroadcastReceiver serviceReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,intent.getStringExtra("data"),Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter=new IntentFilter("com.xyz.service");
        registerReceiver(serviceReceiver,intentFilter);
        btnclick=findViewById(R.id.btnbutton);
        enter=findViewById(R.id.edtclick);
        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MyService.class);
                intent.putExtra("name",enter.getText().toString());
                startService(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(serviceReceiver);
    }
}