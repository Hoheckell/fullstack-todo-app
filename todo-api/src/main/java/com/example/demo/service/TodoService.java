package com.example.demo.service;

import com.example.demo.dto.TodoRequest;
import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor // Cria construtor para o 'repository' final
public class TodoService {

    private final TodoRepository repository;

    public List<Todo> getAll() {
        return repository.findAll();
    }

    public Todo create(TodoRequest request) {
        var todo = new Todo();
        todo.setTitle(request.title());
        todo.setDescription(request.description());
        todo.setCompleted(request.completed());
        return repository.save(todo);
    }
    
    public Todo update(Long id, TodoRequest request) {
        Todo todo = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada")); // Em produção, usaríamos uma Exception customizada
        
        todo.setTitle(request.title());
        todo.setDescription(request.description());
        todo.setCompleted(request.completed());
        
        return repository.save(todo);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada");
        }
        repository.deleteById(id);
    }
}