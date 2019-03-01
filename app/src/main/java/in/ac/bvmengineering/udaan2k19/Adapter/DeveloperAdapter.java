package in.ac.bvmengineering.udaan2k19.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.ac.bvmengineering.udaan2k19.DataClass.Developer;
import in.ac.bvmengineering.udaan2k19.R;

public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.DeveloperViewHolder> {
    private ArrayList<Developer> developers;
    private Context context;

    public DeveloperAdapter(ArrayList<Developer> developers, Context context) {
        this.developers = developers;
        this.context = context;
    }

    @NonNull
    @Override
    public DeveloperViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DeveloperViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.developer_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DeveloperViewHolder developerViewHolder, int i) {
        final Developer developer = developers.get(i);
        developerViewHolder.type.setText(developer.getType());
        developerViewHolder.name.setText(developer.getName());
        developerViewHolder.imageView.setImageDrawable(context.getDrawable(developer.getImageID()));
        developerViewHolder.mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{developer.getMail()});
                email.setType("message/rfc822");
                context.startActivity(Intent.createChooser(email, "Choose an Email client :"));

            }
        });
        developerViewHolder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + developer.getPhone()));
                context.startActivity(intent);
            }
        });
        developerViewHolder.git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(context.getString(R.string.gith_link) + developer.getGithub()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return developers.size();
    }

    class DeveloperViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, type;
        Button git, mail, phone;

        DeveloperViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.developer_icon);
            name = itemView.findViewById(R.id.developer_name);
            type = itemView.findViewById(R.id.developer_type);
            git = itemView.findViewById(R.id.github);
            mail = itemView.findViewById(R.id.mail);
            phone = itemView.findViewById(R.id.dialer);
        }
    }
}
