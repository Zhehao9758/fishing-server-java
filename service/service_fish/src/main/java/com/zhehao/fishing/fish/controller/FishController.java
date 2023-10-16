package com.zhehao.fishing.fish.controller;

import com.zhehao.fishing.fish.service.FishService;
import com.zhehao.fishing.model.FishEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fishes")
public class FishController {

    private final FishService fishService;

    @Autowired
    public FishController(FishService fishService) {
        this.fishService = fishService;
    }

    @GetMapping
    public List<FishEntity> findAllFish() {
        return fishService.list();
    }

    @GetMapping("/{fish_id}")
    public ResponseEntity<FishEntity> findFishById(@PathVariable Long fish_id) {
        FishEntity fish = fishService.getFishById(fish_id);
        if (fish != null) {
            return ResponseEntity.ok(fish);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<FishEntity> findFishByName(@RequestParam("name") String fish_name) {
        FishEntity fish = fishService.getFishByName(fish_name);
        if (fish != null) {
            return ResponseEntity.ok(fish);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/lists")
    public ResponseEntity<List<FishEntity>> findFishByPage(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                                           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        List<FishEntity> fishList = fishService.getFishPage(pageNum, pageSize);
        return ResponseEntity.ok(fishList);
    }

}
