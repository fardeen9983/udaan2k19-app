package in.ac.bvmengineering.udaan2k19.Activity;

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
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import in.ac.bvmengineering.udaan2k19.DataClass.Developer;
import in.ac.bvmengineering.udaan2k19.DataClass.Event;
import in.ac.bvmengineering.udaan2k19.DataClass.EventCategory;
import in.ac.bvmengineering.udaan2k19.DataClass.Manager;
import in.ac.bvmengineering.udaan2k19.Misc.Constant;
import in.ac.bvmengineering.udaan2k19.R;

public class IntroActivity extends AppCompatActivity {

    final String TAG = getClass().getSimpleName();
    ImageView imageView;
    JSONArray json;
    EventCategory builder, chamber, halfwave, scamander, mad, ohms, automative, workshop;
    ArrayList<Developer> developers;
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

                if (PowerPreference.getDefaultFile().getMap("events", HashMap.class, String.class, Event.class) == null) {
                    try {
                        //Reading from events.json file
                        InputStream is = getApplication().getAssets().open("events.json");
                        byte[] buffer = new byte[is.available()];
                        is.read(buffer);
                        is.close();
                        json = new JSONArray(new String(buffer));
                        Log.v(TAG, json.toString());
                        HashMap<String, Event> events = new HashMap<>();
                        //serializing event details into objects
                        ArrayList<EventCategory> eventCategories = new ArrayList<>();
                        workshop = new EventCategory(R.drawable.goblet_of_workshop, getString(R.string.cat_workshop));
                        builder = new EventCategory(R.drawable.builder_of_azkaban, getResources().getString(R.string.cat_civil));
                        chamber = new EventCategory(R.drawable.chamber_of_coders, getResources().getString(R.string.cat_cp_it));
                        halfwave = new EventCategory(R.drawable.half_wave_prince, getResources().getString(R.string.cat_ec_el));
                        scamander = new EventCategory(R.drawable.scamander_suitcase, getResources().getString(R.string.cat_non_tech));
                        mad = new EventCategory(R.drawable.mad_hollows, getResources().getString(R.string.cat_cultural));
                        ohms = new EventCategory(R.drawable.order_of_ohms, getResources().getString(R.string.cat_ee));
                        automative = new EventCategory(R.drawable.automotive_philosopher, getResources().getString(R.string.cat_mech_prod));
                        for (int i = 0; i < json.length(); i++) {
                            Event event = new Gson().fromJson(json.optJSONObject(i).toString(), Event.class);
                            for (int j = 0; j < Constant.EVENT_CATEGORIES.length; j++) {
                                if (event.getDept().equals(Constant.EVENT_CATEGORIES[j])) {
                                    save(event, j);
                                    break;
                                }
                            }
                            events.put(event.getId(), event);
                            Log.v(TAG, event.toString());
                        }

                        is = getAssets().open("head.json");
                        buffer = new byte[is.available()];
                        is.read(buffer);
                        is.close();
                        JSONObject head = new JSONObject(new String(buffer));
                        for (String string : Constant.EVENT_CATEGORIES) {
                            if (string.equals(Constant.SCAMANDERS_SUITCASE) || string.equals(Constant.GOBLET_OF_WORKSHOPS))
                                continue;
                            JSONArray array = head.optJSONArray(string);
                            for (int i = 0; i < array.length(); i++) {
                                Manager manager = new Gson().fromJson(array.optJSONObject(i).toString(), Manager.class);
                                saveHead(manager, string);
                            }
                        }
                        eventCategories.add(builder);
                        eventCategories.add(automative);
                        eventCategories.add(chamber);
                        eventCategories.add(halfwave);
                        eventCategories.add(mad);
                        eventCategories.add(scamander);
                        eventCategories.add(ohms);
                        eventCategories.add(workshop);
                        PowerPreference.getDefaultFile().put("eventCategories", eventCategories);
                        //storing event details
                        PowerPreference.getDefaultFile().put("events", events);

                        developers = new ArrayList<>();
                        is = getAssets().open("developer.json");
                        buffer = new byte[is.available()];
                        is.read(buffer);
                        is.close();
                        JSONObject temp = new JSONObject(new String(buffer));
                        for (String string : Constant.DEVELOPERS) {
                            JSONArray array = temp.optJSONArray(string);
                            for (int i = 0; i < array.length(); i++) {
                                Developer developer = new Gson().fromJson(array.optJSONObject(i).toString(), Developer.class);
                                switch (string) {
                                    case Constant.DEVELOPER_HEAD:
                                        developer.setType(Constant.DEVELOPER_HEAD);
                                        developer.setImageID(R.drawable.programming_icon);
                                        break;
                                    case Constant.ANDROID_DEVELOPER:
                                        developer.setType(Constant.ANDROID_DEVELOPER);
                                        developer.setImageID(R.drawable.android_developer_logo);
                                        break;
                                    case Constant.UI_DESIGNER:
                                        developer.setType(Constant.UI_DESIGNER);
                                        developer.setImageID(R.drawable.graphicsdesigner_icon);
                                        break;
                                    case Constant.WEB_DEVELOPER:
                                        developer.setType(Constant.WEB_DEVELOPER);
                                        developer.setImageID(R.drawable.programming_icon);
                                        break;
                                }
                                developers.add(developer);
                            }
                        }
                        PowerPreference.getDefaultFile().put("developers", developers);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
                            finishAfterTransition();
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

    private void save(Event event, int i) {
        switch (i) {
            case 0:
                builder.getEvents().add(event);
                break;
            case 1:
                automative.getEvents().add(event);
                break;
            case 2:
                chamber.getEvents().add(event);
                break;
            case 3:
                halfwave.getEvents().add(event);
                break;
            case 4:
                mad.getEvents().add(event);
                break;
            case 5:
                scamander.getEvents().add(event);
                break;
            case 6:
                ohms.getEvents().add(event);
                break;
            case 7:
                workshop.getEvents().add(event);
                break;
        }
    }

    private void saveHead(Manager manager, String i) {
        switch (i) {
            case Constant.BUILDER_OF_AZKABAN:
                builder.getManagers().add(manager);
                break;
            case Constant.AUTOMOTIVE_PHILOSOPHER:
                automative.getManagers().add(manager);
                break;
            case Constant.CHAMBER_OF_CODERS:
                chamber.getManagers().add(manager);
                break;
            case Constant.HALF_WAVE_PRINCE:
                halfwave.getManagers().add(manager);
                break;
            case Constant.MAD_HOLLOWS:
                mad.getManagers().add(manager);
                break;
            case Constant.ORDER_OF_OHMS:
                ohms.getManagers().add(manager);
                break;
        }
    }
}
