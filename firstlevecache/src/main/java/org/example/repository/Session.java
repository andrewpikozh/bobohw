package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.EntityKey;
import org.example.exception.OrmException;
import org.example.utils.ReflectionUtils;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.example.utils.ReflectionUtils.checkId;

@RequiredArgsConstructor
public class Session implements EntityManager {

    private static final String SELECT_QUERY = "SELECT * FROM %s WHERE %s=?";
    private final DataSource dataSource;
    private final String tableName;
    private final String tableId;
    private Map<EntityKey, Object> firstLevelCache = new HashMap<>();

    public Session(DataSource dataSource, Class<?> type) {
        this.dataSource = dataSource;
        this.tableName = ReflectionUtils.getTableName(type);
        this.tableId = ReflectionUtils.getTableIdName(type);
    }

    @Override
    public <T> Optional<T> find(Class<T> clazz, Object primaryKey) {
        checkId(clazz, primaryKey);

        var entityKey = new EntityKey(clazz, primaryKey);
        if (firstLevelCache.containsKey(entityKey)) {
            return Optional.of((T) firstLevelCache.get(entityKey));
        } else {
            String statement = String.format(SELECT_QUERY, tableName, tableId);

            try (var connection = dataSource.getConnection()) {
                var preparedStatement = connection.prepareStatement(statement);
                preparedStatement.setObject(1, primaryKey);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    T entity = ReflectionUtils.mapToEntity(resultSet, clazz);
                    firstLevelCache.put(entityKey, entity);
                    return Optional.of(entity);
                }

                return Optional.empty();
            } catch (Exception e) {
                throw new OrmException(String.format("Exception while retrieving entity with id %s", primaryKey), e);
            }
        }
    }

    @Override
    public void close() throws Exception {
        firstLevelCache.clear();
    }
}
