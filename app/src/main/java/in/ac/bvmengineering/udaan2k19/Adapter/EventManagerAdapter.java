package in.ac.bvmengineering.udaan2k19.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.ac.bvmengineering.udaan2k19.DataClass.Manager;
import in.ac.bvmengineering.udaan2k19.R;

public class EventManagerAdapter extends RecyclerView.Adapter<EventManagerAdapter.EventManagerViewHolder> {

    private ArrayList<Manager> managers;

    public EventManagerAdapter(ArrayList<Manager> managers) {
        this.managers = managers;
    }

    @NonNull
    @Override
    public EventManagerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new EventManagerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventManagerViewHolder eventManagerViewHolder, int i) {
        Manager manager = managers.get(i);
        eventManagerViewHolder.name.setText(manager.getName());
        eventManagerViewHolder.phone.setText(manager.getPhone());
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
    }
}
