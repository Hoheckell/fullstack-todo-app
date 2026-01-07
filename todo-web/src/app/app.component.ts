import { Component, OnInit, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TodoService } from './services/todo.service'; // Assumindo que criou o service antes
import { Todo } from './models/todo.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  private todoService = inject(TodoService);

  // Signals: Reatividade granular (Estilo Vue 3/Solid.js)
  todos = signal<Todo[]>([]);
  newTodoTitle = signal('');

  ngOnInit() {
    this.loadTodos();
  }

  loadTodos() {
    this.todoService.list().subscribe(data => this.todos.set(data));
  }

  addTodo() {
    if (this.newTodoTitle().trim()) {
      const newTodo: Todo = {
        title: this.newTodoTitle(),
        completed: false,
        description: ''
      };
      this.todoService.save(newTodo).subscribe(() => {
        this.newTodoTitle.set('');
        this.loadTodos();
      });
    }
  }
  
  toggleTodo(todo: Todo) {
    const updatedTodo = { ...todo, completed: !todo.completed };
    this.todoService.update(todo.id!, updatedTodo).subscribe(() => this.loadTodos());
  }

  deleteTodo(id: number) {
    this.todoService.delete(id).subscribe(() => this.loadTodos());
  }
}
