package com.cqu.graduation.pyf.graduationprojectavgtraveltime.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.R;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.databinding.ActivityMainBinding;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.bean.UserBean;

public class MainActivity extends AppCompatActivity implements INaviInfoCallback{

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);
        UserBean userBean = new UserBean("PYF", 22);
        binding.setUser(userBean);
        AmapNaviPage.getInstance().showRouteActivity(this, new AmapNaviParams(null),
                this);
    }

    @Override
    public void onInitNaviFailure() {
        Log.d(TAG, "onInitNaviFailure: ");
    }

    @Override
    public void onGetNavigationText(String s) {
        Log.d(TAG, "onGetNavigationText: ");
    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {
        Log.d(TAG, "onLocationChange: ");
    }

    @Override
    public void onArriveDestination(boolean b) {
        Log.d(TAG, "onArriveDestination: ");
    }

    @Override
    public void onStartNavi(int i) {
        Log.d(TAG, "onStartNavi: ");
    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {
        Log.d(TAG, "onCalculateRouteSuccess: ");
    }

    @Override
    public void onCalculateRouteFailure(int i) {
        Log.d(TAG, "onCalculateRouteFailure: ");
    }

    @Override
    public void onStopSpeaking() {
        Log.d(TAG, "onStopSpeaking: ");
    }

    @Override
    public void onReCalculateRoute(int i) {
        Log.d(TAG, "onReCalculateRoute: ");
    }

    @Override
    public void onExitPage(int i) {
        Log.d(TAG, "onExitPage: ");
    }

    @Override
    public void onStrategyChanged(int i) {
        Log.d(TAG, "onStrategyChanged: ");
    }

    @Override
    public View getCustomNaviBottomView() {
        Log.d(TAG, "getCustomNaviBottomView: ");
        return null;
    }

    @Override
    public View getCustomNaviView() {
        Log.d(TAG, "getCustomNaviView: ");
        return null;
    }

    @Override
    public void onArrivedWayPoint(int i) {
        Log.d(TAG, "onArrivedWayPoint: ");
    }
}
