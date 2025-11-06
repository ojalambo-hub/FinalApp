package com.example.finalapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assignments")
public class Assignment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String subject;
    private String title;
    private String dueDate;
    private String status;
    private String notes;

    public Assignment(String subject, String title, String dueDate, String status, String notes) {
        this.subject = subject;
        this.title = title;
        this.dueDate = dueDate;
        this.status = status;
        this.notes = notes;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}