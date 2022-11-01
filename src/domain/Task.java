package domain;

public class Task {
    protected int id;
    protected String name;
    protected String description;
    //protected String status;

    Status status;

    public Task(int id, String name, String description, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "id задачи: " + id
                + " Статус: " + status
                + " Имя: " + name
                + " Описание: " + description;
    }
}
