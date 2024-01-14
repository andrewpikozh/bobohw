package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.exception.OrmException;
import org.example.utils.ReflectionUtils;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.Optional;

import static org.example.utils.ReflectionUtils.checkId;

@RequiredArgsConstructor
public class JDBCRepository<T, U> implements Repository<T, U> {

    private static final String SELECT_QUERY = "SELECT * FROM %s WHERE %s=?";
    private final DataSource dataSource;
    private final Class<T> type;
    private final String tableName;
    private final String tableId;

    public JDBCRepository(DataSource dataSource, Class<T> type) {
        this.dataSource = dataSource;
        this.type = type;
        this.tableName = ReflectionUtils.getTableName(type);
        this.tableId = ReflectionUtils.getTableIdName(type);
    }

    @Override
    public Optional<T> findById(U id) {
        checkId(type, id);
        String statement = String.format(SELECT_QUERY, tableName, tableId);

        try (var connection = dataSource.getConnection()) {
            var preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next() ? mapToEntity(resultSet, type) : Optional.empty();
        } catch (Exception e) {
            throw new OrmException(String.format("Exception while retrieving entity with id %s", id), e);
        }
    }

    private Optional<T> mapToEntity(ResultSet resultSet, Class<T> type) throws Exception {
        T entity = type.getDeclaredConstructor().newInstance();

        for (var field : type.getDeclaredFields()) {
            field.setAccessible(true);
            field.set(entity, resultSet.getObject(ReflectionUtils.getColumnName(field)));
        }
        return Optional.of(entity);
    }
}
