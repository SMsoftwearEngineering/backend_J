package com.example.swbackend.controller;

import com.example.swbackend.DTO.TodoDto;
import com.example.swbackend.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @PostMapping()
    public ResponseEntity postTodoEntity(@RequestBody TodoDto.TodoPostDto todoPostDto){
        return ResponseEntity.ok().body(todoService.createTodo(todoPostDto));
    }

    @PatchMapping
    public ResponseEntity updateTodoEntity(@RequestBody TodoDto.TodoUpdateDto todoUpdateDto){
        return ResponseEntity.ok().body(todoService.updateTodoDto(todoUpdateDto));
    }

    @GetMapping
    public ResponseEntity getTodo(@RequestParam Long todoId){
        return ResponseEntity.ok().body(todoService.readTodo(todoId));
    }


    @DeleteMapping
    public ResponseEntity deleteTodoEntity(@RequestParam Long todoId){
        return ResponseEntity.ok().body(todoService.deleteTodo(todoId));
    }

}
