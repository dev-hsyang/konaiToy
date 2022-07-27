package com.konai.konaitoy.web;

import com.konai.konaitoy.service.posts.PostsService;
import com.konai.konaitoy.web.dto.PostsResponseDTO;
import com.konai.konaitoy.web.dto.PostsSaveRequestDTO;
import com.konai.konaitoy.web.dto.PostsUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    // post 등록 'C'
    @PostMapping("/api/v1/posts")
    public Long save (@RequestBody PostsSaveRequestDTO requestDTO){
        return postsService.save(requestDTO);
    }

    // post 조회 'R'
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDTO findById (@PathVariable Long id){
        return postsService.findById(id);
    }

    // post 수정 'U'
    @PutMapping ("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDTO requestDTO){
        return postsService.update(id, requestDTO);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
