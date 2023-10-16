package com.zhehao.fishing.skill.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhehao.fishing.model.SkillEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SkillMapper extends BaseMapper<SkillEntity> {
    SkillEntity getSkillById(long id);
    SkillEntity getSkillByName(String name);



}
