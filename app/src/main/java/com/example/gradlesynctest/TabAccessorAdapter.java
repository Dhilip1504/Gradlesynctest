package com.example.gradlesynctest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAccessorAdapter extends FragmentPagerAdapter {

    public TabAccessorAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                ChatFragment chatFragment = new ChatFragment();
                return chatFragment;

            case 1:
                SchedulesFragment schedulesFragment = new SchedulesFragment();
                return schedulesFragment;

            case 2:
                CallsFragment callsFragment = new CallsFragment();
                return callsFragment;

            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){

            case 0:
                return "Chats";

            case 1:
                return "Schedules";

            case 2:
                return "Calls";

            default:
                return null;

        }

    }
}
