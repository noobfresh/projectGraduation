package com.cqu.graduation.pyf.graduationprojectavgtraveltime.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.help.Tip;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.BusStep;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RailwayStationItem;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteBusWalkItem;
import com.amap.api.services.route.RouteRailwayItem;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.R;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.adapter.BusResultListAdapter;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.bean.AvgTime;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.bean.BusData;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.model.Constants;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.model.IRequestAvgTime;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.util.AMapUtil;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.util.TimeUtil;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoutePlanActivity extends AppCompatActivity implements View.OnClickListener,
        RouteSearch.OnRouteSearchListener, Comparator<BusPath>{

    private static final String TAG = "RoutePlanActivity";

    private static final int REQUEST_CODE = 1;
    private static final String REQUEST_PARAM_START = "startPosition";
    private static final String REQUEST_PARAM_END = "endPosition";

    public static final int RESULT_CODE_START = 11;
    public static final int RESULT_CODE_END = 22;

    private final int ROUTE_TYPE_BUS = 1;
    private final int ROUTE_TYPE_DRIVE = 2;
    private final int ROUTE_TYPE_WALK = 3;
    private final int ROUTE_TYPE_CROSSTOWN = 4;


    private TextView startStation;
    private TextView endStation;

    private TextView dateTextView;
    private TextView timeTextView;
//    private TimePicker timePicker;
//    private DatePicker datePicker;
    private Button searchBtn;
    private ListView busResultList;

    private String time = TimeUtil.time();
    private String date = TimeUtil.date();
    private BusRouteResult mBusRouteResult;
    private RouteSearch routeSearch;

    private Tip startTip;
    private Tip endTip;
    private LatLonPoint mStartPoint;
    private LatLonPoint mEndPoint;

    private String[] interval = {"00", "10", "20", "30", "40", "50"};

    private Retrofit retrofit;
    private static final String httpurl = "http://192.168.229.1:8080/index/";

    private BusResultListAdapter mBusResultListAdapter;

    private ProgressBar progressBar = null;// 搜索时进度条


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_plan);

        startStation = findViewById(R.id.startPosition);
        endStation = findViewById(R.id.endPosition);

        startStation.setOnClickListener(this);
        endStation.setOnClickListener(this);

        progressBar = findViewById(R.id.progressBar);

        searchBtn = findViewById(R.id.search_button);
        searchBtn.setOnClickListener(this);

        dateTextView = findViewById(R.id.date);
        dateTextView.setOnClickListener(this);
        timeTextView = findViewById(R.id.time);
        timeTextView.setOnClickListener(this);

        busResultList = findViewById(R.id.bus_result_list);

        routeSearch = new RouteSearch(this);
        routeSearch.setRouteSearchListener(this);

        		retrofit = new Retrofit.Builder()
				.baseUrl(httpurl)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.startPosition:
                Intent intent1 = new Intent(this, InputTipsActivity.class);
                intent1.putExtra("position", REQUEST_PARAM_START);
                startActivityForResult(intent1, REQUEST_CODE);
                break;
            case R.id.endPosition:
                Intent intent2 = new Intent(this, InputTipsActivity.class);
                intent2.putExtra("position", REQUEST_PARAM_END);
                startActivityForResult(intent2, REQUEST_CODE);
                break;
            case R.id.date:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        AlertDialog.THEME_HOLO_LIGHT,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                date = TimeUtil.formatDate(i1+1, i2);
                                dateTextView.setText((i1 + 1) + "月" + i2 + "日");
                                Log.d(TAG, "onDateSet: " + date);
                            }
                        }, TimeUtil.year(), TimeUtil.month(), TimeUtil.day());

                datePickerDialog.show();
                break;
            case R.id.time:
                DurationTimePickDialog timePickerDialog = new DurationTimePickDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        time = TimeUtil.formatTime(i, i1);
                        timeTextView.setText(i + ":" + i1);
                        Log.d(TAG, "onTimeSet: " + time);
                    }
                }, TimeUtil.hour(), TimeUtil.min(), true, 10);
                timePickerDialog.show();
                break;
            case R.id.search_button:
                //数据库查询
                searchRouteResult(ROUTE_TYPE_BUS, RouteSearch.BusDefault);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CODE_START && data != null){
            startTip = data.getParcelableExtra(Constants.ROUTE_EXTRA_TIP);
            startStation.setText("起点：        " + startTip.getName());
        } else if (resultCode == RESULT_CODE_END && data != null) {
            endTip = data.getParcelableExtra(Constants.ROUTE_EXTRA_TIP);
            endStation.setText("终点：        " + endTip.getName());
        }
    }



    /**
     * 得到timePicker里面的android.widget.NumberPicker组件 （有两个android.widget.NumberPicker组件--hour，minute）
     * @param viewGroup
     * @return
     */
    private List<NumberPicker> findNumberPicker(ViewGroup viewGroup)
    {
        List<NumberPicker> npList = new ArrayList<NumberPicker>();
        View child = null;

        if (null != viewGroup)
        {
            for (int i = 0; i < viewGroup.getChildCount(); i++)
            {
                child = viewGroup.getChildAt(i);
                if (child instanceof NumberPicker)
                {
                    npList.add((NumberPicker)child);
                }
                else if (child instanceof LinearLayout)
                {
                    List<NumberPicker> result = findNumberPicker((ViewGroup)child);
                    if (result.size() > 0)
                    {
                        return result;
                    }
                }
            }
        }
        return npList;
    }

    /**
     * 查找timePicker里面的android.widget.NumberPicker组件 ，并对其进行时间间隔设置
     * @param viewGroup  TimePicker timePicker
     */
    private void setNumberPickerTextSize(ViewGroup viewGroup){
        List<NumberPicker> npList = findNumberPicker(viewGroup);
        if (null != npList)
        {
            for (NumberPicker mMinuteSpinner : npList)
            {
//              System.out.println("mMinuteSpinner.toString()="+mMinuteSpinner.toString());
                if(mMinuteSpinner.toString().contains("id/minute")){//对分钟进行间隔设置

                    mMinuteSpinner.setMinValue(0);
                    mMinuteSpinner.setMaxValue(interval.length-1);
                    mMinuteSpinner.setDisplayedValues(interval);  //这里的minuts是一个String数组，就是要显示的分钟值
                }
                //对小时进行间隔设置 使用 if(mMinuteSpinner.toString().contains("id/hour")){}即可
            }
        }
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {
//        aMap.clear();// 清理地图上的所有覆盖物
        if (i == 1000) {
            if (busRouteResult != null && busRouteResult.getPaths() != null) {
                if (busRouteResult.getPaths().size() > 0) {
                    mBusRouteResult = busRouteResult;
                    mBusResultListAdapter = new BusResultListAdapter(
                            this, mBusRouteResult, date, time);
                    busResultList.setAdapter(mBusResultListAdapter);
                    dealWithResultTime();

                } else if (busRouteResult != null && busRouteResult.getPaths() == null) {
                    Toast.makeText(this, "没结果", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "没结果", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "没结果", Toast.LENGTH_SHORT).show();
        }
        dissmissProgressDialog();
    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }

    /**
     * 开始搜索路径规划方案
     */
    public void searchRouteResult(int routeType, int mode) {
        showProgressDialog();
        mStartPoint = startTip.getPoint();
        mEndPoint = endTip.getPoint();

        if (mStartPoint == null) {
            Toast.makeText(this, "起点未设置", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mEndPoint == null) {
            Toast.makeText(this, "终点未设置", Toast.LENGTH_SHORT).show();
        }
        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                mStartPoint, mEndPoint);
        if (routeType == ROUTE_TYPE_BUS) {// 公交路径规划
            RouteSearch.BusRouteQuery query = new RouteSearch.BusRouteQuery(fromAndTo, mode,
                    Constants.DEFAULT_CITY, 0);// 第一个参数表示路径规划的起点和终点，第二个参数表示公交查询模式，第三个参数表示公交查询城市区号，第四个参数表示是否计算夜班车，0表示不计算
            routeSearch.calculateBusRouteAsyn(query);// 异步路径规划公交模式查询
        } else if (routeType == ROUTE_TYPE_DRIVE) {// 驾车路径规划
            RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, mode, null,
                    null, "");// 第一个参数表示路径规划的起点和终点，第二个参数表示驾车模式，第三个参数表示途经点，第四个参数表示避让区域，第五个参数表示避让道路
            routeSearch.calculateDriveRouteAsyn(query);// 异步路径规划驾车模式查询
        } else if (routeType == ROUTE_TYPE_WALK) {// 步行路径规划
            RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, mode);
            routeSearch.calculateWalkRouteAsyn(query);// 异步路径规划步行模式查询
        } else if (routeType == ROUTE_TYPE_CROSSTOWN) {
            RouteSearch.FromAndTo fromAndTo_bus = new RouteSearch.FromAndTo(
                    mStartPoint, mEndPoint);
            // 第一个参数表示路径规划的起点和终点，第二个参数表示公交查询模式，
            // 第三个参数表示公交查询城市区号，第四个参数表示是否计算夜班车，0表示不计算
            RouteSearch.BusRouteQuery query = new RouteSearch.BusRouteQuery(fromAndTo_bus, mode,
                    Constants.DEFAULT_CITY, 0);
            routeSearch.calculateBusRouteAsyn(query);// 异步路径规划公交模式查询
        }
    }


    public void dealWithResultTime() {
        Log.d(TAG, "dealWithResultTime: ");
        for(BusPath path : mBusRouteResult.getPaths()){
            for(BusStep step : path.getSteps()){

                //取轻轨数据
                if(step.getBusLines().size() > 0){
                    //起点站和终点站，
                    //但是如何区分公交和轻轨呢。这个SDK这里坑了

                    String name = step.getBusLines().get(0).getBusLineName();
                    String startStationName = step.getBusLines()
                            .get(0).getDepartureBusStation().getBusStationName();
                    String endStationName = step.getBusLines()
                            .get(0).getArrivalBusStation().getBusStationName();
                    Log.d(TAG, "dealWithResultTime: " + name);
                    if(busOrRailway(step)){
                        //现有数据里根本就没有专线相关
                        if(!name.contains("路")){
                            continue;
                        }

                        String[] nameAndStation = name.split("路");
                        String lineNo = nameAndStation[0];
                        String stations = nameAndStation[1];
                        stations = stations.substring(1, stations.length() - 1);
                        Log.d(TAG, "dealWithResultTime: stations = " + stations +
                                "   lineNo = " + lineNo);
                        String startDirection = stations.split("--")[0];
                        getBusDuration(lineNo, startStationName, endStationName,
                                TimeUtil.weekday(date), time, startDirection,
                                mBusRouteResult.getPaths().indexOf(path),
                                path.getSteps().indexOf(step));
                    } else {
                        getRailwayDuration(startStationName,
                                endStationName, time,
                                TimeUtil.weekday(date), mBusRouteResult.getPaths().indexOf(path),
                                path.getSteps().indexOf(step));
                    }


                }
            }
        }
    }


    public void getRailwayDuration(String startStation, String endStation,
                                   String startTime, int weekday,
                            final int pathIndex, final int stepIndex){

        IRequestAvgTime requestAvgTime = retrofit.create(IRequestAvgTime.class);
        Call<AvgTime> call = requestAvgTime.getRailWayCall(startStation,
                endStation, startTime, String.valueOf(weekday));
        
        call.enqueue(new Callback<AvgTime>() {
            @Override
            public void onResponse(Call<AvgTime> call, Response<AvgTime> response) {
                //只替换轨交的平均旅程时间,即请求之后有值的
                Log.d(TAG, "onResponse: From RailWay ");
                if(response.body().getDuration() != 0) {
                    Log.d(TAG, "onResponse: duration = " + response.body().getDuration());
                    //确保前后一致
                    mBusRouteResult.getPaths().get(pathIndex)
                            .getSteps().get(stepIndex)
                            .getBusLines().get(0)
                            .setDuration(response.body()
                                    .getDuration());

                    mBusRouteResult.getPaths().get(pathIndex).setDuration((long) caculateNewAllTime(pathIndex));
                }
                if(progressBar.getVisibility() == View.VISIBLE){
                    dissmissProgressDialog();
                }
//                Collections.sort(mBusRouteResult.getPaths(), RoutePlanActivity.this);
                mBusResultListAdapter.setDatas(mBusRouteResult);
            }

            @Override
            public void onFailure(Call<AvgTime> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });

    }

    public void getBusDuration(String lineNo, String startStation,
                               String endStation, int weekday,
                               String startTime, String startDirection,
                               final int pathIndex, final int stepIndex){
        IRequestAvgTime requestAvgTime = retrofit.create(IRequestAvgTime.class);
        Call<BusData> call = requestAvgTime.getBusCall(lineNo, startStation, endStation,
                weekday, startTime, startDirection);

        call.enqueue(new Callback<BusData>() {
            @Override
            public void onResponse(Call<BusData> call, Response<BusData> response) {
                Log.d(TAG, "onResponse: From Bus");
                if(response.body().getDuration() != 0){
                    Log.d(TAG, "onResponse: duration = " + response.body().getDuration());
                    mBusRouteResult.getPaths().get(pathIndex)
                            .getSteps().get(stepIndex)
                            .getBusLines().get(0)
                            .setDuration(response.body()
                                    .getDuration());

                    mBusRouteResult.getPaths().get(pathIndex).setDuration((long) caculateNewAllTime(pathIndex));
                }
                if(progressBar.getVisibility() == View.VISIBLE){
                    dissmissProgressDialog();
                }
                //异步会导致 index 不准，从而出问题
//                Collections.sort(mBusRouteResult.getPaths(), RoutePlanActivity.this);
                mBusResultListAdapter.setDatas(mBusRouteResult);
            }

            @Override
            public void onFailure(Call<BusData> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    /**
     * 显示进度框
     */
    private void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏进度框
     */
    private void dissmissProgressDialog() {
       progressBar.setVisibility(View.GONE);
    }


    /**
     *
     * @param busStep
     * @return true  ---- 公交
     *          false ---- 轨交
     */
    public boolean busOrRailway(BusStep busStep){
        String busName = busStep.getBusLines().get(0).getBusLineName();
        return !busName.contains("轨道");
    }

    public float caculateNewAllTime(int pathIndex){
        float all = 0;
        for(int i = 0;
            i < mBusRouteResult.getPaths().get(pathIndex).getSteps().size();
            i++){
            //步行
            RouteBusWalkItem item = mBusRouteResult.getPaths()
                    .get(pathIndex).getSteps().get(i).getWalk();
            if(item != null){
                all += item.getDuration();
            }
            //公交/轨交
            List list = mBusRouteResult.getPaths().get(pathIndex).getSteps().get(i).getBusLines();
            if(list != null && list.size() > 0){
                all += mBusRouteResult.getPaths().get(pathIndex)
                        .getSteps().get(i)
                        .getBusLines().get(0)
                        .getDuration();
            }
        }
        return all;
    }

    @Override
    public int compare(BusPath o1, BusPath o2) {
        if(o1.getDuration() > o2.getDuration()){
            return 1;
        }else if (o1.getDuration() == o2.getDuration()){
            return 0;
        } else {
            return -1;
        }
    }
}
