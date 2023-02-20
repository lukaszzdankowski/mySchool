package my.school.reply;

import my.school.homework.Homework;
import my.school.task.Task;

import javax.persistence.*;

@Entity
@Table(name = "replies")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "task_id")
    private Task task;
    @ManyToOne
    private Homework homework;

    private Double answer;

    public Reply() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public Double getAnswer() {
        return answer;
    }

    public void setAnswer(Double answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", task=" + task +
                ", homework=" + homework +
                ", answer=" + answer +
                '}';
    }
}
