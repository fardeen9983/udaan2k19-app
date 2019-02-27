package in.ac.bvmengineering.udaan2k19;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.preference.PowerPreference;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import in.ac.bvmengineering.udaan2k19.DataClass.Event;

public class IntroActivity extends AppCompatActivity {

    ImageView imageView;
    final String TAG = getClass().getSimpleName();
    JSONArray json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        PowerPreference.getDefaultFile().setDefaults(R.xml.default_preferences);
        imageView = findViewById(R.id.introImage);


        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.welcome_animation);
        imageView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                try {
                    InputStream is = getApplication().getAssets().open("events.json");
                    byte[] buffer = new byte[is.available()];
                    is.read(buffer);
                    is.close();
                    json = new JSONArray(new String(buffer));
                    //Toast.makeText(getApplicationContext(),json.toString(),Toast.LENGTH_LONG).show();
                    Log.v(TAG, json.toString());
                    HashMap<String, Event> events = new HashMap<>();
                    for (int i = 0; i < json.length(); i++) {
                        Event event = new Gson().fromJson(json.optJSONObject(i).toString(), Event.class);
                        events.put(event.getId(), event);
                        Log.v(TAG, event.toString());
                    }
                    PowerPreference.getDefaultFile().put("events", events);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            Thread.sleep(500);
                            Intent intent;
                            if (PowerPreference.getDefaultFile().getBoolean("loggedIn"))
                                intent = new Intent(getApplicationContext(), EventCategoryActivity.class);
                            else
                                intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
