package com.zhehao.fishing.catches.controller;

import com.zhehao.fishing.catches.service.CatchService;
import com.zhehao.fishing.exceptions.CatchNotFoundException;
import com.zhehao.fishing.model.CatchEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/catches")
public class CatchController {

    private final CatchService catchService;

    @Autowired
    public CatchController(CatchService catchService) {
        this.catchService = catchService;
    }

    @GetMapping("/all")
    public List<CatchEntity> findAllCatches() {
        return catchService.list();
    }


    @PostMapping
    public ResponseEntity<String> addCatch(@RequestBody CatchEntity catchEntity){
        try {
            catchEntity.setLikes(0L);
            catchService.insertCatch(catchEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Catch published successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error occurred");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatchEntity> findCatchById(@PathVariable Long id) {
        CatchEntity catchEntity = catchService.getCatchById(id);
        if (catchEntity != null) {
            return ResponseEntity.ok(catchEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/myCatches")
    public ResponseEntity<List<CatchEntity>> getCatchesByUserIdOrderedByTime(@RequestParam("userId") Long user_id){
        List<CatchEntity> catches = catchService.getCatchesByUserIdOrderedByTime(user_id);
        if(catches == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(catches);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateCatch(@RequestBody CatchEntity catchEntity){
        try {
            catchService.updateCatch(catchEntity);
            return ResponseEntity.ok("Updated catch Successfully");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error occurred");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCatchById(@PathVariable Long id){
        try {
            catchService.deleteCatchById(id);
            return ResponseEntity.ok("Deleted catch Successfully");
        }catch (CatchNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error occurred");
        }
    }

    @GetMapping("/lists")
    public ResponseEntity<List<CatchEntity>> findCatchByPage(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                                           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        List<CatchEntity> catchList = catchService.getCatchPage(pageNum, pageSize);
        return ResponseEntity.ok(catchList);
    }



}
