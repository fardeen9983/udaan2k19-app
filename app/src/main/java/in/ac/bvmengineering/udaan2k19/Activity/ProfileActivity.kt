package `in`.ac.bvmengineering.udaan2k19.Activity

import `in`.ac.bvmengineering.udaan2k19.DataClass.Participant
import `in`.ac.bvmengineering.udaan2k19.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val participant = PowerPreference.getDefaultFile().getObject("participant", Participant::class.java) as Participant
        registered_event_list.layoutManager = LinearLayoutManager(this)

//        registered_event_list.adapter = EventListAdapter(this, participant.events!!, object : OnEventClickListener {
//            override fun onEventClick(event: Event) {
//            }
//        })

    }
}