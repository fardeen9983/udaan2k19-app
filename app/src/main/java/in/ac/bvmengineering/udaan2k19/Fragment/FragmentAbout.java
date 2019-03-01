package in.ac.bvmengineering.udaan2k19.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

import in.ac.bvmengineering.udaan2k19.R;

public class FragmentAbout extends Fragment {

    Button insta, facebook, youtube, share, web;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        insta = view.findViewById(R.id.insta_profile);
        facebook = view.findViewById(R.id.facebook_profile);
        youtube = view.findViewById(R.id.youtube_profile);
        web = view.findViewById(R.id.website);
        share = view.findViewById(R.id.share);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(getString(R.string.facebook_page)));
                startActivity(i);
            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(getString(R.string.youtube_channel)));
                startActivity(i);
            }
        });
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(getString(R.string.insta_profile)));
                startActivity(i);
            }
        });
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(getString(R.string.website)));
                startActivity(i);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_SEND);
                intent2.setType("text/plain");
                intent2.putExtra(Intent.EXTRA_TEXT, Objects.requireNonNull(getContext()).getString(R.string.share_text));
                startActivity(Intent.createChooser(intent2, "Share via"));

            }
        });

    }

}
