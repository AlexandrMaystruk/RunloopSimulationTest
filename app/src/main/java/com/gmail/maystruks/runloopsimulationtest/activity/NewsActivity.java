package com.gmail.maystruks.runloopsimulationtest.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.gmail.maystruks.runloopsimulationtest.R;

public class NewsActivity extends AppCompatActivity {


    private final static String TAG = "NEWS_DESCRIPTION";

    private Toolbar toolbar;
    private String newsDescription = null;
    private TextView tvDescription;

    public static void startActivity(Context context, String description) {

        Intent intent = new Intent(context, NewsActivity.class);
        intent.putExtra(TAG, description);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsDescription = getIntent().getStringExtra(TAG);
        toolbar = findViewById(R.id.toolbar_news);
        tvDescription = findViewById(R.id.tv_news_description_activity);
        if (!tvDescription.equals("")) {
            tvDescription.setText(newsDescription);
        }

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
