package in.ac.bvmengineering.udaan2k19.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.preference.PowerPreference;

import java.util.Objects;

import in.ac.bvmengineering.udaan2k19.Misc.CustomDialog;
import in.ac.bvmengineering.udaan2k19.R;

public class MainActivity extends AppCompatActivity {
    Animation animation;
    Button eventList, profileButton, aboutButton, ARCameraButton, filterButton;
    String TAG = getClass().getSimpleName();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        animation = AnimationUtils.loadAnimation(this, R.anim.button_animation);
        aboutButton = findViewById(R.id.about_button);
        eventList = findViewById(R.id.events_button);
        profileButton = findViewById(R.id.profile_button);
        filterButton = findViewById(R.id.filter);
        ARCameraButton = findViewById(R.id.ar_camera);

        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitleTextAppearance(this, R.style.ToolbarText);
        toolbar.setTitle("Home");

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
            }
        });
        ARCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
                CustomDialog customDialog = new CustomDialog(MainActivity.this, "Coming Soon", "This feature will be available in the next update. Make sure to come back!!!");
                customDialog.show();
                //startActivity(new Intent(getApplicationContext(), AboutActivity.class));
            }
        });
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
                CustomDialog customDialog = new CustomDialog(MainActivity.this, "Coming Soon", "This feature will be available in the next update. Make sure to come back!!!");
                customDialog.show();
                //    startActivity(new Intent(getApplicationContext(), AboutActivity.class));
            }
        });
        eventList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animation);
                Intent intent = new Intent(MainActivity.this, EventCategoryActivity.class);
                startActivity(intent);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
                CustomDialog customDialog = new CustomDialog(MainActivity.this, "Coming Soon", "You need to log in with your phone no and password to access your profile");
                customDialog.show();
                //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = Objects.requireNonNull(task.getResult()).getToken();
                        PowerPreference.getDefaultFile().put("authToken", token);
                        // Log and toast
                        //Log.d(TAG, token);
                        //Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
