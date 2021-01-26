package todo.list

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@MapperScan(basePackages = ["todo.list.mapper"])
class ToDoListApplication

fun main(args: Array<String>) {
    runApplication<ToDoListApplication>(*args)
}
