package ru.itpark.noteswithsecurity.service;

import org.springframework.stereotype.Service;
import ru.itpark.noteswithsecurity.entity.NoteEntity;
import ru.itpark.noteswithsecurity.repository.NoteRepository;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public List<NoteEntity> findAll() {
        return repository.findAll();
    }

    public NoteEntity findById(int id) {
        return repository.findById(id)
                .orElseThrow(); // TODO: throw specific exception
    }

    public void removeById(int id) {
        repository.deleteById(id);
    }

    public void add(NoteEntity entity) {
        repository.save(entity); // TODO: add edit functionality
    }
}
