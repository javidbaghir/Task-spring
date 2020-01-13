package task.service;

import task.domain.DataTableRequest;
import task.domain.DataTableResult;
import task.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    DataTableResult getPersonDataTable(DataTableRequest request);
    Task addTask(Task task);
    int deleteTask(int id);
    Optional<Task> getTaskById(int id);
    long updateTask(Task task);
    List<Task> viewTaskById(int id);


}
