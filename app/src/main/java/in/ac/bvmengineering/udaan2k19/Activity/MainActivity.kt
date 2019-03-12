package `in`.ac.bvmengineering.udaan2k19.Activity

import `in`.ac.bvmengineering.udaan2k19.DataClass.Participant
import `in`.ac.bvmengineering.udaan2k19.Misc.CustomDialog
import `in`.ac.bvmengineering.udaan2k19.R
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.tool_bar.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        tool_bar.title = "Home"
        tool_bar.setTitleTextAppearance(this, R.style.ToolbarText)

        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            val participant = Participant()
            val map = HashMap<String, String>()
            map["phone"] = PowerPreference.getDefaultFile().getString("phone")
            val jsonrequest = object : JsonObjectRequest(Request.Method.POST, getString(R.string.url_login),
                    JSONObject(map), Response.Listener {
                val x = Gson().fromJson<Participant>(it.toString(), Participant::class.java)
            }, Response.ErrorListener { }) {

            }
            participant.token = PowerPreference.getDefaultFile().getString("authToken")
            PowerPreference.getDefaultFile().put("participant", participant)
        }
        about_button.setOnClickListener {
            startActivity(Intent(applicationContext, AboutActivity::class.java))
        }

        profile_button.setOnClickListener {
            if (firebaseAuth.currentUser != null) {
                startActivity(Intent(applicationContext, ProfileActivity::class.java))
            } else {
                val x = CustomDialog(this@MainActivity, "Alert", "Please Login to continue")
                x.setOnDismissListener { startActivity(Intent(this@MainActivity, LoginActivity::class.java)) }
                x.show()
            }
        }

        events_button.setOnClickListener { startActivity(Intent(applicationContext, EventActivity::class.java)) }

    }


}