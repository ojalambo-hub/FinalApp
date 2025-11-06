package com.example.finalapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class DashboardFragment extends Fragment {

    private TextView totalAssignments, pendingAssignments, inProgressAssignments, submittedAssignments;
    private MyViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        totalAssignments = view.findViewById(R.id.total_assignments);
        pendingAssignments = view.findViewById(R.id.pending_assignments);
        inProgressAssignments = view.findViewById(R.id.in_progress_assignments);
        submittedAssignments = view.findViewById(R.id.submitted_assignments);

        setupViewModel();
        return view;
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        viewModel.getAllAssignments().observe(getViewLifecycleOwner(), assignments -> {

                int total = assignments.size();
                int pending = 0;
                int inProgress = 0;
                int submitted = 0;

                for (Assignment assignment : assignments) {
                    switch (assignment.getStatus()) {case "Pending":
                            pending++;break; case "In Progress": inProgress++;
                            break; case "Submitted": submitted++;break;
                    }
                }
                totalAssignments.setText(String.valueOf(total)+" عدد كل الواجبات ");
                pendingAssignments.setText(String.valueOf(pending)+" المعلقة ");
                inProgressAssignments.setText(String.valueOf(inProgress)+" جارية");
                submittedAssignments.setText(String.valueOf(submitted)+" تمت");

        });
    }
}