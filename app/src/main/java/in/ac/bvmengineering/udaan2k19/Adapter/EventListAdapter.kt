package `in`.ac.bvmengineering.udaan2k19.Adapter

import `in`.ac.bvmengineering.udaan2k19.DataClass.Event
import `in`.ac.bvmengineering.udaan2k19.Interface.OnEventClickListener
import `in`.ac.bvmengineering.udaan2k19.Misc.Constant
import `in`.ac.bvmengineering.udaan2k19.R
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*

class EventListAdapter(private val context: Context?, private val events: ArrayList<Event>, private val onEventClickListener: OnEventClickListener) : RecyclerView.Adapter<EventListAdapter.EventListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): EventListViewHolder {
        return EventListViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.event_list_item, viewGroup, false))
    }

    override fun onBindViewHolder(eventListViewHolder: EventListViewHolder, i: Int) {
        eventListViewHolder.bind(events[i], onEventClickListener)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    inner class EventListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var eventName: TextView = itemView.findViewById(R.id.event_name)

        fun bind(event: Event, listener: OnEventClickListener) {
            eventName.text = event.name
            itemView.setOnClickListener { v ->
                v.startAnimation(Constant.getAnimation(context))
                listener.onEventClick(event)
            }
        }
    }
}