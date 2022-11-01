package domain;

public class SubTask extends Task {
    private int subTaskId;

    public SubTask(int id, String name, String description, Status status, int subTaskId) {
        super(id, name, description, status);
        this.subTaskId = subTaskId;
    }

    public int getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(int subTaskId) {
        this.subTaskId = subTaskId;
    }

    @Override
    public String toString() {
        return "id подзадачи: " + subTaskId
                + " Статус: " + status
                + " Имя: " + name
                + " Описание: " + description;
    }
}

