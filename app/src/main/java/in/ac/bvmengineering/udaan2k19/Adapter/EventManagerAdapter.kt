package `in`.ac.bvmengineering.udaan2k19.Adapter

import `in`.ac.bvmengineering.udaan2k19.DataClass.Manager
import `in`.ac.bvmengineering.udaan2k19.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.contact_list_item.view.*

class EventManagerAdapter(private val managers: ArrayList<Manager>) : RecyclerView.Adapter<EventManagerAdapter.EventManagerViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): EventManagerViewHolder {
        return EventManagerViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.contact_list_item, p0, false))
    }

    override fun getItemCount(): Int {
        return managers.size
    }

    override fun onBindViewHolder(p0: EventManagerViewHolder, p1: Int) {
        val manger = managers[p1]
        p0.name.text = manger.name
        p0.phone.text = manger.phone
    }

    class EventManagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.contact_name!!
        val phone = view.contact_number!!
    }
}