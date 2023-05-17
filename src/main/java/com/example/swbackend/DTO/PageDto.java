package com.example.swbackend.DTO;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class  PageDto<T> {
    List<T> content;
    int currentPageNum;
    int size;
    Long totalElements;
    int totalPages;

    public static <T> PageDto<T> makePageDto(Page<T> pageobj){
        PageDto<T> pageDto = new PageDto<>();
        pageDto.setContent(pageobj.getContent());
        pageDto.setTotalPages(pageobj.getTotalPages());
        pageDto.setCurrentPageNum(pageobj.getNumber());
        pageDto.setTotalElements(pageobj.getTotalElements());
        return pageDto;
    }
}
