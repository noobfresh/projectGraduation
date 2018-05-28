package com.cqu.project.graduation.mapper;

import com.cqu.project.graduation.entity.Busstation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusstationMapper {
    int deleteByPrimaryKey(@Param("id") String id, @Param("lineNo") String lineNo);

    int insert(Busstation record);

    Busstation selectByPrimaryKey(@Param("id") String id, @Param("lineNo") String lineNo);

    List<Busstation> selectByStationName(
            @Param("stationName") String stationName, @Param("lineNo") String lineNo);

    List<Busstation> selectOnlyByStationName(
            @Param("stationName") String stationName);

    List<Busstation> selectByRange(@Param("startId") int startId,
                                   @Param("endId") int endId,
                                   @Param("lineNo") String lineNo);

    List<Busstation> selectByLineNo(@Param("lineNo") String lineNo);

    List<Busstation> selectAll();

    int updateByPrimaryKey(Busstation record);
}