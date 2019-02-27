package in.ac.bvmengineering.udaan2k19.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import in.ac.bvmengineering.udaan2k19.DataClass.EventCategory;
import in.ac.bvmengineering.udaan2k19.Interface.OnCategoryItemClickListener;
import in.ac.bvmengineering.udaan2k19.Misc.GlideApp;
import in.ac.bvmengineering.udaan2k19.R;

public class EventCategoryAdapter extends RecyclerView.Adapter<EventCategoryAdapter.EventCategoryViewHolder> {
    private Activity context;
    private ArrayList<EventCategory> eventCategories;
    private final OnCategoryItemClickListener listener;

    public EventCategoryAdapter(Activity context, ArrayList<EventCategory> eventCategories, OnCategoryItemClickListener listener) {
        this.context = context;
        this.eventCategories = eventCategories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EventCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        int layoutResource = 0;
        switch (getItemViewType(i)) {
            case 0:
                layoutResource = R.layout.event_catagory_list_item_left;
                break;
            case 1:
                layoutResource = R.layout.event_catagory_list_item_right;
                break;
        }
        View view = inflater.inflate(layoutResource, viewGroup, false);
        return (new EventCategoryViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull EventCategoryViewHolder eventCategoryViewHolder, int position) {
        eventCategoryViewHolder.bind(eventCategories.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return eventCategories.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    class EventCategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView eventCategoryIcon;
        TextView eventCategoryName;
        RelativeLayout relativeLayout;

        EventCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (itemView.getId()) {
                case R.id.event_catagory_layout_left:
                    eventCategoryIcon = itemView.findViewById(R.id.event_catagory_image_left);
                    eventCategoryName = itemView.findViewById(R.id.event_catagory_name_left);
                    relativeLayout = itemView.findViewById(R.id.event_catagory_layout_left);
                    break;
                case R.id.event_catagory_layout_right:
                    eventCategoryIcon = itemView.findViewById(R.id.event_catagory_image_right);
                    eventCategoryName = itemView.findViewById(R.id.event_catagory_name_right);
                    relativeLayout = itemView.findViewById(R.id.event_catagory_layout_right);
                    break;
            }
        }

        void bind(final EventCategory eventCategory, final OnCategoryItemClickListener listener) {
            eventCategoryName.setText(eventCategory.getName());
            GlideApp
                    .with(context)
                    .load(eventCategory.getImage())
                    .circleCrop()
                    .into(eventCategoryIcon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCategoryItemClick(eventCategory);
                }
            });
        }
    }
}


