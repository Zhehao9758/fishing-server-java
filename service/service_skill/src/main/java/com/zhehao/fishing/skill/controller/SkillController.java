package com.zhehao.fishing.skill.controller;



import com.zhehao.fishing.model.SkillEntity;
import com.zhehao.fishing.skill.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/all")
    public List<SkillEntity> findAllSkills() {
        return skillService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillEntity> findSkillById(@PathVariable Long id) {
        SkillEntity skill = skillService.getSkillById(id);
        if (skill != null) {
            return ResponseEntity.ok(skill);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<SkillEntity> findSkillByName(@RequestParam("name") String name) {
        SkillEntity skill = skillService.getSkillByName(name);
        if (skill != null) {
            return ResponseEntity.ok(skill);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
