package in.ac.bvmengineering.udaan2k19.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.ac.bvmengineering.udaan2k19.DataClass.Event;
import in.ac.bvmengineering.udaan2k19.Interface.OnEventClickListener;
import in.ac.bvmengineering.udaan2k19.R;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventListViewHolder> {
    private ArrayList<Event> events;
    private OnEventClickListener onEventClickListener;

    public EventListAdapter(ArrayList<Event> events, OnEventClickListener onEventClickListener) {
        this.events = events;
        this.onEventClickListener = onEventClickListener;
    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new EventListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder eventListViewHolder, int i) {
        eventListViewHolder.bind(events.get(i), onEventClickListener);
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

        void bind(final Event event, final OnEventClickListener listener) {
            eventName.setText(event.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onEventClick(event);
                }
            });
        }
    }
}
