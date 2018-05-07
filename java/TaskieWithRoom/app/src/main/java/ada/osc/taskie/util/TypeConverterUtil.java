package ada.osc.taskie.util;

import android.arch.persistence.room.TypeConverter;

import ada.osc.taskie.model.TaskPriority;

public class TypeConverterUtil {
    @TypeConverter
    public static TaskPriority fromString(String string) {
        return TaskPriority.valueOf(string);
    }

    @TypeConverter
    public static String fromTaskPriority(TaskPriority taskPriority) {
        return taskPriority.toString();
    }
}
