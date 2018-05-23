package com.cqu.graduation.pyf.graduationprojectavgtraveltime.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.amap.api.services.help.Tip;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.R;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.model.Constants;

public class RoutePlanActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_CODE = 1;
    private static final String REQUEST_PARAM_START = "startPosition";
    private static final String REQUEST_PARAM_END = "endPosition";

    public static final int RESULT_CODE_START = 11;
    public static final int RESULT_CODE_END = 22;


    private TextView startStation;
    private TextView endStation;
    private TimePicker timePicker;
    private DatePicker datePicker;

    private Tip startTip;
    private Tip endTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_plan);

        startStation = findViewById(R.id.startPosition);
        endStation = findViewById(R.id.endPosition);

        startStation.setOnClickListener(this);
        endStation.setOnClickListener(this);

        timePicker = findViewById(R.id.timepicker);
        timePicker.setIs24HourView(true);
        datePicker = findViewById(R.id.datepicker);
        //设置年份为隐藏
        ((ViewGroup) ((ViewGroup)(datePicker.getChildAt(0)))
                .getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
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
}
