package `in`.ac.bvmengineering.udaan2k19.Activity

import `in`.ac.bvmengineering.udaan2k19.Misc.CustomDialog
import `in`.ac.bvmengineering.udaan2k19.R
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {


    private lateinit var firebaseAuth: FirebaseAuth
    internal var TAG = javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        if (!PowerPreference.getDefaultFile().getBoolean("first_time")) {
            CustomDialog(this, "Login", "Participants of Udaan 19 need to just provide their phone number for logging in.").show()
            PowerPreference.getDefaultFile().put("first_time", true)
        }
        guest_button.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finishAfterTransition()
        }

        login_button.setOnClickListener {
            val phoneT = phone.text.toString()

            if (phoneT.isEmpty())
                phone.error = "Enter a Phone no"
            else {
//                val singleton = VolleySingleton.getInstance(applicationContext)
//                val params = HashMap<String, String>()
//                params["username"] = phoneT
//                params["password"] = passT
//                val jsonRequest = object : JsonObjectRequest(
//                        Request.Method.POST,
//                        resources.getString(R.string.url_login), JSONObject(params), Response.Listener { response ->
//                    if (response.has("token")) {
//                        val token = response.optString("token")
//                        val preference = PowerPreference.getDefaultFile()
//
//                        preference.putString("token", token)
//                        //                                preference.putBoolean("loggedIn", true);
//                        //Toast.makeText(getApplicationContext(), token, Toast.LENGTH_LONG).show();
                val intent = Intent(this@LoginActivity, PhoneAuthActivity::class.java)
                intent.putExtra("phone", "+91$phoneT")
                PowerPreference.getDefaultFile().put("phone", phoneT)
                startActivity(intent)
                finish()
//                    } else {
//                        Toast.makeText(applicationContext, "Unable to sign in. Invalid Credentials", Toast.LENGTH_LONG).show()
//                    }
//                }, Response.ErrorListener { error ->
//                    error.printStackTrace()
//                    // Log.e(TAG, error.getMessage());
//                }) {
//
//                }
//                singleton.requestQueue.add(jsonRequest)
//                singleton.requestQueue.start()
            }
        }


    }

}
