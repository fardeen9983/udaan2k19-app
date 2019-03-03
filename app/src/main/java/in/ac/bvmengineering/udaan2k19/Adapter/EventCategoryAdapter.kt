package `in`.ac.bvmengineering.udaan2k19.Adapter

import `in`.ac.bvmengineering.udaan2k19.DataClass.EventCategory
import `in`.ac.bvmengineering.udaan2k19.Interface.OnCategoryItemClickListener
import `in`.ac.bvmengineering.udaan2k19.Misc.Constant
import `in`.ac.bvmengineering.udaan2k19.R
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.event_catagory_list_item_left.view.*
import kotlinx.android.synthetic.main.event_catagory_list_item_right.view.*

class EventCategoryAdapter(private val context: Context?, private val categories: ArrayList<EventCategory>, private val onCategoryItemClickListener: OnCategoryItemClickListener) : RecyclerView.Adapter<EventCategoryAdapter.EventCategoryViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = EventCategoryViewHolder(LayoutInflater.from(p0.context)
            .inflate(when (getItemViewType(p1)) {
                0 -> R.layout.event_catagory_list_item_left
                1 -> R.layout.event_catagory_list_item_right
                else -> R.layout.event_catagory_list_item_left
            }, p0, false))

    override fun getItemCount() = categories.size
    override fun onBindViewHolder(p0: EventCategoryViewHolder, p1: Int) {
        p0.bind(categories[p1], onCategoryItemClickListener)
    }

    override fun getItemViewType(position: Int) = position % 2
    inner class EventCategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        lateinit var categoryName: TextView
        lateinit var categoryIcon: ImageView

        init {
            when (view.id) {
                R.id.event_catagory_layout_left -> {
                    categoryIcon = view.event_catagory_image_left
                    categoryName = view.event_catagory_name_left
                }
                R.id.event_catagory_layout_right -> {
                    categoryIcon = view.event_catagory_image_right
                    categoryName = view.event_catagory_name_right
                }
            }
        }

        fun bind(category: EventCategory, onCategoryItemClickListener: OnCategoryItemClickListener) {
            categoryName.text = category.name
            Glide.with(context!!).load(category.image).into(categoryIcon).clearOnDetach()
            view.setOnClickListener {
                view.startAnimation(Constant.getAnimation(context))
                onCategoryItemClickListener.onCategoryItemClick(category)
            }
        }
    }
}