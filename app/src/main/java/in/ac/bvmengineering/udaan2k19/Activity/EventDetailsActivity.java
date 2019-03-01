package in.ac.bvmengineering.udaan2k19.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import in.ac.bvmengineering.udaan2k19.Adapter.EventManagerAdapter;
import in.ac.bvmengineering.udaan2k19.DataClass.Event;
import in.ac.bvmengineering.udaan2k19.R;

public class EventDetailsActivity extends AppCompatActivity {
    TextView roundDetails;
    Event event;
    TextView fees, name, tag;
    RecyclerView managerRecyclerView;
    EventManagerAdapter eventManagerAdapter;
    TextView instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        assert getIntent() != null;
        event = (Event) getIntent().getSerializableExtra("event");
        tag = findViewById(R.id.tag_line);
        fees = findViewById(R.id.fees_text);
        name = findViewById(R.id.event_name);
        instructions = findViewById(R.id.intsructions);
        tag.setText(event.getTagLine());
        String f = "Entry Fees : " + event.getFees() + "/" + event.getTeamSize();
        fees.setText(f);
        name.setText(event.getName());

        managerRecyclerView = findViewById(R.id.contact_list);
        managerRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        roundDetails = findViewById(R.id.round_text);
        ArrayList<String> rounds = event.getRounds();
        StringBuilder temp = new StringBuilder();
        if (rounds.size() == 0) {
            instructions.setVisibility(View.GONE);
            roundDetails.setVisibility(View.GONE);
        } else {
            for (int i = 0; i < rounds.size(); i++) {
                temp.append("Round ").append(i + 1).append(":\n").append(rounds.get(i)).append("\n\n");
            }
            roundDetails.setText(temp);
        }

        eventManagerAdapter = new EventManagerAdapter(event.getManagers());
        managerRecyclerView.setAdapter(eventManagerAdapter);

    }
}
