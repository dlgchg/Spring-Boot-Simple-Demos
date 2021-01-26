package todo.list.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import todo.list.model.Todo
import todo.list.service.ToDoService
import java.util.*

@RestController
@RequestMapping("/")
class IndexController {

    @Autowired
    lateinit var todoService: ToDoService

    @GetMapping
    fun selectMultiTodo(): Any {
        return todoService.selectMultiTodo()
    }

    @PostMapping
    fun insertTodo(@RequestParam("content") content: String) {
        val todo = Todo(0, content, Date(), 0)
        todoService.insertTodo(todo)
    }

    @PutMapping
    fun updateTodo(@RequestParam("id") id: Int,
                   @RequestParam("content") content: String,
                   @RequestParam("status") status: Int) {
        val todo = Todo(id, content, Date(), status)
        todoService.updateTodo(todo)
    }

    @DeleteMapping
    fun deleteTodo(@RequestParam("id") id: Int) {
        todoService.deleteTodo(id)
    }
}