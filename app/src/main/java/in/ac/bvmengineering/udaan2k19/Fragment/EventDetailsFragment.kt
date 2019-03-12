package `in`.ac.bvmengineering.udaan2k19.Fragment

import `in`.ac.bvmengineering.udaan2k19.Adapter.EventManagerAdapter
import `in`.ac.bvmengineering.udaan2k19.DataClass.Event
import `in`.ac.bvmengineering.udaan2k19.R
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_event_details.*
import kotlinx.android.synthetic.main.fragment_event_details.view.*

class EventDetailsFragment : FragmentThatCanRespondToBack() {
    override fun onBackPressed(): Boolean {
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.event_frame, EventListFragment())?.commitNow()
        return true
    }

    lateinit var event: Event
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_event_details, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        event = arguments?.getSerializable("event") as Event
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.tag_line.text = event.tagLine
        view.event_name.text = event.name
        view.fees_text.text = "${event.fees}/${event.teamSize}"
        val round = when (event.name) {
            "Python Bootcamp", "Drone Workshop", "Wizards of IOT" -> false
            else -> true
        }
        val notes = event.notes
        val builder = StringBuilder()
        if (event.rounds?.size == 0) {
            if (notes != null && notes.isNotEmpty()) {
                view.round_text.text = "Notes: $notes"
            } else {
                view.intsructions.visibility = View.GONE
                view.round_text.visibility = View.GONE
            }
        } else if (!round) {
            view.round_text.text = event.rounds!![0]
            if (notes != null && notes.isNotEmpty()) {
                view.round_text.text = "${view.round_text.text} \n\n Notes: $notes"
            }
        } else {
            for (i in 0 until event.rounds!!.size) {
                builder.append("Round ").append(i + 1).append(":\n").append(event.rounds!![i]).append("\n\n")
            }
            view.round_text.text = builder.toString()
            if (notes != null && notes.isNotEmpty()) {
                view.round_text.text = "${view.round_text.text} \n\n Notes: $notes"
            }
        }


        contact_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        contact_list.adapter = EventManagerAdapter(event.managers!!)
    }
}