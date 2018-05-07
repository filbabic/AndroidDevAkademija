package ada.osc.taskie.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import ada.osc.taskie.model.Task;
import ada.osc.taskie.util.TypeConverterUtil;

@Database(entities = {Task.class}, version = 1)
@TypeConverters({TypeConverterUtil.class})
public abstract class TaskRoomDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

    private static TaskRoomDatabase INSTANCE;

    public static TaskRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskRoomDatabase.class, "task_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
