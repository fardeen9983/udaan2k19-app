package `in`.ac.bvmengineering.udaan2k19.Activity

import `in`.ac.bvmengineering.udaan2k19.DataClass.Participant
import `in`.ac.bvmengineering.udaan2k19.Misc.CustomDialog
import `in`.ac.bvmengineering.udaan2k19.R
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.tool_bar.*

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
            participant.token = PowerPreference.getDefaultFile().getString("token")
            PowerPreference.getDefaultFile().put("participant", participant)
        }
        about_button.setOnClickListener {
            startActivity(Intent(applicationContext, AboutActivity::class.java))
        }

        profile_button.setOnClickListener {
            if (firebaseAuth.currentUser != null) {
                startActivity(Intent(applicationContext, ProfileActivity::class.java))
            } else {
                CustomDialog(applicationContext, "Alert", "Please Login to continue").show()
            }
        }

        ar_camera.setOnClickListener {
            startActivity(Intent(applicationContext, ARFilterActivity::class.java))
        }

        ar_camera.setOnClickListener { }

        events_button.setOnClickListener { startActivity(Intent(applicationContext, EventActivity::class.java)) }
        if (firebaseAuth.currentUser !== null)
            FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
                Toast.makeText(applicationContext, it.token, Toast.LENGTH_LONG).show()
                PowerPreference.getDefaultFile().put("authToken", it.token)
            }
    }
}