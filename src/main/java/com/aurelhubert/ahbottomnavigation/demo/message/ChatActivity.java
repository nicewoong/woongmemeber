package com.aurelhubert.ahbottomnavigation.demo.message;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.demo.R;

public class ChatActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        TextView nameTextView = (TextView)findViewById(R.id.actionBarTitle);


        Intent intent=new Intent(this.getIntent());
        String name=intent.getStringExtra("name");
        nameTextView.setText(name);
    }
}
