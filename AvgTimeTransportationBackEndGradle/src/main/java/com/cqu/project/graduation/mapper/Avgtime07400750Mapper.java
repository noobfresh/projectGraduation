package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Avgtime07400750;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Avgtime07400750Mapper {
    int deleteByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    int insert(Avgtime07400750 record);

    Avgtime07400750 selectByPrimaryKey(@Param("startStation") String startStation, @Param("endStation") String endStation);

    List<Avgtime07400750> selectAll();

    int updateByPrimaryKey(Avgtime07400750 record);
}