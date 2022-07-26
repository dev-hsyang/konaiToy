package com.konai.konaitoy.service.posts;

import com.konai.konaitoy.domain.posts.Posts;
import com.konai.konaitoy.domain.posts.PostsRepository;
import com.konai.konaitoy.web.dto.PostsResponseDTO;
import com.konai.konaitoy.web.dto.PostsSaveRequestDTO;
import com.konai.konaitoy.web.dto.PostsUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor // Constructor DI
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDTO requestDTO){
        return postsRepository.save(requestDTO.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDTO requestDTO){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        posts.update(requestDTO.getTitle(), requestDTO.getContent());
        // JPA 영속성. Entity 객체의 정보를 변경만 하면 Database에 query를 날리지 않아도 변경된다.

        return id;
    }

    public PostsResponseDTO findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDTO(entity);
    }
}