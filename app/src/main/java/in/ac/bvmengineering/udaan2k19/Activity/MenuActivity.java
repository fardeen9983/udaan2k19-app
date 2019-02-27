package in.ac.bvmengineering.udaan2k19.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import in.ac.bvmengineering.udaan2k19.R;

public class MenuActivity extends AppCompatActivity {

    Button eventList, profileButton, aboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        aboutButton = findViewById(R.id.about_button);
        eventList = findViewById(R.id.events_button);
        profileButton = findViewById(R.id.profile_button);

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
            }
        });
        eventList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, EventCategoryActivity.class);
                startActivity(intent);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });



    }
}
