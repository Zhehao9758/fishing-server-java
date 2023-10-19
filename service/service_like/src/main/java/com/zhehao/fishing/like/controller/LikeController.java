package com.zhehao.fishing.like.controller;


import com.zhehao.fishing.exceptions.PostNotFoundException;
import com.zhehao.fishing.exceptions.UserNotFoundException;
import com.zhehao.fishing.like.service.LikeService;
import com.zhehao.fishing.model.CatchEntity;
import com.zhehao.fishing.model.CatchLikeEntity;
import com.zhehao.fishing.model.PostEntity;
import com.zhehao.fishing.model.PostLikeEntity;
import io.seata.core.exception.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/catch")
    public ResponseEntity<String> likeCatch(@RequestBody CatchLikeEntity catchLikeEntity) {
            likeService.likeCatch(catchLikeEntity);
            return ResponseEntity.ok("success");
    }

    @PostMapping("/cancel/catch")
    public ResponseEntity<String> unlikeCatch(@RequestBody CatchLikeEntity catchLikeEntity) {
        likeService.unlikeCatch(catchLikeEntity);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/post")
    public ResponseEntity<String> likePost(@RequestBody PostLikeEntity postLikeEntity) {
        likeService.likePost(postLikeEntity);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/cancel/post")
    public ResponseEntity<String> unlikePost(@RequestBody PostLikeEntity postLikeEntity) {
        likeService.unlikePost(postLikeEntity);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/catch")
    public ResponseEntity<List<CatchEntity>> myLikesOfCatch(@RequestParam("userId") Long userId){
        try{
            List<CatchEntity> catches = likeService.myLikesOfCatch(userId);
            return ResponseEntity.ok(catches);
        } catch (UserNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/post")
    public ResponseEntity<List<PostEntity>> myLikesOfPost(@RequestParam("userId") Long userId){
        try{
            List<PostEntity> posts = likeService.myLikesOfPost(userId);
            return ResponseEntity.ok(posts);
        } catch (UserNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
