package com.myblog.blogapp.controller;
import com.myblog.blogapp.payload.PostResponse;
import com.myblog.blogapp.service.PostService;
import com.myblog.blogapp.payload.PostDto;


import com.myblog.blogapp.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping("/api/posts")
public class PostController {

     private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


//    @GetMapping
//    public List<PostDto> getAllPosts(){
//        return postService.getAllPosts();
//    }

    @GetMapping
    public PostResponse getAllPost(
            @RequestParam(value="pageNo",defaultValue=AppConstants.DEFAULT_PAGE_NUMBER,required=false)int pageNo,
            @RequestParam(value="pageSize",defaultValue=AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
            @RequestParam(value="sortBy",defaultValue=AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
            @RequestParam(value="sortDir",defaultValue=AppConstants.DEFAULT_SORT_DIR,required = false)String sortDir
    ){
        return postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postdto, BindingResult results){
        if(results.hasErrors()){
            Map<String,String> error =  new HashMap<>();
             results.getFieldErrors().forEach(er->{
                error.put(er.getField(), er.getDefaultMessage());
             });
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

        }
        PostDto postdto1 =postService.createPost(postdto);
        return new ResponseEntity<>(postdto1,HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        PostDto postById = postService.getPostById(id);
        return ResponseEntity.ok(postById);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postdto,@PathVariable("id") long id){
        PostDto postDto = postService.updatePost(postdto, id);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post entity deleted successfully",HttpStatus.OK);
    }
}
