package `in`.ac.bvmengineering.udaan2k19.Fragment

import `in`.ac.bvmengineering.udaan2k19.Adapter.EventListAdapter
import `in`.ac.bvmengineering.udaan2k19.Adapter.EventManagerAdapter
import `in`.ac.bvmengineering.udaan2k19.DataClass.Event
import `in`.ac.bvmengineering.udaan2k19.DataClass.EventCategory
import `in`.ac.bvmengineering.udaan2k19.Interface.OnEventClickListener
import `in`.ac.bvmengineering.udaan2k19.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_event_list.*
import kotlinx.android.synthetic.main.tool_bar.*

class EventListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val category = arguments?.getSerializable("category") as EventCategory
        val eventListAdapter = EventListAdapter(context, category.events, object : OnEventClickListener {
            override fun onEventClick(event: Event) {
                val fragment = EventDetailsFragment()
                val args = Bundle()
                args.putSerializable("event", event)
                fragment.arguments = args
                val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
                fragmentTransaction?.setCustomAnimations(R.anim.entry, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                fragmentTransaction?.add(R.id.event_frame, fragment, "a")
                fragmentTransaction?.commitNow()
            }

        })
        event_list.layoutManager = LinearLayoutManager(context)
        event_list.adapter = eventListAdapter

        tool_bar.setTitleTextAppearance(context, R.style.ToolbarText)
        tool_bar.title = category.name
        if (category.managers.size == 0) {
            event_head_list.visibility = View.GONE
            eventhead_text.visibility = View.GONE
        } else {
            event_head_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            event_head_list.adapter = EventManagerAdapter(category.managers)
        }

    }

}