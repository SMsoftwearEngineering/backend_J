package com.example.swbackend.controller;

import com.example.swbackend.DTO.FolderDto;
import com.example.swbackend.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FolderController {
    private final FolderService folderService;

    @PostMapping
    public ResponseEntity createFolder(@RequestBody FolderDto.FolderPostDto postDto){
        return ResponseEntity.ok().body(folderService.createFolder(postDto));
    }

    @PatchMapping
    public ResponseEntity updateFolder(@RequestBody FolderDto.UpdateFolderDto updateFolderDto){
        return ResponseEntity.ok().body(folderService.updateFolder(updateFolderDto));
    }
    @DeleteMapping
    public ResponseEntity deleteFolder(@RequestParam Long folderId){
        return ResponseEntity.ok().body(folderService.deleteTodo(folderId));
    }
    @GetMapping
    public ResponseEntity readFolder(@RequestParam Long folderId){
        return ResponseEntity.ok().body(folderService.readFolder(folderId));
    }


}
