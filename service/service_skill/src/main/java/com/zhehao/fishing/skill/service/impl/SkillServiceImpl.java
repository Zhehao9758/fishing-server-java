package com.zhehao.fishing.skill.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhehao.fishing.model.SkillEntity;
import com.zhehao.fishing.skill.mapper.SkillMapper;
import com.zhehao.fishing.skill.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl extends ServiceImpl<SkillMapper, SkillEntity> implements SkillService {
    private final SkillMapper skillMapper;

    @Autowired
    public SkillServiceImpl(SkillMapper skillMapper){
        this.skillMapper = skillMapper;
    }



    @Override
    public SkillEntity getSkillById(long id) {
        return skillMapper.getSkillById(id);
    }

    @Override
    public SkillEntity getSkillByName(String name) {
        return skillMapper.getSkillByName(name);
    }
}
