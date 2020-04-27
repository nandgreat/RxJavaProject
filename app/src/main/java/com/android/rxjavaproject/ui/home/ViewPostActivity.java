package com.android.rxjavaproject.ui.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.rxjavaproject.R;
import com.android.rxjavaproject.model.Post;
import com.google.gson.Gson;

public class ViewPostActivity extends AppCompatActivity {

    private static final String TAG = "ViewPostActivity";

    private TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);
        text = findViewById(R.id.title);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Gson gson = new Gson();

        if(getIntent().hasExtra("post")){
            Post post = gson.fromJson(getIntent().getStringExtra("post"), Post.class);
            Log.d(TAG, "getIncomingIntent: "+post.getTitle());
            text.setText(post.getTitle());
        }
    }
}
