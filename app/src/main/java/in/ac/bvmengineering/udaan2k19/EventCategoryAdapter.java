package in.ac.bvmengineering.udaan2k19;

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

public class EventCategoryAdapter extends RecyclerView.Adapter<EventCategoryAdapter.EventCatagoryViewHolder> {
    private Activity context;
    private ArrayList<EventCategory> eventCatagories;

    public EventCategoryAdapter(Activity context, ArrayList<EventCategory> eventCatagories) {
        this.context = context;
        this.eventCatagories = eventCatagories;
    }

    @NonNull
    @Override
    public EventCatagoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        int layuotResource = 0;
        switch (getItemViewType(i)) {
            case 0:
                layuotResource = R.layout.event_catagory_list_item_left;
                break;
            case 1:
                layuotResource = R.layout.event_catagory_list_item_right;
                break;
        }
        View view = inflater.inflate(layuotResource, viewGroup, false);
        return (new EventCatagoryViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull EventCatagoryViewHolder eventCatagoryViewHolder, int position) {
        EventCategory eventCategory = eventCatagories.get(position);
        eventCatagoryViewHolder.eventCatagoryName.setText(eventCategory.getName());
        GlideApp
                .with(context)
                .load(eventCategory.getImage())
                .circleCrop()
                .into(eventCatagoryViewHolder.eventCatagoryIcon);

    }

    @Override
    public int getItemCount() {
        return eventCatagories.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    public class EventCatagoryViewHolder extends RecyclerView.ViewHolder {
        ImageView eventCatagoryIcon;
        TextView eventCatagoryName;
        RelativeLayout relativeLayout;

        public EventCatagoryViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (itemView.getId()) {
                case R.id.event_catagory_layout_left:
                    eventCatagoryIcon = itemView.findViewById(R.id.event_catagory_image_left);
                    eventCatagoryName = itemView.findViewById(R.id.event_catagory_name_left);
                    relativeLayout = itemView.findViewById(R.id.event_catagory_layout_left);
                    break;
                case R.id.event_catagory_layout_right:
                    eventCatagoryIcon = itemView.findViewById(R.id.event_catagory_image_right);
                    eventCatagoryName = itemView.findViewById(R.id.event_catagory_name_right);
                    relativeLayout = itemView.findViewById(R.id.event_catagory_layout_right);
                    break;
            }
        }
    }

}
