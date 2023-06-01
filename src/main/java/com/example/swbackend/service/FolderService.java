package com.example.swbackend.service;

import com.example.swbackend.DTO.FolderDto;
import com.example.swbackend.DTO.PageDto;
import com.example.swbackend.constant.Color;
import com.example.swbackend.domain.FolderEntity;
import com.example.swbackend.domain.MemberEntity;
import com.example.swbackend.mapper.FolderMapper;
import com.example.swbackend.repository.FolderRepository;
import com.example.swbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FolderService {
    private final FolderRepository folderRepository;
    private final MemberService memberService;
    private final FolderMapper folderMapper;

    private final MemberRepository memberRepository;


    @Transactional
    public FolderDto.FolderResponseDto createFolder(FolderDto.FolderPostDto postDto){
        log.info("memberId : {}",postDto.getMemberId());
        MemberEntity memberEntity = memberService.readMember(Long.valueOf(postDto.getMemberId()));

        FolderEntity folderEntity = FolderEntity.createFolder(
                memberEntity,
                postDto.getFolderTitle(),
                postDto.getColor());

        folderRepository.save(folderEntity);

        return folderMapper.folderEntityToFolderResponseDto(folderEntity);
    }


    @Transactional
    public FolderDto.FolderResponseDto createNewFolder(FolderDto.NewFolderPostDto postDto){
        log.info("memberId : {}",postDto.getMemberId());
        MemberEntity memberEntity = memberRepository.findById(postDto.getMemberId()).orElseThrow(()-> new UsernameNotFoundException("there is no member"));

        FolderEntity folderEntity = FolderEntity.createFolder(
                memberEntity,
                postDto.getFolderTitle(),
                postDto.getColor());

        folderRepository.save(folderEntity);

        return folderMapper.folderEntityToFolderResponseDto(folderEntity);
    }

    @Transactional
    public FolderDto.FolderResponseDto createNew2Folder(FolderDto.New2FolderPostDto postDto){
        log.info("memberId : {}",postDto.getMemberId());
        MemberEntity memberEntity = memberService.readMember(Long.valueOf(postDto.getMemberId()));

        FolderEntity folderEntity = FolderEntity.createFolder(
                memberEntity,
                postDto.getFolderTitle(),
                postDto.getColor());

        folderRepository.save(folderEntity);

        return folderMapper.folderEntityToFolderResponseDto(folderEntity);
    }


    @Transactional(readOnly = true)
    public PageDto<FolderDto.FolderResponseDto> readListFolder(Long userId, Integer pageNum, Integer pageSize, String sortBy){
        Pageable pageable = PageRequest.of(pageNum,pageSize, Sort.by(sortBy));
        MemberEntity memberEntity = memberService.readMember(Long.valueOf(userId));
        Page<FolderDto.FolderResponseDto> folderResponseDto =  folderRepository.findAllByMemberEntity(memberEntity, pageable)
                .map(this.folderMapper::folderEntityToFolderResponseDto);

        return PageDto.makePageDto(folderResponseDto);

    }


    @Transactional
    public FolderDto.FolderResponseDto updateFolder(FolderDto.UpdateFolderDto updateFolderDto){

        FolderEntity folderEntity = folderRepository.findById(updateFolderDto.getFolderId())

                .orElseThrow(()-> new RuntimeException("there is no folder"));

        folderEntity.updateFolder(updateFolderDto);

        return folderMapper.folderEntityToFolderResponseDto(folderEntity);
    }

    @Transactional
    public String deleteTodo(Long folderId){
        FolderEntity folderEntity = folderRepository.findById(folderId)
                .orElseThrow(()-> new RuntimeException("there is no folder"));
        folderEntity.deleteFolder();

        folderRepository.delete(folderEntity);
        return "folder delete : "+ folderId;
    }

    @Transactional(readOnly = true)
    public FolderDto.FolderResponseDto readFolder(Long folderId){
        FolderEntity folderEntity  = folderRepository.findById(folderId)
                .orElseThrow(()-> new RuntimeException("there is no folder"));

        return folderMapper.folderEntityToFolderResponseDto(folderEntity);

    }



}
