package com.example.swbackend.controller;

import com.example.swbackend.DTO.FolderDto;
import com.example.swbackend.service.FolderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
@RestController
@RequiredArgsConstructor
@RequestMapping("folder")
public class FolderController {
    private final FolderService folderService;

    @PostMapping
    @Operation(summary = "폴더 생성", description = "폴더 생성하기")
    @ApiResponses({@ApiResponse(responseCode = "200" ,description = "폴더가 정상적으로 생성됨",
            content = @Content(schema = @Schema(implementation = FolderDto.FolderResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content)})
    public ResponseEntity createFolder(@RequestBody FolderDto.FolderPostDto postDto){
        return ResponseEntity.ok().body(folderService.createFolder(postDto));
    }

    @PostMapping("/new")
    @Operation(summary = "폴더 생성", description = "폴더 생성하기")
    @ApiResponses({@ApiResponse(responseCode = "200" ,description = "폴더가 정상적으로 생성됨",
            content = @Content(schema = @Schema(implementation = FolderDto.FolderResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content)})
    public ResponseEntity creatNewFolder(@RequestBody FolderDto.NewFolderPostDto postDto){
        return ResponseEntity.ok().body(folderService.createNewFolder(postDto));
    }

    @PostMapping("/new2")
    @Operation(summary = "폴더 생성", description = "폴더 생성하기")
    @ApiResponses({@ApiResponse(responseCode = "200" ,description = "폴더가 정상적으로 생성됨",
            content = @Content(schema = @Schema(implementation = FolderDto.FolderResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content)})
    public ResponseEntity creatNewFolder(@RequestBody FolderDto.New2FolderPostDto postDto){
        return ResponseEntity.ok().body(folderService.createNew2Folder(postDto));
    }

    @PatchMapping
    @Operation(summary = "폴더 수정", description = "폴더 수정하기")
    @ApiResponses({@ApiResponse(responseCode = "200" ,description = "폴더가 정상적으로 수정됨",
            content = @Content(schema = @Schema(implementation = FolderDto.FolderResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content)})
    public ResponseEntity updateFolder(@RequestBody FolderDto.UpdateFolderDto updateFolderDto){
        return ResponseEntity.ok().body(folderService.updateFolder(updateFolderDto));
    }
    @DeleteMapping
    @Operation(summary = "폴더 삭제", description = "폴더 삭제")
    @ApiResponses({@ApiResponse(responseCode = "201" ,description = "폴더 삭제하기",
            content = @Content(schema = @Schema(implementation = FolderDto.FolderResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content)})
    public ResponseEntity deleteFolder(@RequestParam Long folderId){
        return ResponseEntity.ok().body(folderService.deleteTodo(folderId));
    }

    @GetMapping
    @Operation(summary = "폴더 가져오기", description = "폴더 가져오기")
    @ApiResponses({@ApiResponse(responseCode = "201" ,description = "폴더가 정상적으로 가져와짐",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content)})
    public ResponseEntity readFolder(@RequestParam Long folderId){
        return ResponseEntity.ok().body(folderService.readFolder(folderId));
    }


    @GetMapping("list/")
    @Operation(summary = "폴더 가져오기", description = "유저에 해당한 폴더 가져오기")
    @ApiResponses({@ApiResponse(responseCode = "201" ,description = "폴더가 유저 아이디를 기준으로 가져와짐",
            content = {
                    @Content(array = @ArraySchema( schema  = @Schema (implementation = FolderDto.FolderResponseDto.class))),
            } ),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content)})
    public ResponseEntity readFolder(@RequestParam Long userId,
                                     @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
                                     @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize,
                                     @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy){
        return ResponseEntity.ok().body(folderService.readListFolder(userId, pageNo,pageSize,sortBy));
    }


}
