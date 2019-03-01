package in.ac.bvmengineering.udaan2k19.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import in.ac.bvmengineering.udaan2k19.Adapter.EventListAdapter;
import in.ac.bvmengineering.udaan2k19.Adapter.EventManagerAdapter;
import in.ac.bvmengineering.udaan2k19.DataClass.Event;
import in.ac.bvmengineering.udaan2k19.DataClass.EventCategory;
import in.ac.bvmengineering.udaan2k19.Interface.OnEventClickListener;
import in.ac.bvmengineering.udaan2k19.R;

public class EventListActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    ArrayList<Event> events = new ArrayList<>();
    RecyclerView eventListRecyclerView;
    EventListAdapter eventListAdapter;
    EventCategory category;
    EventManagerAdapter eventManagerAdapter;
    RecyclerView eventCatHeadList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        assert getIntent() != null;
        category = (EventCategory) getIntent().getSerializableExtra("cat");
        events = category.getEvents();
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitleTextAppearance(this, R.style.ToolbarText);
        toolbar.setTitle(category.getName());


        eventCatHeadList = findViewById(R.id.event_head_list);
        if (category.getManagers().size() == 0) {
            View temp = findViewById(R.id.head_ralative_layout);
            temp.setVisibility(View.GONE);
        } else {
            eventManagerAdapter = new EventManagerAdapter(category.getManagers());
            eventCatHeadList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
            eventCatHeadList.setAdapter(eventManagerAdapter);

        }
        eventListRecyclerView = findViewById(R.id.event_list);
        eventListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        eventListRecyclerView.addItemDecoration(dividerItemDecoration);

        eventListAdapter = new EventListAdapter(this, events, new OnEventClickListener() {
            @Override
            public void onEventClick(Event event) {
                Intent intent = new Intent(getApplicationContext(), EventDetailsActivity.class);
                intent.putExtra("event", event);
                startActivity(intent);
            }
        });

        eventListRecyclerView.setAdapter(eventListAdapter);

    }
}
