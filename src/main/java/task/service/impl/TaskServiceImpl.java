package task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.domain.DataTableRequest;
import task.domain.DataTableResult;
import task.domain.Task;
import task.repository.TaskRepository;
import task.repository.impl.SqlQuery;
import task.service.TaskService;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public DataTableResult getPersonDataTable(DataTableRequest request) {

        Map<Integer, String> columnMapping = new HashMap<>();

        columnMapping.put(0, "id");
        columnMapping.put(1, "task_name");
        columnMapping.put(2, "description");
        columnMapping.put(3, "start_date");
        columnMapping.put(4, "end_date");
        columnMapping.put(5, "user_name");

        String sql = SqlQuery.GET_PERSON_LIST_WITH_PAGING.replace("{SORT_COLUMN}", columnMapping.get(request.getSortColumn()))
                .replace("{SORT_DIRECTION}", request.getSortDirection());

        DataTableResult dataTableResult = new DataTableResult();

        dataTableResult.setDraw(request.getDraw());
        dataTableResult.setRecordsTotal(taskRepository.getPersonCount());
        dataTableResult.setRecordsFiltered(taskRepository.getFilteredPersonCount(request.getFilter()));

        List<Task> taskList = taskRepository.getListTaskWithPaging(sql, request.getStart(), request.getLength(), request.getFilter());

        dataTableResult.setData(new Object[taskList.size()][9]);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        for (int i = 0; i < taskList.size(); i++) {
            dataTableResult.getData()[i][0] = taskList.get(i).getId();
            dataTableResult.getData()[i][1] = taskList.get(i).getTaskName();
            dataTableResult.getData()[i][2] = taskList.get(i).getDescription();
            if (taskList.get(i).getStartDate() != null){
                dataTableResult.getData()[i][3] = dateTimeFormatter.format(taskList.get(i).getStartDate());
            }

            if (taskList.get(i).getEndDate() != null){
                dataTableResult.getData()[i][4] = dateTimeFormatter.format(taskList.get(i).getEndDate());
            }

            dataTableResult.getData()[i][5] = taskList.get(i).getUser_name();
            dataTableResult.getData()[i][6] = "<a href=\"delete?id=" + taskList.get(i).getId() + "\">Delete</a>&nbsp"
            + "<a href=\"view?id=" + taskList.get(i).getId() + "\">View</a>&nbsp" +
               "<a href=\"update?id=" + taskList.get(i).getId() + "\">Update</a>&nbsp;";

        }
        return dataTableResult;
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.addTask(task);
    }

    @Override
    public int deleteTask(int id) {
        return taskRepository.deleteTask(id);
    }

    @Override
    public Optional<Task> getTaskById(int id) {
        return taskRepository.getTaskById(id);
    }

    @Override
    public long updateTask(Task task) {
        return taskRepository.updateTask(task);
    }

    @Override
    public List<Task> viewTaskById(int id) {
        return taskRepository.viewTaskById(id);
    }
}