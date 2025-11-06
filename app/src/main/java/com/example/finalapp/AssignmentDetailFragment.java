package com.example.finalapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class AssignmentDetailFragment extends Fragment {
    private static final String ARG_ASSIGNMENT_ID = "assignment_id";

    private MyViewModel viewModel;
    private Assignment currentAssignment;

    private TextView subject, title, dueDate;
    private Spinner statusSpinner;
    private EditText notesEditText;
    private Button saveButton;

    public static AssignmentDetailFragment newInstance(int assignmentId) {
        AssignmentDetailFragment fragment = new AssignmentDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ASSIGNMENT_ID, assignmentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment_detail, container, false);
        initViews(view);
        setupViewModel();
        return view;
    }

    private void initViews(View view) {
        subject = view.findViewById(R.id.subject_text);
        title = view.findViewById(R.id.title_text);
        dueDate = view.findViewById(R.id.due_date_text);
        statusSpinner = view.findViewById(R.id.status_spinner);
        notesEditText = view.findViewById(R.id.notes_edit_text);
        saveButton = view.findViewById(R.id.save_button);

        // إعداد Spinner للحالات
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.assignment_statuses,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

        saveButton.setOnClickListener(v -> saveAssignment());
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        if (getArguments() != null) {
            int assignmentId = getArguments().getInt(ARG_ASSIGNMENT_ID);

            viewModel.getAllAssignments().observe(getViewLifecycleOwner(), assignments -> {
                for (Assignment assignment : assignments) {
                    if (assignment.getId() == assignmentId) {
                        currentAssignment = assignment;
                        populateData();
                        break;
                    }
                }
            });
        }
    }

    private void populateData() {
        if (currentAssignment != null) {
            subject.setText(currentAssignment.getSubject());
            title.setText(currentAssignment.getTitle());
            dueDate.setText(currentAssignment.getDueDate());
            notesEditText.setText(currentAssignment.getNotes());

            // تعيين الحالة في Spinner
            String status = currentAssignment.getStatus();
            switch (status) {
                case "Pending":
                    statusSpinner.setSelection(0);
                    break;
                case "In Progress":
                    statusSpinner.setSelection(1);
                    break;
                case "Submitted":
                    statusSpinner.setSelection(2);
                    break;
            }
        }
    }

    private void saveAssignment() {
        if (currentAssignment != null) {
            String selectedStatus = statusSpinner.getSelectedItem().toString();
            String notes = notesEditText.getText().toString();

            // تحويل النص العربي لإنجليزي
            String englishStatus = convertToEnglishStatus(selectedStatus);

            currentAssignment.setStatus(englishStatus);
            currentAssignment.setNotes(notes);

            viewModel.updateAssignment(currentAssignment);

            // العودة للشاشة السابقة
            requireActivity().onBackPressed();
        }
    }

    private String convertToEnglishStatus(String arabicStatus) {
        switch (arabicStatus) {
            case "معلق": return "Pending";
            case "قيد التنفيذ": return "In Progress";
            case "تم التسليم": return "Submitted";
            default: return "Pending";
        }
    }
}