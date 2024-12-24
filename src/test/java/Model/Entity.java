package Model;

import java.util.Objects;

public class Entity {
    private int id;
    private String student;

    public Entity(int id, String student){
        this.id = id;
        this.student = student;
    }

    public Entity() {
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", student='" + student + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return id == entity.id && Objects.equals(student, entity.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student);
    }
}
