package task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import task.domain.DataTableRequest;
import task.domain.DataTableResult;
import task.domain.Task;
import task.repository.TaskRepository;
import task.service.TaskService;

import java.util.List;
import java.util.Optional;


@RequestMapping("/")
@Controller
public class WebController {


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @GetMapping("/getPersonListAjax")
    @ResponseBody
    public DataTableResult getPersonListAjax(
            @RequestParam(name = "draw") int draw,
            @RequestParam(name = "start") int start,
            @RequestParam(name = "length") int length,
            @RequestParam(name = "order[0][column]") int sortColumn,
            @RequestParam(name = "order[0][dir]") String sortDirection,
            @RequestParam(name = "search[value]") String filter) {

        DataTableRequest dataTableRequest = new DataTableRequest();

        dataTableRequest.setDraw(draw);
        dataTableRequest.setStart(start);
        dataTableRequest.setLength(length);
        dataTableRequest.setSortColumn(sortColumn);
        dataTableRequest.setSortDirection(sortDirection);
        dataTableRequest.setFilter(filter);

        DataTableResult dataTableResult = taskService.getPersonDataTable(dataTableRequest);

        return dataTableResult;
    }

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/task_form")
    public ModelAndView form() {
        ModelAndView modelAndView = new ModelAndView("form");
        Task task = new Task();
        modelAndView.addObject("task", task);

        return modelAndView;
    }

    @PostMapping("/add_task")
    public ModelAndView formPost(@ModelAttribute("task") Task task) {

        ModelAndView modelAndView = new ModelAndView("index");

        taskService.addTask(task);
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam(name = "id") int id) {


        ModelAndView modelAndView = new ModelAndView("index");


        taskService.deleteTask(id);

        return modelAndView;
    }

    @GetMapping("/update")
    public ModelAndView update(@RequestParam(name = "id") int id) {

        ModelAndView modelAndView = new ModelAndView("update");

        Optional<Task> optionalTask = taskService.getTaskById(id);

        Task task = optionalTask.get();

        modelAndView.addObject("task", task);

        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateTask(@ModelAttribute("task") Task task) {

        ModelAndView modelAndView = new ModelAndView("index");

        taskService.updateTask(task);


        return modelAndView;
    }

    @GetMapping("/view")
    public ModelAndView viewTask(@RequestParam(name = "id") int id) {

        ModelAndView modelAndView = new ModelAndView();

        List<Task> taskList = taskService.viewTaskById(id);
        if (!taskList.isEmpty()) {
            modelAndView.addObject("view", taskList.get(0));
            modelAndView.setViewName("view");
        } else {
            modelAndView.setViewName("index");
        }

        return modelAndView;
    }
}