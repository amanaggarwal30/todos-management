package net.guides.todo.todomanagement.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column(nullable = false)
    private String userName;

    @Column
    @Size(min = 10, message = "Enter at least 10 charaters")
    private String description;

    @Column
    private Date targetDate;

    public Todo() {
        super();
    }

    public Todo(String userName, @Size(min = 10, message = "Enter at least 10 charaters") String description, Date targetDate) {
        super();
        this.userName = userName;
        this.description = description;
        this.targetDate = targetDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }
}
