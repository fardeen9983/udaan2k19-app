package in.ac.bvmengineering.udaan2k19.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import in.ac.bvmengineering.udaan2k19.Adapter.EventManagerAdapter;
import in.ac.bvmengineering.udaan2k19.DataClass.Event;
import in.ac.bvmengineering.udaan2k19.DataClass.Manager;
import in.ac.bvmengineering.udaan2k19.Interface.OnContactCardClickListener;
import in.ac.bvmengineering.udaan2k19.R;

public class EventDetailsActivity extends AppCompatActivity {
    TextView roundDetails;
    Event event;
    TextView fees, name, tag;
    RecyclerView managerRecyclerView;
    EventManagerAdapter eventManagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        assert getIntent() != null;
        event = (Event) getIntent().getSerializableExtra("event");
        tag = findViewById(R.id.tag_line);
        fees = findViewById(R.id.fees_text);
        name = findViewById(R.id.event_name);

        tag.setText(event.getTagLine());
        fees.setText("Entry Fees : " + event.getFees());
        name.setText(event.getName());

        managerRecyclerView = findViewById(R.id.contact_list);
        managerRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<String> rounds = event.getRounds();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < rounds.size(); i++) {
            temp.append("Round ").append(i + 1).append(":\n").append(rounds.get(i)).append("\n\n");
        }
        roundDetails = findViewById(R.id.round_text);
        roundDetails.setText(temp);
        eventManagerAdapter = new EventManagerAdapter(event.getManagers(), new OnContactCardClickListener() {
            @Override
            public void onContactCardClicked(Manager manager) {

            }
        });
        managerRecyclerView.setAdapter(eventManagerAdapter);

    }
}
