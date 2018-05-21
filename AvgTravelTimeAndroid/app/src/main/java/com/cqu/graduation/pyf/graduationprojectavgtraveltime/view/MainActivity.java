package com.cqu.graduation.pyf.graduationprojectavgtraveltime.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.R;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.databinding.ActivityMainBinding;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.bean.UserBean;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);
        UserBean userBean = new UserBean("PYF", 22);
        binding.setUser(userBean);

        //引入高德
        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        final AMap aMap = mapView.getMap();
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15f));
        //显示定位
        MyLocationStyle myLocationStyle;
        //初始化定位蓝点样式类
        myLocationStyle = new MyLocationStyle();
        
        //连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。
        // （1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        myLocationStyle.showMyLocation(true);
        //设置连续定位模式下的定位间隔，
        // 只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(10000);
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMyLocationEnabled(true);

        //
        PoiSearch.Query query = new PoiSearch.Query("大学城", "", "023");
        query.setPageSize(10);
        query.setPageNum(1);

        PoiSearch search = new PoiSearch(this, query);
        search.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult poiResult, int i) {
                Log.d(TAG, "onPoiSearched: " + i);
                List<PoiItem> list = poiResult.getPois();

                for(PoiItem item : list){

                    aMap.addMarker(new MarkerOptions()
                            .position(new LatLng(item.getLatLonPoint().getLatitude(),
                                    item.getLatLonPoint().getLongitude()))
                            .title(item.getAdName()).snippet("DefaultMarker"));
                }
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {
                Log.d(TAG, "onPoiItemSearched: ");
            }
        });

        search.searchPOIAsyn();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

}
