package com.zhehao.fishing.skill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhehao.fishing.model.SkillEntity;

import java.util.List;

public interface SkillService extends IService<SkillEntity> {
    SkillEntity getSkillById(long id);
    SkillEntity getSkillByName(String name);
}
