package domain;

public class SubTask extends Task{
    private int subTaskId;

    public SubTask(int id, String name, String description, String status, int subTaskId) {
        super(id, name, description, status);
        this.subTaskId = subTaskId;
    }

    public int getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(int subTaskId) {
        this.subTaskId = subTaskId;
    }
}
