package taskmanager;

import domain.Epic;
import domain.SubTask;
import domain.Task;
import historymanager.HistoryManager;

import java.util.ArrayList;

public interface TaskManager {


    void add(Task task);

    void add(Epic epic);

    void add(SubTask subTask);

    void update(Task task);

    void update(Epic epic);

    void update(SubTask subTask);

    Epic getEpic(int id);

    SubTask getSubTask(int id);

    Task getTask(int id);

    ArrayList<Epic> getAllEpics();

    ArrayList<SubTask> getAllSubTaskFromEpic(int id);

    ArrayList<SubTask> getAllSubTasks();

    ArrayList<Task> getAllTasks();

    void deleteTask(int id);

    void deleteEpic(int id);

    void deleteSubTask(int id);

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubTasks();


    /*HistoryManager getHistoryManager();*/ //для тестирвоания истории
}
