package `in`.ac.bvmengineering.udaan2k19.Fragment

import `in`.ac.bvmengineering.udaan2k19.Adapter.EventCategoryAdapter
import `in`.ac.bvmengineering.udaan2k19.DataClass.EventCategory
import `in`.ac.bvmengineering.udaan2k19.Interface.OnCategoryItemClickListener
import `in`.ac.bvmengineering.udaan2k19.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.fragment_event_category.*
import kotlinx.android.synthetic.main.tool_bar.*

class EventCategoryFragment : Fragment() {
    private var eventCategoryArrayList = ArrayList<EventCategory>()
    private lateinit var eventCategoryAdapter: EventCategoryAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_event_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        event_catagory_list.layoutManager = LinearLayoutManager(context)
        tool_bar.setTitleTextAppearance(context, R.style.ToolbarText)
        tool_bar.title = "Event Categories"
        val temp: Array<EventCategory> = PowerPreference.getDefaultFile().getObject("eventCategories", Array<EventCategory>::class.java)
        eventCategoryArrayList = temp.toCollection(ArrayList())
        eventCategoryAdapter = EventCategoryAdapter(context, eventCategoryArrayList, object : OnCategoryItemClickListener {
            override fun onCategoryItemClick(eventCategory: EventCategory) {
                val eventListFragment = EventListFragment()
                val args = Bundle()
                args.putSerializable("category", eventCategory)
                eventListFragment.arguments = args
                val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
                fragmentTransaction?.setCustomAnimations(R.anim.entry, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                fragmentTransaction?.add(R.id.event_frame, eventListFragment, "a")
                fragmentTransaction?.commitNow()
            }

        })
        event_catagory_list.adapter = eventCategoryAdapter
    }
}