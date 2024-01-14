package org.example.utils;

import lombok.experimental.UtilityClass;
import org.example.annotation.Column;
import org.example.annotation.Id;
import org.example.annotation.Table;
import org.example.exception.NoIdException;
import org.example.exception.OrmException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

@UtilityClass
public class ReflectionUtils {
    private static final String SNAKE_REGEX = "([a-z])([A-Z]+)";
    private static final String REPLACEMENT = "$1_$2";

    public static String getTableName(Class<?> clazz) {
        return Optional.ofNullable(clazz.getAnnotation(Table.class))
                .map(Table::name)
                .orElse(clazz.getSimpleName());
    }

    public static String getColumnName(Field field) {
        return Optional.ofNullable(field.getAnnotation(Column.class))
                .map(Column::name)
                .orElse(field.getName().replaceAll(SNAKE_REGEX, REPLACEMENT).toLowerCase());
    }

    public static <T> String getTableIdName(Class<T> clazz) {
        return getColumnName(getTableId(clazz));
    }

    private static <T> Field getTableId(Class<T> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new NoIdException(String.format("Specify id for Entity %s", clazz.getSimpleName())));
    }

    public static <T> void checkId(Class<T> clazz, Object id) {
        Field tableId = getTableId(clazz);
        Class<?> type = tableId.getType();

        if (!type.isInstance(id)) {
            throw new OrmException(
                    String.format("Specified id does not match the Entity Id type. Entity Id: %s, Specified Id: %s",
                            type, id.getClass()));
        }
    }
}
