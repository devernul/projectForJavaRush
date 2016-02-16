package edu.devernul.project.controller;

import edu.devernul.project.dao.*;
import edu.devernul.project.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import edu.devernul.project.model.Status;
import edu.devernul.project.model.Task;
import edu.devernul.project.validation.TaskValidator;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class TasksController {

    private static final Logger logger = LoggerFactory.getLogger(TasksController.class);
    public static final int TASK_ON_PAGE = 5;
    public static final int INDEX_FIRST_PAGE = 1;
    public static final String PDF_VIEW = "pdfView";
    public static final String TASKS = "tasks";
    public static final String REDIRECT_TASKS = "redirect:/tasks";
    public static final String SHOW = "show";

    @Autowired
    @Qualifier(value = "taskDao")
    private DaoTask taskDao;

    @Autowired
    @Qualifier(value = "userDao")
    private DaoUser userDao;

    @Autowired
    @Qualifier(value = "statusDao")
    private DaoStatus statusDao;

    @Autowired
    @Qualifier(value = "taskValidator")
    private TaskValidator taskValidator;

    @ModelAttribute("users")
    public List<User> getAllUsers() {
        return userDao.findAll();
    }


    @ModelAttribute("statuses")
    public List<Status> getAllStatus() {
        return statusDao.findAll();
    }

    @ModelAttribute("tasks")
    public List<Task> getAllTasks() {
        return taskDao.findPage(0, TASK_ON_PAGE);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/tasks")
    public String get(Model model) {

        if(statusDao.findAll().isEmpty()) {
            initialDump(statusDao, userDao, taskDao);
        }

        model.addAttribute("task", new Task());
        model.addAttribute("page_id", INDEX_FIRST_PAGE);
        model.addAttribute("countsTasks", taskDao.size());

        return TASKS;
    }



    @RequestMapping(method = RequestMethod.GET, value = "/tasks/filter/{statusId}/")
    public ModelAndView get(@PathVariable String statusId,Model model) {
        Status status = (Status) statusDao.getById(Integer.parseInt(statusId));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tasks",taskDao.findAllbyStatus(status));
        modelAndView.addObject("task", new Task());
        modelAndView.addObject("statusName",status.getName());
        modelAndView.setViewName(TASKS);

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/page_id/")
    public String redirToNullPage(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("page_id", INDEX_FIRST_PAGE);
        model.addAttribute("countsTasks", taskDao.size());

        return TASKS;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/tasks/page_id/{pageId}/")
    public ModelAndView get(@PathVariable Integer pageId,Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tasks",taskDao.findPage(TASK_ON_PAGE *pageId - TASK_ON_PAGE, TASK_ON_PAGE *pageId));
        modelAndView.addObject("task", new Task());
        modelAndView.addObject("countsTasks", taskDao.size());
        modelAndView.addObject("page_id", pageId);
        modelAndView.setViewName(TASKS);

        return modelAndView;
    }


    @RequestMapping(method = RequestMethod.POST)
    public String add( @ModelAttribute("task") @Valid Task  task, BindingResult result,Model model) {

        taskValidator.validate(task, result);

        if (result.hasErrors()){
            model.addAttribute("countsTasks", taskDao.size());
            model.addAttribute("page_id", INDEX_FIRST_PAGE);

            return TASKS;
        }else{
            if(task.getTaskId() != null){
                taskDao.update(task);
            }else{
                taskDao.create(task);
            }

            return REDIRECT_TASKS;
        }
    }


    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Status.class, "status", new PropertyEditorSupport() {

            public String getAsText() {
                Object value = getValue();
                if (value != null) {
                    Status status = (Status) value;
                    return status.getName();
                }

                return null;
            }

            public void setAsText(String text) {
                if (text instanceof String) {
                    Integer statusId = Integer.parseInt(text);
                    Status status = (Status) statusDao.getById(statusId);
                    setValue(status);
                }
            }
        });
        binder.registerCustomEditor(List.class, "users", new CustomCollectionEditor(List.class) {

            protected Object convertElement(Object element) {
                if (element != null) {
                    Integer userId = Integer.parseInt(element.toString());
                    User user = (User) userDao.getById(userId);
                    return user;

                }

                return null;
            }

        });


    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{action}/{id}")
    public String handleAction(@ModelAttribute("statuses")  List<Status>  statuses,@PathVariable Integer id, @PathVariable String action, Model model) {
        Task task = (Task) taskDao.getById(id);
        if (action.equalsIgnoreCase("edit")) {
            task.setUsers(new ArrayList<User>());
            model.addAttribute("task", task);
            model.addAttribute("statuses", statuses);
            model.addAttribute("countsTasks", taskDao.size());
            model.addAttribute("page_id", 1);

            return TASKS;
        } else if (action.equalsIgnoreCase("delete")) {
            taskDao.delete(task);
        }
        else if (action.equalsIgnoreCase("begin")) {
            taskDao.updateStatus(task, (Status) statusDao.getByName(EnumStatus.PROCESS.getTitle()));
        }
        else if (action.equalsIgnoreCase("complete")) {
            taskDao.updateStatus(task, (Status) statusDao.getByName(EnumStatus.COMPLETE.getTitle()));
        }
        else if (action.equalsIgnoreCase("show")) {
            task.setUsers(new ArrayList<User>());
            model.addAttribute("task", task);
            model.addAttribute("statuses", statusDao.findAll());
            return SHOW;
        }

        return REDIRECT_TASKS;
    }

    @RequestMapping(value = "/downloadPDF", method = RequestMethod.GET)
    public ModelAndView downloadPDF() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tasks",taskDao.findAll());
        modelAndView.setViewName(PDF_VIEW);
        return modelAndView;
    }


    private static void initialDump(final DaoStatus statusDao, final DaoUser userDao, final DaoTask taskDao) {


        Thread th2 = new Thread(
                    new Runnable() {
                        public void run() {

                                if (!Thread.interrupted())
                                {
                                    statusDao.initAction();

                                    if (userDao.findAll().isEmpty()) {
                                        userDao.initAction();
                                    }
                                    if (taskDao.size() <= 0) {
                                        for (EnumTask taskEl : EnumTask.values()) {
                                            Task task = new Task();
                                            task.setDate(new Date());
                                            task.setName(taskEl.getName());
                                            task.setDescription(taskEl.getDescription());
                                            task.setStatus(statusDao.getByName(EnumStatus.NEW.getTitle()));
                                            task.setUsers(userDao.findAll());
                                            taskDao.create(task);
                                        }
                                    }
                                }
                                else
                                    return;
                                try {
                                    Thread.currentThread().sleep(500);
                                } catch (InterruptedException e) {
                                    logger.warn(e.getMessage());
                                }

                        }
                    });
            th2.start();
            try {
                th2.join();
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                logger.warn(e.getMessage());
            }
    }

}
