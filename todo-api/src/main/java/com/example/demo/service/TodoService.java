package com.example.demo.service;

import com.example.demo.dto.TodoRequest;
import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.example.demo.config.RabbitMQConfig;

@Service
@RequiredArgsConstructor // Cria construtor para o 'repository' final
public class TodoService {

    private final TodoRepository repository;
    private final RabbitTemplate rabbitTemplate;

    public List<Todo> getAll() {
        return repository.findAll();
    }

    public Todo create(TodoRequest request) {
        Todo todo = new Todo();
        todo.setTitle(request.title());
        todo.setDescription(request.description());
        
        Todo saved = repository.save(todo);
        
        // Agora enviamos o objeto Todo completo! 
        // O Jackson que adicionamos fará a conversão para JSON automaticamente.
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, saved);
        
        return saved;
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