package todo.list.mapper

import org.apache.ibatis.annotations.Mapper
import todo.list.model.Todo

@Mapper
interface TodoMapper {
    fun selectMultiTodo(): ArrayList<Todo>
    fun insertTodo(todo: Todo)
    fun deleteTodo(id: Int)
    fun updateTodo(todo: Todo)
}
