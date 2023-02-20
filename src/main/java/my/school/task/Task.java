package my.school.task;

import my.school.exam.Exam;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    @NotEmpty
    @NotBlank
    private String content;
    private double result;
    @ManyToMany(mappedBy = "tasks", fetch = FetchType.EAGER)
    private List<Exam> exams = new ArrayList<>();

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", result=" + result +
                ", exams=" + exams +
                '}';
    }
}
