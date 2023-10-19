package com.zhehao.fishing.common.interfaces;

import com.zhehao.fishing.model.CatchEntity;

import java.util.List;

public interface CatchBatchService {
    List<CatchEntity> getCatchesByIds(List<Long> ids);
}

