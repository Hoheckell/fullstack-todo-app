package com.example.demo.consumer;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.model.Todo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TodoConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void consumeMessage(Todo todo) { // O Spring converte o JSON de volta para Todo
        System.out.println(" [AUDITORIA] Nova tarefa registrada no sistema:");
        System.out.println(" > ID: " + todo.getId());
        System.out.println(" > Título: " + todo.getTitle());
        System.out.println(" > Status: " + (todo.isCompleted() ? "Concluída" : "Pendente"));
    }
}