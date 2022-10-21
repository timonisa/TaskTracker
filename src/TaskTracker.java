import domain.Epic;
import domain.SubTask;
import domain.Task;
import java.util.ArrayList;
import java.util.HashMap;

public class TaskTracker {
    private  int nextId = 1;
    private HashMap<Integer, Task> taskMap = new HashMap<>();
    private HashMap<Integer, Epic> epicMap = new HashMap<>();
    private HashMap<Integer, SubTask> subTaskMap = new HashMap<>();


    public void add(Task task){
        task.setId(nextId);
        nextId++;
        taskMap.put(task.getId(), task);
    }

    public void add(Epic epic){
        if (!epic.getStatus().equals("NEW")) return;
        epic.setId(nextId);
        nextId++;
        epicMap.put(epic.getId(), epic);
    }

    public void add(SubTask subTask){
        subTask.setSubTaskId(nextId);
        nextId++;
        subTaskMap.put(subTask.getSubTaskId(), subTask); //добавили сабтаску
        epicMap.get(subTask.getId()).getTaskIds().add(subTask.getSubTaskId()); //  и привязали айдишник эпика к сабтаске

    }

    public void update(Task task){
        taskMap.put(task.getId(), task);

    }

    public void update(Epic epic){
        calculateEpicStatus(epic);
    }

    public void update(SubTask subTask){
        subTaskMap.put(subTask.getSubTaskId(), subTask);
        calculateEpicStatus(epicMap.get(subTask.getId()));
    }

    public void getAll(){
        if (epicMap.isEmpty() && subTaskMap.isEmpty() && taskMap.isEmpty()){
            System.out.println("Нет эпиков, подзадач и задач");
            return;
        }
        getAllTasks();
        ArrayList<Integer> keySet = new ArrayList<>(epicMap.keySet());
        for (int i = 0; i < epicMap.size() ; i++) {
            getAllSubTaskFromEpic(keySet.get(i));
        }
    }

    public void getAllEpics(){
        if (epicMap.size() == 0) {
            System.out.println("Нет эпиков");
            return;
        }
        ArrayList<Integer> keySet = new ArrayList<>(epicMap.keySet());
        for (int i = 0; i < epicMap.size() ; i++) {
            getEpic(keySet.get(i));
        }
    }
    
    public void getEpic(int id){
        if (epicMap.get(id) != null) {
            Epic epic = epicMap.get(id);
            System.out.println("id эпика: " + epic.getId()
                    + " Статус: " + epic.getStatus()
                    + " Имя: " + epic.getEpicName()
                    + " Описание: " + epic.getDescription());
        }
    }

    public void getAllSubTaskFromEpic(int id) {
        if (epicMap.get(id) != null) {
            if (epicMap.get(id).getTaskIds().size() == 0) {
                System.out.println("Нет подзадач");
                return;
            }
            getEpic(id);
            for (Integer taskId : epicMap.get(id).getTaskIds()) { // перебираем сабтаски
                getSubTask(taskId);
            }
        }
    }

    public void getSubTask(int id){
        if (subTaskMap.get(id) != null) {
            SubTask subTask = subTaskMap.get(id);
            System.out.println("id подзадачи: " + subTask.getSubTaskId()
                    + " Статус: " + subTask.getStatus()
                    + " Имя: " + subTask.getName()
                    + " Описание: " + subTask.getDescription());
        }else {
            System.out.println("Нет подзадачи");
        }
    }

    public void getTask(int id){
        if (taskMap.get(id) != null) {
            Task task = taskMap.get(id);
            System.out.println("id задачи: " + task.getId()
                    + " Статус: " + task.getStatus()
                    + " Имя: " + task.getName()
                    + " Описание: " + task.getDescription());
        } else {
            System.out.println("Нет задачи");
        }
    }

    public void getAllTasks(){
        if(taskMap.isEmpty()){
            System.out.println("Нет задач");
        } else {
            ArrayList<Integer> keySet = new ArrayList<>(taskMap.keySet());
            for (int i = 0; i < taskMap.size(); i++) {
                getTask(keySet.get(i));
            }
        }
    }

    public void deleteAllTasks(){
        if(taskMap.isEmpty()){
            System.out.println("Нет задач");
            return;
        }
        ArrayList<Integer> keySet = new ArrayList<>(taskMap.keySet());
        for (int i = 0; i <= taskMap.size(); i++) {
            deleteTask(keySet.get(i));
        }
    }

    public void deleteTask(int id){
        if (taskMap.get(id) == null) System.out.println("Нет задачи");
        if (taskMap.get(id) != null) taskMap.remove(id);
    }

    public void deleteAllSubTasks(int id){
        if (epicMap.get(id) != null) {
            if (epicMap.get(id).getTaskIds().size() == 0) {
                System.out.println("Нет подзадач");
                return;
            }
            getEpic(id);
            for (Integer taskId : epicMap.get(id).getTaskIds()) { // перебираем сабтаски
                deleteSubTask(taskId);
            }
        }
    }

    public void deleteSubTask(int id){
        int epicId = subTaskMap.get(id).getId();
        if (subTaskMap.get(id) == null) System.out.println("Нет подзадачи");
        if (subTaskMap.get(id) != null){
            subTaskMap.remove(id);
            epicMap.get(epicId).setTaskIds(new ArrayList<>(subTaskMap.keySet()));
        }
        calculateEpicStatus(epicMap.get(epicId));
    }

    public void deleteAllEpics(){
        if (epicMap.size() == 0) {
            System.out.println("Нет эпиков");
            return;
        }
        ArrayList<Integer> keySet = new ArrayList<>(epicMap.keySet());
        for (int i = 0; i <= epicMap.size() ; i++) {
            deleteAllSubTasks(keySet.get(i));
            deleteEpic(keySet.get(i));
        }
    }

    public void deleteEpic(int id){
        if (epicMap.size() == 0) {
            System.out.println("Нет эпика");
            return;
        }
        if (epicMap.get(id) != null) {
            deleteAllSubTasks(id);
            epicMap.remove(id);
        }
    }

    private void calculateEpicStatus(Epic epic){ // расчет статуса для эпика
        if (epicMap.size() == 0 || subTaskMap.size() == 0) return;
        ArrayList<Integer> taskIds = epicMap.get(epic.getId()).getTaskIds();
        int statusNew = 0;
        int statusDone = 0;
        int subTaskInEpic = taskIds.size();
        for (Integer taskId : taskIds) { // перебираем сабтаски и добавляем количество в статусах новый и завершен
            if (subTaskMap.get(taskId).getStatus().equals("NEW")) statusNew++;
            if (subTaskMap.get(taskId).getStatus().equals("DONE")) statusDone++;
        }
        if(statusDone == subTaskInEpic) epic.setStatus("DONE"); // если равно количеству сабтасок
        if(statusNew == subTaskInEpic) epic.setStatus("NEW");
        if(statusNew < subTaskInEpic && statusDone < subTaskInEpic) epic.setStatus("IN_PROGRESS"); // если меньше количества сабтасок
        epic.setTaskIds(taskIds);
        epicMap.put(epic.getId(), epic); //обновляем статус обьекта
    }
}
