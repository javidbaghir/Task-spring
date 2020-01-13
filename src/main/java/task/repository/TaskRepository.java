package task.repository;


import task.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<Task> getListTaskWithPaging(String sql, int start, int length, String filter);

    long getPersonCount();
    long getFilteredPersonCount(String filter);
    Task addTask(Task task);
    int deleteTask(int id);
    Optional<Task> getTaskById(int id);
    long updateTask(Task task);
    List<Task> viewTaskById(int id);


}
