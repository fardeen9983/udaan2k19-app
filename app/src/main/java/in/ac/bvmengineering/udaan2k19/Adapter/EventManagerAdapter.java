package in.ac.bvmengineering.udaan2k19.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.ac.bvmengineering.udaan2k19.DataClass.Manager;
import in.ac.bvmengineering.udaan2k19.Interface.OnContactCardClickListener;
import in.ac.bvmengineering.udaan2k19.R;

public class EventManagerAdapter extends RecyclerView.Adapter<EventManagerAdapter.EventManagerViewHolder> {

    private ArrayList<Manager> managers;
    private OnContactCardClickListener listener;

    public EventManagerAdapter(ArrayList<Manager> managers, OnContactCardClickListener listener) {
        this.managers = managers;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EventManagerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new EventManagerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventManagerViewHolder eventManagerViewHolder, int i) {
        eventManagerViewHolder.bind(managers.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return managers.size();
    }

    class EventManagerViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone;

        EventManagerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.contact_name);
            phone = itemView.findViewById(R.id.contact_number);
        }

        void bind(final Manager manager, final OnContactCardClickListener listener) {
            name.setText(manager.getName());
            phone.setText(manager.getPhone());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onContactCardClicked(manager);
                }
            });
        }
    }
}
