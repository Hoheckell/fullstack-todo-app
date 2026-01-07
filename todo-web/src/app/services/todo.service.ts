import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Todo } from '../models/todo.model';

@Injectable({ providedIn: 'root' })
export class TodoService {
  private http = inject(HttpClient);
  // Lembra-te: a porta 8081 é a que definimos no Spring para evitar conflitos
  private readonly API = 'http://localhost:8081/api/todos';

  list() {
    return this.http.get<Todo[]>(this.API);
  }

  save(todo: Todo) {
    return this.http.post<Todo>(this.API, todo);
  }
  
  update(id: number, todo: Todo) {
    return this.http.put<Todo>(`${this.API}/${id}`, todo);
  }

  delete(id: number) {
    return this.http.delete(`${this.API}/${id}`);
  }
}
