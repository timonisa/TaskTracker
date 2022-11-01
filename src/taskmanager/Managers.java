package taskmanager;
import historymanager.InMemoryHistoryManager;
import historymanager.HistoryManager;

public class Managers {
    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }
}
