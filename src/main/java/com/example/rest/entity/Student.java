package com.example.rest.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentName;
    private String groupName;

    public Student() {
    }

    public Student(String name, String group) {
        this.studentName = name;
        this.groupName = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String name) {
        this.studentName = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String group) {
        this.groupName = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(studentName, student.studentName) &&
                Objects.equals(groupName, student.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentName, groupName);
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + studentName + '\'' +
                ", group='" + groupName + '\'' + '}';
    }
}
