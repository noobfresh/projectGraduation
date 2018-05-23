package com.cqu.graduation.pyf.graduationprojectavgtraveltime.util;

/**
 * Created by PYF on 2018/5/22.
 */

public class Useless {

    public void useless(){
        //
        /*PoiSearch.Query query = new PoiSearch.Query("大学城", "", "023");
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
                            .title(item.getTitle()).snippet(item.getTypeDes()));
                }
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {
                Log.d(TAG, "onPoiItemSearched: ");
            }
        });

        search.searchPOIAsyn();*/



        /*timePicker = findViewById(R.id.timepicker);
        timePicker.setIs24HourView(true);
        setNumberPickerTextSize(timePicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                time = TimeUtil.formatTime(i,i1);
            }
        });

        datePicker = findViewById(R.id.datepicker);
        //设置年份为隐藏
        ((ViewGroup) ((ViewGroup)(datePicker.getChildAt(0)))
                .getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
        Calendar calendar = Calendar.getInstance();
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                        date = TimeUtil.formatDate(i1+1, i2);
                    }
                });*/
    }
}
