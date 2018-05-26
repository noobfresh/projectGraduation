package com.cqu.graduation.pyf.graduationprojectavgtraveltime.model;

import com.cqu.graduation.pyf.graduationprojectavgtraveltime.bean.AvgTime;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by PYF on 2018/5/24.
 */

public interface IRequestAvgTime {

    @GET("android/railwayCaculate")
    Call<AvgTime> getRailWayCall(@Query("startStation") String startStation,
                          @Query("endStation") String endStation,
                          @Query("startTime") String startTime,
                          @Query("weekday") String weekday);

    /**
     *
     * @param lineNo
     * @param startStation
     * @param endStation
     * @param weekday
     * @param startTime
     * @param startDirection 线路的起点站
     * @return
     */
    Call<AvgTime> getBusCall(@Query("lineNo") String lineNo,
                             @Query("startStation") String startStation,
                             @Query("endStation") String endStation,
                             @Query("weeekDay") String weekday,
                             @Query("startTime") String startTime,
                             @Query("startDirection") String startDirection);
}
