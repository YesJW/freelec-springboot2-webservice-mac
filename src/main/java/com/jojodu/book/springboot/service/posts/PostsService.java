package com.jojodu.book.springboot.service.posts;

import com.jojodu.book.springboot.domain.posts.PostRepository;
import com.jojodu.book.springboot.domain.posts.Posts;
import com.jojodu.book.springboot.web.dto.PostsListResponseDto;
import com.jojodu.book.springboot.web.dto.PostsResponseDto;
import com.jojodu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojodu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;

import  org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostRepository repository;
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return repository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = repository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("해당 게시글이 없습니다. id = "+ id));

        posts.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return repository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = repository.findById(id)
                .orElseThrow(() -> new IllegalIdentifierException("해당 게시글이 없습니다. id= "+ id));
        repository.delete(posts);

    }
}
