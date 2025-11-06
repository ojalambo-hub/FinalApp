package com.example.finalapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AssignmentListFragment extends Fragment {
    private static final String ARG_STATUS = "status";

    private MyViewModel viewModel;
    private RecyclerView recyclerView;
    private AssignmentAdapter adapter;
    private String status;

    public static AssignmentListFragment newInstance(String status) {
        AssignmentListFragment fragment = new AssignmentListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment_list, container, false);

        if (getArguments() != null) {
            status = getArguments().getString(ARG_STATUS);
        }

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new AssignmentAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.getAssignmentsByStatus(status).observe(getViewLifecycleOwner(), assignments -> {
            adapter.setAssignments(assignments);
        });

        return view;
    }
}