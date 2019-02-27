package in.ac.bvmengineering.udaan2k19.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.ac.bvmengineering.udaan2k19.DataClass.Event;
import in.ac.bvmengineering.udaan2k19.R;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventListViewHolder> {
    private ArrayList<Event> events;


    public EventListAdapter(ArrayList<Event> events) {
        this.events = events;

    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new EventListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_event_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder eventListViewHolder, int i) {
        Event event = events.get(i);
        eventListViewHolder.eventName.setText(event.getName());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventListViewHolder extends RecyclerView.ViewHolder {
        TextView eventName;

        EventListViewHolder(@NonNull View itemView) {
            super(itemView);
            eventName = itemView.findViewById(R.id.event_name);
        }
    }
}
