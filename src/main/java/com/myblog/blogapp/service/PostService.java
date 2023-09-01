package com.myblog.blogapp.service;
import java.util.*;
import com.myblog.blogapp.payload.PostDto;
import com.myblog.blogapp.payload.PostResponse;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPost(int pageNo, int pageSize,String sortBy ,String sortDir);
//  List<PostDto> getAllPosts();
    PostDto getPostById(long id);

    PostDto updatePost(PostDto postdto, long id);

    void deletePost(long id);
}
