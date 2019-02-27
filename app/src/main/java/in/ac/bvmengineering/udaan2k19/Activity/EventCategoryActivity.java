package in.ac.bvmengineering.udaan2k19.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.preference.PowerPreference;

import java.util.ArrayList;
import java.util.Arrays;

import in.ac.bvmengineering.udaan2k19.Adapter.EventCategoryAdapter;
import in.ac.bvmengineering.udaan2k19.DataClass.EventCategory;
import in.ac.bvmengineering.udaan2k19.Interface.OnCategoryItemClickListener;
import in.ac.bvmengineering.udaan2k19.Misc.BottomPanel;
import in.ac.bvmengineering.udaan2k19.R;

public class EventCategoryActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    BottomPanel bottomPanel;
    Dialog bottomPanelView;
    RecyclerView eventCategoryRecyclerView;
    ArrayList<EventCategory> eventCategoryArrayList = new ArrayList<>();
    EventCategoryAdapter eventCategoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_category);

        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitleTextColor(Color.parseColor("#FFE5DF2F"));
        toolbar.setTitle("Event Category");
        setSupportActionBar(toolbar);

        eventCategoryRecyclerView = findViewById(R.id.event_catagory_list);
        eventCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        eventCategoryRecyclerView.addItemDecoration(dividerItemDecoration);

        EventCategory[] events = PowerPreference.getDefaultFile().getObject("eventCategories", EventCategory[].class, null);
        eventCategoryArrayList.addAll(Arrays.asList(events));
        eventCategoryAdapter = new EventCategoryAdapter(EventCategoryActivity.this, eventCategoryArrayList, new OnCategoryItemClickListener() {
            @Override
            public void onCategoryItemClick(EventCategory eventCategory) {
                Intent intent = new Intent(getApplicationContext(), EventListActivity.class);
                intent.putExtra("cat", eventCategory);
                startActivity(intent);
            }
        });
        eventCategoryRecyclerView.setAdapter(eventCategoryAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tool, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.more_option:
                bottomPanel = new BottomPanel(EventCategoryActivity.this);
                bottomPanelView = bottomPanel.showDialog();
                bottomPanelView.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
