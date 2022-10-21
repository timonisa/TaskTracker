public class Main {
    public static void main(String[] args) {
        TaskTracker tracker = new TaskTracker(); //создали новый трекер
        /* для тестирования раскомментить
        tracker.getAllEpics(); //спрашиваем эпики когда их нет, ЕСТЬ ВОПРОС : Я ДОЛЖЕН ВОЗВРАЩАТЬ ОБЬЕКТЫ ИЛИ СТРОКИ???
        tracker.getAllTasks(); // про задачи спросили когда их нет

        domain.Epic epic1 = new domain.Epic(-1, "Описание эпика1","NEW",  "Эпик1");
        tracker.add(epic1);// добавили эпик1
        tracker.getAllSubTaskFromEpic(epic1.getId()); // печатаем все сабтаски эпика

        domain.SubTask subTask = new domain.SubTask(epic1.getId(), "подзадача1", "Описание подзадачи1", "NEW", -1);
        tracker.add(subTask);  // подзадача 1

        tracker.getSubTask(subTask.getSubTaskId()); // смотрим подзадачу

        domain.SubTask subTask2 = new domain.SubTask(epic1.getId(), "подзадача2", "Описание подзадачи2", "NEW", -1);
        tracker.add(subTask2); // подзадача 2

        tracker.getAllSubTaskFromEpic(epic1.getId()); // печатаем все сабтаски эпика

        domain.Epic epic2 = new domain.Epic(-1, "Описание эпика2","NEW",  "Эпик2");
        tracker.add(epic2);// добавили эпик2
        domain.SubTask subTask3 = new domain.SubTask(epic2.getId(), "подзадача1", "Описание подзадачи1", "NEW", -1);
        tracker.add(subTask3);  // подзадача 1

        tracker.getAllEpics(); // смотрим все эпики


        domain.Task task  = new domain.Task(-1, "Задача1", "Описание задачи1", "NEW");
        tracker.add(task);// добавили таску

        tracker.getTask(task.getId());

        task  = new domain.Task(task.getId(), "Измененная Задача1", "Измененное Описание задачи1", "IN_PROGRESS");
        tracker.update(task);// апдейт таску

        tracker.getTask(task.getId());

        domain.Task task2  = new domain.Task(-1, "Задача2", "Описание задачи2", "NEW");
        tracker.add(task2);// добавили таску

        tracker.getAllTasks();

        subTask = new domain.SubTask(epic1.getId(), "Измененная подзадача1", "Измененное описание подзадачи1", "DONE", subTask.getSubTaskId());
        tracker.update(subTask); // изменяем статус сабтаски на в процесса и у эпика меняется статус.
        subTask2 = new domain.SubTask(epic1.getId(), "Измененная подзадача2", "Измененное описание подзадачи2", "IN_PROGRESS", subTask2.getSubTaskId());
        tracker.update(subTask2); // изменяем статус сабтаски на в процесса и у эпика меняется статус.

        subTask3 = new domain.SubTask(epic2.getId(), "подзадача1", "Описание подзадачи1", "DONE", subTask3.getSubTaskId());
        tracker.update(subTask3); // поменяли статус сабтаски на дан, теперь и эпик быть должен дан


        epic1 = new domain.Epic(epic1.getId(), "измененное описание эпика1","NEW",  "Эпик1 изменил имя");
        tracker.update(epic1);

        tracker.getAll();

        tracker.deleteEpic(epic1.getId());

        tracker.getAll();

        tracker.deleteAllTasks();

        tracker.getAll();

        tracker.deleteAllEpics();

        tracker.getAll();*/

    }
}