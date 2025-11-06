package com.example.finalapp;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class MyRepository {

    private final AssignmentDao assignmentDao;

    public MyRepository(Application application) {
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        assignmentDao = db.assignmentDao();
    }

    public LiveData<List<Assignment>> getAllAssignments() {
        return assignmentDao.getAllAssignments();
    }

    public LiveData<List<Assignment>> getAssignmentsByStatus(String status) {
        return assignmentDao.getAssignmentsByStatus(status);
    }

    public void insertAssignment(Assignment assignment) {
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                assignmentDao.insertAssignment(assignment);
            }
        });
    }

    public void updateAssignment(Assignment assignment) {
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                assignmentDao.updateAssignment(assignment);
            }
        });
    }

    public void deleteAssignment(Assignment assignment) {
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                assignmentDao.deleteAssignment(assignment);
            }
        });
    }
}