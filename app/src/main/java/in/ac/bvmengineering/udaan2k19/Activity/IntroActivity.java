package in.ac.bvmengineering.udaan2k19.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.preference.PowerPreference;

import org.json.JSONArray;

import in.ac.bvmengineering.udaan2k19.DataClass.EventCategory;
import in.ac.bvmengineering.udaan2k19.R;
import in.ac.bvmengineering.udaan2k19.Thread.LoadFromJSON;

public class IntroActivity extends AppCompatActivity {

    final String TAG = getClass().getSimpleName();
    ImageView imageView;
    JSONArray json;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        PowerPreference.getDefaultFile().setDefaults(R.xml.default_preferences);
        imageView = findViewById(R.id.introImage);

        firebaseAuth = FirebaseAuth.getInstance();
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.welcome_animation);
        imageView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                if (PowerPreference.getDefaultFile().getObject("eventCategories", EventCategory[].class) == null) {
                    new LoadFromJSON().execute(getApplicationContext());
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
                            if (firebaseAuth.getCurrentUser() != null)
                                intent = new Intent(getApplicationContext(), MainActivity.class);
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


}
