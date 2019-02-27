package in.ac.bvmengineering.udaan2k19.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import in.ac.bvmengineering.udaan2k19.R;

public class EventListActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        getIntent().getStringExtra("cat_id");
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitleTextColor(Color.parseColor("#FFE5DF2F"));
        toolbar.setTitle("Event Category");
    }
}
