package org.jit.sose.service;

import org.jit.sose.entity.AchieveDegree;

import com.github.pagehelper.PageInfo;

public interface AchieveDegreeService {

	void delete(Integer id);

    void insert(AchieveDegree achieveDegree);

    void update(AchieveDegree achieveDegree);
    
    PageInfo<AchieveDegree> selectPageInfo(AchieveDegree achieveDegree , Integer pageNum, Integer pageSize);
    
    void updateScore(AchieveDegree achieveDegree);
}
