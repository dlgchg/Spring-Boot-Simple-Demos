package todo.list.service

import todo.list.model.Todo

interface ToDoService {
    fun selectMultiTodo(): ArrayList<Todo>
    fun insertTodo(todo: Todo)
    fun deleteTodo(id: Int)
    fun updateTodo(todo: Todo)
}
