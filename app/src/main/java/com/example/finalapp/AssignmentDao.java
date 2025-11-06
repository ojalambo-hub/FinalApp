package com.example.finalapp;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import java.util.List;

@Dao
public interface AssignmentDao {

    @Insert
    void insertAssignment(Assignment assignment);

    @Update
    void updateAssignment(Assignment assignment);

    @Delete
    void deleteAssignment(Assignment assignment);

    @Query("select * from assignments")
    LiveData<List<Assignment>> getAllAssignments();

    @Query("select * from assignments where status = :status")
    LiveData<List<Assignment>> getAssignmentsByStatus(String status);

    @Query("select count(*) from assignments")
    int getTotalAssignments();

    @Query("select count(*) from assignments where status = :status")
    int getAssignmentsCountByStatus(String status);
}