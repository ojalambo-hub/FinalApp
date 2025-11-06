package com.example.finalapp;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Assignment.class}, version = 1, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {

    public abstract AssignmentDao assignmentDao();

    private static volatile MyRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MyRoomDatabase.class, "database")
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    AssignmentDao dao = INSTANCE.assignmentDao();

                    dao.insertAssignment(new Assignment("الرياضيات", "تمارين ", "2024-01-15", "Pending", "ملاحظة مهمة"));
                    dao.insertAssignment(new Assignment("الفيزياء", "تمارين ", "2024-01-15", "In Progress", "ملاحظة مهمة"));
                    dao.insertAssignment(new Assignment("اللغة العربية", "تمارين تمارين", "2024-01-15", "Submitted", "ملاحظة مهمة"));
                    dao.insertAssignment(new Assignment("التاريخ", "تمارين عن تمارين", "2024-01-20", "Pending", "5ملاحظة مهمة"));
                    dao.insertAssignment(new Assignment("علوم", "مشرو ", "2024-01-15", "In Progress", "يي ملاحظة مهمة"));
                }
            });
        }
    };
}