package `in`.ac.bvmengineering.udaan2k19.Activity

import `in`.ac.bvmengineering.udaan2k19.DataClass.EventCategory
import `in`.ac.bvmengineering.udaan2k19.DataClass.Participant
import `in`.ac.bvmengineering.udaan2k19.Misc.CustomDialog
import `in`.ac.bvmengineering.udaan2k19.R
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.tool_bar.*

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private val PERMISSION_REQUEST_CODE = 0x01
    private val permissions = arrayOf(Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        for (x in permissions) {
            if (ActivityCompat.checkSelfPermission(this, x) != PackageManager.PERMISSION_GRANTED)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permissions, PERMISSION_REQUEST_CODE)
                } else {

                }
        }
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
                val x = CustomDialog(this@MainActivity, "Alert", "Please Login to continue")
                x.setOnDismissListener { startActivity(Intent(this@MainActivity, LoginActivity::class.java)) }
                x.show()
            }
        }

        ar_camera.setOnClickListener {
            startActivity(Intent(applicationContext, ARFilterActivity::class.java))
        }

        ar_camera.setOnClickListener { startActivity(Intent(this@MainActivity, ARCameraActivity::class.java)) }

        events_button.setOnClickListener { startActivity(Intent(applicationContext, EventActivity::class.java)) }
        if (firebaseAuth.currentUser !== null)
            FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
                //Toast.makeText(applicationContext, it.token, Toast.LENGTH_LONG).show()
                var x = 0
                PowerPreference.getDefaultFile().put("authToken", it.token)
                val temp: Array<EventCategory> = PowerPreference.getDefaultFile().getObject("eventCategories", Array<EventCategory>::class.java)
                for (i in temp) {
                    for (j in i.events) {
                        FirebaseMessaging.getInstance().subscribeToTopic(j.id)
                                .addOnCompleteListener {
                                    x++
                                }
                    }
                }
                Toast.makeText(this@MainActivity, "events : $x", Toast.LENGTH_LONG).show()
            }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                var granted = true
                for (x in grantResults) {
                    if (x != PackageManager.PERMISSION_GRANTED) {
                        granted = false
                        break
                    }
                }
                if (granted)
                    Toast.makeText(this, "Permissions Successfully Granted", Toast.LENGTH_LONG).show()
                else {
                    Toast.makeText(this, "Requesting permissions again", Toast.LENGTH_SHORT).show()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(permissions, PERMISSION_REQUEST_CODE)
                    }
                }
            }
        }
    }
}