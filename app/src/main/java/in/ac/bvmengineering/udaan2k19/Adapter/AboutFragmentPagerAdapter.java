package in.ac.bvmengineering.udaan2k19.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.ac.bvmengineering.udaan2k19.Fragment.FragmentAbout;
import in.ac.bvmengineering.udaan2k19.Fragment.FragmentDeveloper;

public class AboutFragmentPagerAdapter extends FragmentPagerAdapter {

    private String tabs[] = {"Developer,About Us "};

    public AboutFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new FragmentDeveloper();
            case 1:
                return new FragmentAbout();
            default:
                return new FragmentDeveloper();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0)
            return "Developer";
        else return "About Us";

    }
}
