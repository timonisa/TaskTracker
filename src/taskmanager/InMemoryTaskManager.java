package taskmanager;

import domain.Epic;
import domain.Status;
import domain.SubTask;
import domain.Task;
import historymanager.HistoryManager;


import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {

    private HistoryManager historyManager = Managers.getDefaultHistory();
    private int nextId = 1;
    private HashMap<Integer, Task> taskMap = new HashMap<>();
    private HashMap<Integer, Epic> epicMap = new HashMap<>();
    private HashMap<Integer, SubTask> subTaskMap = new HashMap<>();


    /*public HistoryManager getHistoryManager() { //для тестирования, чтоб вывести нужный экземпляр истории
        return historyManager;
    }*/


    @Override
    public void add(Task task) {
        task.setId(nextId);
        nextId++;
        taskMap.put(task.getId(), task);
    }

    @Override
    public void add(Epic epic) {
        if (!epic.getStatus().equals(Status.NEW)) return;
        epic.setId(nextId);
        nextId++;
        epicMap.put(epic.getId(), epic);
    }

    @Override
    public void add(SubTask subTask) {
        subTask.setSubTaskId(nextId);
        nextId++;
        subTaskMap.put(subTask.getSubTaskId(), subTask); //добавили сабтаску
        epicMap.get(subTask.getId()).getTaskIds().add(subTask.getSubTaskId()); //  и привязали айдишник эпика к сабтаске
        calculateEpicStatus(epicMap.get(subTask.getId())); //пересчитали статус
    }

    @Override
    public void update(Task task) {
        taskMap.put(task.getId(), task);
    }

    @Override
    public void update(Epic epic) {
        calculateEpicStatus(epic); // передаем эпик в метод и после расчета статуса обновляем эпик в мапе.
    }

    @Override
    public void update(SubTask subTask) {
        subTaskMap.put(subTask.getSubTaskId(), subTask);
        calculateEpicStatus(epicMap.get(subTask.getId()));
    }

    @Override
    public ArrayList<Epic> getAllEpics() {
        if (epicMap.size() == 0) {
            System.out.println("Нет эпиков");
            return null;
        }
        return new ArrayList<>(epicMap.values());
    }

    @Override
    public Epic getEpic(int id) {
        if (epicMap.containsKey(id)) {
            historyManager.add(epicMap.get(id));
            return epicMap.get(id);
        } else {
            System.out.println("Нет эпика");
            return null;
        }
    }

    @Override
    public ArrayList<SubTask> getAllSubTaskFromEpic(int id) {
        if (epicMap.get(id).getTaskIds().size() == 0) {
            System.out.println("Нет подзадач");
            return null;
        }
        ArrayList<SubTask> subTasks = new ArrayList<>();
        for (Integer taskId : epicMap.get(id).getTaskIds()) { // перебираем сабтаски
            subTasks.add(getSubTask(taskId));
        }
        return subTasks;
    }

    @Override
    public SubTask getSubTask(int id) {
        if (subTaskMap.containsKey(id)) {
            historyManager.add(subTaskMap.get(id));
            return subTaskMap.get(id);
        } else {
            System.out.println("Нет подзадачи");
            return null;
        }
    }

    @Override
    public Task getTask(int id) {
        if (taskMap.containsKey(id)) {
            historyManager.add(taskMap.get(id));
            return taskMap.get(id);
        } else {
            System.out.println("Нет задачи");
            return null;
        }
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        if (taskMap.isEmpty()) {
            System.out.println("Нет задач");
            return null;
        } else {
            return new ArrayList<>(taskMap.values());
        }
    }

    @Override
    public ArrayList<SubTask> getAllSubTasks() {
        if (subTaskMap.isEmpty()) {
            System.out.println("Нет подзадач");
            return null;
        } else {
            return new ArrayList<>(subTaskMap.values());
        }
    }

    @Override
    public void deleteAllTasks() {
        if (taskMap.isEmpty()) {
            System.out.println("Нет задач");
            return;
        }
        ArrayList<Integer> keySet = new ArrayList<>(taskMap.keySet());
        for (int i = 0; i <= taskMap.size(); i++) {
            deleteTask(keySet.get(i));
        }
    }

    @Override
    public void deleteTask(int id) {
        if (!taskMap.containsKey(id)) System.out.println("Нет задачи");
        if (taskMap.get(id) != null) taskMap.remove(id);
    }

    @Override
    public void deleteAllSubTasks() {
        if (subTaskMap.isEmpty()) {
            System.out.println("Нет подзадач");
            return;
        }
        ArrayList<Integer> keySet = new ArrayList<>(epicMap.keySet());
        for (int i = 0; i < epicMap.size(); i++) {
            ArrayList<Integer> taskIds = epicMap.get(keySet.get(i)).getTaskIds();
            for (int j = (taskIds.size()) - 1; j >= 0; j--) {
                // перебираем сабтаски
                deleteSubTask(taskIds.get(j));
            }
        }
    }

    @Override
    public void deleteSubTask(int id) {
        int epicId = subTaskMap.get(id).getId();
        if (!subTaskMap.containsKey(id)) System.out.println("Нет подзадачи");
        if (subTaskMap.get(id) != null) {
            epicMap.get(epicId).getTaskIds().remove((Integer) id);
            subTaskMap.remove(id);
            //epicMap.get(epicId).getTaskIds().addAll(new ArrayList<>(subTaskMap.keySet()));
        }
        calculateEpicStatus(epicMap.get(epicId));
    }

    @Override
    public void deleteAllEpics() {
        if (epicMap.size() == 0) {
            System.out.println("Нет эпиков");
            return;
        }
        ArrayList<Integer> keySet = new ArrayList<>(epicMap.keySet());
        for (int i = 0; i <= epicMap.size(); i++) {
            for (Integer taskId : epicMap.get(keySet.get(i)).getTaskIds()) { // перебираем сабтаски
                deleteSubTask(taskId);
            }
            deleteEpic(keySet.get(i));
        }
    }

    @Override
    public void deleteEpic(int id) {
        if (epicMap.get(id) == null) {
            System.out.println("Нет эпика");
            return;
        }
        if (epicMap.get(id) != null) {
            ArrayList<Integer> taskIds = new ArrayList<>(epicMap.get(id).getTaskIds());
            for (int i = 0; i < taskIds.size(); i++) {
                // перебираем сабтаски
                deleteSubTask(taskIds.get(i));
            }
            epicMap.remove(id);
        }
    }

    private void calculateEpicStatus(Epic epic) { // расчет статуса для эпика
        if (epicMap.size() == 0 || subTaskMap.size() == 0) return;
        ArrayList<Integer> taskIds = epicMap.get(epic.getId()).getTaskIds();
        int statusNew = 0;
        int statusDone = 0;
        int subTaskInEpic = taskIds.size();
        for (Integer taskId : taskIds) { // перебираем сабтаски и добавляем количество в статусах новый и завершен
            if (subTaskMap.get(taskId).getStatus().equals(Status.NEW)) statusNew++;
            if (subTaskMap.get(taskId).getStatus().equals(Status.DONE)) statusDone++;
        }
        if (statusDone == subTaskInEpic) epic.setStatus(Status.DONE); // если равно количеству сабтасок
        if (statusNew == subTaskInEpic) epic.setStatus(Status.NEW);
        if (statusNew < subTaskInEpic && statusDone < subTaskInEpic)
            epic.setStatus(Status.IN_PROGRESS); // если меньше количества сабтасок
        //epic.setTaskIds(taskIds);
        ArrayList<Integer> idCopies = new ArrayList<>(taskIds);
        epicMap.get(epic.getId()).getTaskIds().clear();
        epic.getTaskIds().addAll(idCopies);
        epicMap.put(epic.getId(), epic); //обновляем хеш мапу обьектом
    }
}
