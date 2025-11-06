package com.example.finalapp;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private MyRepository repository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new MyRepository(application);
    }

    public LiveData<List<Assignment>> getAllAssignments() {
        return repository.getAllAssignments();
    }

    public LiveData<List<Assignment>> getAssignmentsByStatus(String status) {
        return repository.getAssignmentsByStatus(status);
    }

    public void insertAssignment(Assignment assignment) {
        repository.insertAssignment(assignment);
    }

    public void updateAssignment(Assignment assignment) {
        repository.updateAssignment(assignment);
    }

    public void deleteAssignment(Assignment assignment) {
        repository.deleteAssignment(assignment);
    }
}