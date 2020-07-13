package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.entity.AchieveDegree;

public interface AchieveDegreeMapper {
    void delete(Integer id);

    void insert(AchieveDegree achieveDegree);

    void update(AchieveDegree achieveDegree);
    
    List<AchieveDegree> listByAchieveDegree(AchieveDegree achieveDegree);
    
    void updateScore(AchieveDegree achieveDegree);
}