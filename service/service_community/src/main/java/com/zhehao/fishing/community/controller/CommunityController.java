package com.zhehao.fishing.community.controller;

import com.zhehao.fishing.community.service.CommunityService;
import com.zhehao.fishing.exceptions.PostNotFoundException;
import com.zhehao.fishing.model.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/community")
public class CommunityController {

    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping
    public List<PostEntity> findAllPosts() {
        return communityService.list();
    }

    @PostMapping
    public ResponseEntity<String> addPost(@RequestBody PostEntity postEntity){
        try {
            postEntity.setLikesNum(0);
            postEntity.setCommentsNum(0);
            communityService.insertPost(postEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Post published successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error occurred");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostEntity> findPostById(@PathVariable Long id) {
        PostEntity postEntity = communityService.getPostById(id);
        if (postEntity != null) {
            return ResponseEntity.ok(postEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/myPosts")
    public ResponseEntity<List<PostEntity>> getCatchesByUserIdOrderedByTime(@RequestParam("userId") Long user_id){
        List<PostEntity> posts = communityService.getPostsByUserIdOrderedByTime(user_id);
        if(posts == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(posts);
        }
    }

    @PutMapping
    public ResponseEntity<String> updatePost(@RequestBody PostEntity postEntity){
        try {
            communityService.updatePost(postEntity);
            return ResponseEntity.ok("Updated Post Successfully");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error occurred");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Long id){
        try {
            communityService.deletePostById(id);
            return ResponseEntity.ok("Deleted Post Successfully");
        }catch (PostNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error occurred");
        }
    }

    @GetMapping("/lists")
    public ResponseEntity<List<PostEntity>> findCatchByPage(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                                             @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        List<PostEntity> postList = communityService.getPostPage(pageNum, pageSize);
        return ResponseEntity.ok(postList);
    }

}
