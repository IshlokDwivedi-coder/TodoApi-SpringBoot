package com.example.todoapispring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private TodoService todoService;
    private TodoService todoService2;

    private static List<Todo> todoList;

    public static final String Todo_Not_Found="Todo not Found";



//    private TodoService todoService;  // This Statement referred as `Composition` in OOPs

    public TodoController( @Qualifier("FakeTodoService") TodoService todoService2,
                           @Qualifier("AnotherTodoService")TodoService todoService){
        this.todoService = todoService;
        this.todoService2=todoService2;
        todoList=new ArrayList<>();
        todoList.add(new Todo(1,false,"Todo 1",1));
        todoList.add(new Todo(2 ,true,"Todo 2",2));
    }


//      Dependency Inversion : DI says that we Should depends on abstraction rather than Concrete Classes . In order to implements
//         DI we should use something called as Dependency Injection


    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false) boolean isCompleted){
        System.out.println("Incoming Query Params : "+ isCompleted+" "+ todoService2.doSomething());
        return ResponseEntity.ok(todoList);
    }

    @GetMapping("/todos")
//    @RequestMapping("/api/v1/todos")    Used for versioning of api
    public List<Todo> gettodos(){
        return todoList;
    }

    @PostMapping("/todos")
//    @ResponseStatus(HttpStatus.CREATED)          This Annotation can be used to set the status code { ex : 200 OK ko 201 aise me convert kar dega }
//    ResponseEntity<> is alternative way of doing the above work
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newtodo){
        todoList.add(newtodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newtodo);
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<String> gettodoId(@PathVariable Long todoId){
        for(Todo todo : todoList){
            if(todo.getId()!=todoId) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo not found");
            }
        }
        String todo = "";
        return ResponseEntity.ok(todo);
    }

}
