package ru.itpark.noteswithsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class NoteEntity {
    @Id
    @GeneratedValue
    private int id;
    // private int ownerId; -> id'шник пользователя, который добавил, надо записывать руками
    private String name;
    private String content;
}
