import domain.Status;
import taskmanager.TaskManager;
import taskmanager.Managers;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TaskManager tracker = Managers.getDefault();

        domain.Epic epic1 = new domain.Epic(-1, "Описание эпика1", Status.NEW,  "Эпик1");
        tracker.add(epic1);// добавили эпик1
        domain.SubTask subTask = new domain.SubTask(epic1.getId(), "подзадача1", "Описание подзадачи1", Status.NEW, -1);
        tracker.add(subTask);  // подзадача 1
        domain.SubTask subTask2 = new domain.SubTask(epic1.getId(), "подзадача2", "Описание подзадачи2", Status.NEW, -1);
        tracker.add(subTask2); // подзадача 2


        System.out.println(tracker.getEpic(epic1.getId()).toString());
        System.out.println(tracker.getSubTask(subTask.getSubTaskId()).toString());
        System.out.println(tracker.getSubTask(subTask2.getSubTaskId()).toString());



        domain.Epic epic2 = new domain.Epic(-1, "Описание эпика2",Status.NEW,  "Эпик2");
        tracker.add(epic2);// добавили эпик2
        domain.SubTask subTask3 = new domain.SubTask(epic2.getId(), "подзадача3", "Описание подзадачи3", Status.NEW, -1);
        tracker.add(subTask3);  // подзадача 1

        //System.out.println(tracker.getEpic(epic2.getId()).toString());
        //System.out.println(tracker.getSubTask(subTask3.getSubTaskId()).toString());

        //System.out.println(tracker.getAllEpics().toString());

        //System.out.println(tracker.getAllSubTasks().toString());


        domain.Task task  = new domain.Task(-1, "Задача1", "Описание задачи1", Status.NEW);
        tracker.add(task);// добавили таску
        System.out.println(tracker.getTask(task.getId()).toString());

        //System.out.println(tracker.getAllTasks().toString());

        task  = new domain.Task(task.getId(), "Измененная Задача1", "Измененное Описание задачи1", Status.IN_PROGRESS);
        tracker.update(task);// апдейт таску


        domain.Task task2  = new domain.Task(-1, "Задача2", "Описание задачи2", Status.NEW);
        tracker.add(task2);// добавили таску
        //System.out.println(tracker.getTask(task2.getId()).toString());

        //System.out.println(tracker.getAllTasks().toString());


        subTask = new domain.SubTask(epic1.getId(), "Измененная подзадача1", "Измененное описание подзадачи1", Status.DONE, subTask.getSubTaskId());
        tracker.update(subTask); // изменяем статус сабтаски на в процесса и у эпика меняется статус.
        subTask2 = new domain.SubTask(epic1.getId(), "Измененная подзадача2", "Измененное описание подзадачи2", Status.IN_PROGRESS, subTask2.getSubTaskId());
        tracker.update(subTask2); // изменяем статус сабтаски на в процесса и у эпика меняется статус.
        subTask3 = new domain.SubTask(epic2.getId(), "подзадача3", "Описание подзадачи3", Status.DONE, subTask3.getSubTaskId());
        tracker.update(subTask3); // поменяли статус сабтаски на дан, теперь и эпик быть должен дан



        epic1 = new domain.Epic(epic1.getId(), "измененное описание эпика1",Status.NEW,  "Эпик1 изменил имя");
        tracker.update(epic1);

        tracker.deleteAllSubTasks();

        //System.out.println(tracker.getAllEpics().toString());

        tracker.deleteEpic(epic1.getId());

        //System.out.println(tracker.getAllEpics().toString());



        /*System.out.println("История просмотров: ");
        List<Task> listHistory;
        listHistory = tracker.getHistoryManager().getHistory();
        for(Task taskFromHistory : listHistory){
            System.out.println(taskFromHistory.toString());
        }*/ //для тестирвоания истории

        tracker.deleteAllEpics();

        tracker.deleteAllTasks();

    }
}

