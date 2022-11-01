package domain;

import java.util.ArrayList;

public class Epic extends Task {
    private String epicName;
    private ArrayList<Integer> taskIds = new ArrayList<>();

    public ArrayList<Integer> getTaskIds() {
        return taskIds;
    }

    public Epic(int id, String description, Status status, String epicName) {
        super(id, "null", description, status);
        this.epicName = epicName;
    }


    @Override
    public String toString() {
        return "id эпика: " + id
                + " Статус: " + status
                + " Имя: " + epicName
                + " Описание: " + description;
    }
}
