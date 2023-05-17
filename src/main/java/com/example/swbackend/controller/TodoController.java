package com.example.swbackend.controller;

import com.example.swbackend.DTO.FolderDto;
import com.example.swbackend.DTO.TodoDto;
import com.example.swbackend.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @PostMapping()
    @Operation(summary = "todo 생성", description = "todo 를 생성하는 메소드 입니다")
    @ApiResponses({@ApiResponse(responseCode = "200" ,description = "todo 가 정상적으로 생성됨",
            content = @Content(schema = @Schema(implementation = TodoDto.TodoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content)})
    public ResponseEntity postTodoEntity(@RequestBody TodoDto.TodoPostDto todoPostDto){
        return ResponseEntity.ok().body(todoService.createTodo(todoPostDto));
    }

    @PatchMapping
    @Operation(summary = "todo 수정", description = "todo 수정하는")
    @ApiResponses({@ApiResponse(responseCode = "200" ,description = "todo 가 정상적으로 수정",
            content = @Content(schema = @Schema(implementation = TodoDto.TodoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content)})
    public ResponseEntity updateTodoEntity(@RequestBody TodoDto.TodoUpdateDto todoUpdateDto){
        return ResponseEntity.ok().body(todoService.updateTodoDto(todoUpdateDto));
    }


    @GetMapping
    @Operation(summary = "todo 가져오기", description = "todo 가져오는")
    @ApiResponses({@ApiResponse(responseCode = "200" ,description = "todo 가 정상적으로 수정",
            content = @Content(schema = @Schema(implementation = TodoDto.TodoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content)})
    public ResponseEntity getTodo(@RequestParam Long todoId){
        return ResponseEntity.ok().body(todoService.readTodo(todoId));
    }


    @DeleteMapping
    @Operation(summary = "todo 삭제하기", description = "todo 삭제하는")
    @ApiResponses({@ApiResponse(responseCode = "200" ,description = "todo 가 정상적으로 수정",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content)})
    public ResponseEntity deleteTodoEntity(@RequestParam Long todoId){
        return ResponseEntity.ok().body(todoService.deleteTodo(todoId));
    }


    @GetMapping("/list")
    @Operation(summary = "todo 가져오기", description = "todo 폴도 아이디를 기준으로 가져오는 method")
    @ApiResponses({@ApiResponse(responseCode = "201" ,description = "todo 가 폴더 아이디를 기준으로 가져와짐",
            content = {
                    @Content(array = @ArraySchema( schema  = @Schema (implementation = TodoDto.TodoResponseDto.class))),
            } ),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content)})
    public ResponseEntity getTodo(@RequestParam Long folderId,
                                  @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
                                  @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize,
                                  @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy){// TODO: 2023/05/17 페이지 param 받게 변경 todo folder 둘다.

        return ResponseEntity.ok().body(todoService.readListTodo(folderId, pageNo,pageSize,sortBy ));
    }



    @PatchMapping("/done")
    @Operation(summary = "todo 완료 만들기", description = "todo 완료 만들기")
    @ApiResponses({@ApiResponse(responseCode = "200" ,description = "todo 가 정상적으로 수정",
            content = @Content(schema = @Schema(implementation = TodoDto.TodoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content)})
    public ResponseEntity completeTodo(@RequestParam Long todoId, Boolean done){
        return ResponseEntity.ok().body(todoService.updateDone(todoId,done));
    }



}
