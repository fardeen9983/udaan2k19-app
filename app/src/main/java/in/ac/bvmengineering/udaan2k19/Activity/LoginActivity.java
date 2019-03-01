package in.ac.bvmengineering.udaan2k19.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import in.ac.bvmengineering.udaan2k19.Misc.CustomDialog;
import in.ac.bvmengineering.udaan2k19.R;

public class LoginActivity extends AppCompatActivity {

    Button login, guest;
    TextView forgotPassword;
    EditText phone, password;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.login_button);
        forgotPassword = findViewById(R.id.forgot_password);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        guest = findViewById(R.id.guest_button);

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finishAfterTransition();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog customDialog = new CustomDialog(LoginActivity.this, "Coming Soon", "This feature will be available in the next update. Make sure to come back!!!");
                customDialog.show();
//                final String phoneT = phone.getText().toString();
//                String passT = password.getText().toString();
//                if (phoneT.isEmpty())
//                    phone.setError("Enter a Phone no");
//                else if (passT.isEmpty())
//                    password.setError("Password cannot be empty");
//                else {
//                    VolleySingleton singleton = VolleySingleton.getInstance(getApplicationContext());
//                    HashMap<String, String> params = new HashMap<>();
//                    params.put("username", phoneT);
//                    params.put("password", passT);
//                    JsonObjectRequest jsonRequest = new JsonObjectRequest(
//                            Request.Method.POST,
//                            getResources().getString(R.string.url_login), new JSONObject(params), new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            if (response.has("token")) {
//                                String token = response.optString("token");
//                                Preference preference = PowerPreference.getDefaultFile();
//                                preference.putString("token", token);
//                                preference.putBoolean("loggedIn", false);
//                                //Toast.makeText(getApplicationContext(), token, Toast.LENGTH_LONG).show();
//                                Intent intent = new Intent(LoginActivity.this, PhoneAuthActivity.class);
//                                intent.putExtra("phone", "+91" + phoneT);
//                                startActivity(intent);
//                                finish();
//                            } else {
//                                Toast.makeText(getApplicationContext(), "Unable to sign in. Invalid Credentials", Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            error.printStackTrace();
//
//                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
//                        }
//                    }) {
//
//                    };
//                    singleton.getRequestQueue().add(jsonRequest);
//                    singleton.getRequestQueue().start();
//                }
            }
        });

        SpannableString ss = new SpannableString("Forgot Password?");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
//                startActivity(intent);
            }
        };
        ss.setSpan(clickableSpan, 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        forgotPassword.setText(ss);
        forgotPassword.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
