package com.example.finalapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return AssignmentListFragment.newInstance("Pending");
            case 1: return AssignmentListFragment.newInstance("In Progress");
            case 2: return AssignmentListFragment.newInstance("Submitted");
            default: return AssignmentListFragment.newInstance("Pending");
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}