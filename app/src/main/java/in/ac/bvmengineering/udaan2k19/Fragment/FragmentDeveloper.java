package in.ac.bvmengineering.udaan2k19.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.preference.PowerPreference;

import java.util.ArrayList;
import java.util.Arrays;

import in.ac.bvmengineering.udaan2k19.Adapter.DeveloperAdapter;
import in.ac.bvmengineering.udaan2k19.DataClass.Developer;
import in.ac.bvmengineering.udaan2k19.R;

public class FragmentDeveloper extends Fragment {

    RecyclerView developerList;
    DeveloperAdapter developerAdapter;
    ArrayList<Developer> developers;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_developer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Developer[] developers1 = PowerPreference.getDefaultFile().getObject("developers", Developer[].class);
        developers = new ArrayList<>();
        developers.addAll(Arrays.asList(developers1));
        developerAdapter = new DeveloperAdapter(developers, getContext());
        developerList = view.findViewById(R.id.developer_list);
        developerList.setLayoutManager(new LinearLayoutManager(getContext()));
        developerList.setAdapter(developerAdapter);
    }
}
