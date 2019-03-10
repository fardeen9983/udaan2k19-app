package `in`.ac.bvmengineering.udaan2k19.Activity

import `in`.ac.bvmengineering.udaan2k19.Misc.CustomDialog
import `in`.ac.bvmengineering.udaan2k19.Misc.ForgotPasswordDialog
import `in`.ac.bvmengineering.udaan2k19.Misc.VolleySingleton
import `in`.ac.bvmengineering.udaan2k19.R
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.LinkMovementMethod
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.firebase.auth.FirebaseAuth
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import java.util.*


class LoginActivity : AppCompatActivity() {


    private lateinit var firebaseAuth: FirebaseAuth
    internal var TAG = javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        if (!PowerPreference.getDefaultFile().getBoolean("first_time")) {
            CustomDialog(this, "Login", "Participants of Udaan 19 will receive their passwords on their registered phone numbers").show()
            PowerPreference.getDefaultFile().put("first_time", true)
        }
        guest_button.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finishAfterTransition()
        }

        login_button.setOnClickListener {
            val phoneT = phone.text.toString()
            val passT = password.text.toString()
            if (phoneT.isEmpty())
                phone.error = "Enter a Phone no"
            else if (passT.isEmpty())
                password.error = "Password cannot be empty"
            else {
                val singleton = VolleySingleton.getInstance(applicationContext)
                val params = HashMap<String, String>()
                params["username"] = phoneT
                params["password"] = passT
                val jsonRequest = object : JsonObjectRequest(
                        Request.Method.POST,
                        resources.getString(R.string.url_login), JSONObject(params), Response.Listener { response ->
                    if (response.has("token")) {
                        val token = response.optString("token")
                        val preference = PowerPreference.getDefaultFile()

                        preference.putString("token", token)
                        //                                preference.putBoolean("loggedIn", true);
                        //Toast.makeText(getApplicationContext(), token, Toast.LENGTH_LONG).show();
                        val intent = Intent(this@LoginActivity, PhoneAuthActivity::class.java)
                        intent.putExtra("phone", "+91$phoneT")
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Unable to sign in. Invalid Credentials", Toast.LENGTH_LONG).show()
                    }
                }, Response.ErrorListener { error ->
                    error.printStackTrace()
                    // Log.e(TAG, error.getMessage());
                }) {

                }
                singleton.requestQueue.add(jsonRequest)
                singleton.requestQueue.start()
            }
        }
        forgot_password.setOnClickListener {
            ForgotPasswordDialog(this@LoginActivity).show()
        }
        forgot_password.movementMethod = LinkMovementMethod.getInstance()

    }

}
