package task.repository.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import task.domain.Task;
import task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private TaskRowMapper taskRowMapper;


    @Override
    public List<Task> getListTaskWithPaging(String sql, int start, int length, String filter) {

        Object[] args = {"%" + filter + "%", start, length};

       List<Task> taskList = jdbcTemplate.query(sql, args, taskRowMapper);

        return taskList;
    }

    @Override
    public long getPersonCount() {
        long count = 0;
        count =  jdbcTemplate.queryForObject(SqlQuery.GET_TASK_COUNT, Long.class);

        return count;
    }

    @Override
    public long getFilteredPersonCount(String filter) {

        long count = 0;
        Object[] args = {"%" + filter + "%"};
        count = jdbcTemplate.queryForObject(SqlQuery.GET_FILTERED_PERSON_COUNT, args, Long.class);


        return count;
    }

    public Task addTask(Task task){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("task_name", task.getTaskName());
        params.addValue("description", task.getDescription());
        params.addValue("start_date", task.getStartDate());
        params.addValue("end_date", task.getEndDate());
        params.addValue("user_name", task.getUser_name());

       namedParameterJdbcTemplate.update(SqlQuery.INSERT_ADD_TASK, params);
        return task;
    }

    @Override
    public int deleteTask(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        int count = 0;
        count = namedParameterJdbcTemplate.update(SqlQuery.DELETE_TASK, params);

        return count;
    }

    @Override
    public Optional<Task> getTaskById(int id) {

       Optional<Task> optionalTask =  Optional.empty();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
       List<Task> taskList =  namedParameterJdbcTemplate.query(SqlQuery.SELECT_TASK, params, taskRowMapper);
       if(!taskList.isEmpty()) {

           optionalTask = Optional.of(taskList.get(0));
       }

        return optionalTask;
    }

    @Override
    public long updateTask(Task task) {
        System.out.println("task "+ task);

      MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", task.getId());
        params.addValue("task_name", task.getTaskName());
        params.addValue("description", task.getDescription());
        params.addValue("start_date", task.getStartDate());
        params.addValue("end_date", task.getEndDate());
        params.addValue("user_name", task.getUser_name());

     long count = namedParameterJdbcTemplate.update(SqlQuery.UPDATE_TASK, params);

        return count;
    }


    @Override
    public List<Task> viewTaskById(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        List<Task> taskList =  namedParameterJdbcTemplate.query(SqlQuery.SELECT_TASK, params, taskRowMapper);

        return taskList;
    }

}
