package todo.list.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import todo.list.mapper.TodoMapper
import todo.list.model.Todo
import todo.list.service.ToDoService

@Service
class ToDoServiceImpl : ToDoService {

    @Autowired
    lateinit var todoMapper: TodoMapper

    override fun selectMultiTodo(): ArrayList<Todo> = todoMapper.selectMultiTodo()

    override fun insertTodo(todo: Todo) = todoMapper.insertTodo(todo)

    override fun deleteTodo(id: Int) = todoMapper.deleteTodo(id)

    override fun updateTodo(todo: Todo) = todoMapper.updateTodo(todo)
}