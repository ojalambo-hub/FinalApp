package com.example.finalapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {

    private List<Assignment> assignments = new ArrayList<>();

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_assignment, parent, false);
        return new AssignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {
        Assignment assignment = assignments.get(position);
        holder.subject.setText(assignment.getSubject());
        holder.title.setText(assignment.getTitle());
        holder.dueDate.setText(assignment.getDueDate());

        String status = assignment.getStatus();
        if (status.equals("Pending")) {
            holder.status.setText("معلق");
        } else if (status.equals("In Progress")) {
            holder.status.setText("قيد التنفيذ");
        } else {
            holder.status.setText("تم التسليم");
        }
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
        notifyDataSetChanged();
    }

    static class AssignmentViewHolder extends RecyclerView.ViewHolder {
        TextView subject, title, dueDate, status;

        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.assignment_subject);
            title = itemView.findViewById(R.id.assignment_title);
            dueDate = itemView.findViewById(R.id.assignment_due_date);
            status = itemView.findViewById(R.id.assignment_status);
        }
    }
}