package com.cqu.graduation.pyf.graduationprojectavgtraveltime.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.R;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.adapter.InputTipsAdapter;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.model.Constants;

import java.util.ArrayList;
import java.util.List;

public class InputTipsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,
        Inputtips.InputtipsListener, AdapterView.OnItemClickListener, View.OnClickListener{

    private static final String TAG = "InputTipsActivity";

    private String position;

    private SearchView mSearchView;// 输入搜索关键字
    private ImageView mBack;
    private ListView mInputListView;
    private List<Tip> mCurrentTipList;
    private InputTipsAdapter mIntipAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_tips);
        Intent intent = getIntent();
        if(intent != null && !IsEmptyOrNullString(String.valueOf
                (intent.getCharSequenceExtra("position")))){
            position =  String.valueOf(intent.getCharSequenceExtra("position"));
        }

        initSearchView();
        mInputListView = (ListView) findViewById(R.id.inputtip_list);
        mInputListView.setOnItemClickListener(this);
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
    }


    private void initSearchView() {
        mSearchView = (SearchView) findViewById(R.id.keyWord);
        mSearchView.setOnQueryTextListener(this);
        //设置SearchView默认为展开显示
        mSearchView.setIconified(false);
        mSearchView.onActionViewExpanded();
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setSubmitButtonEnabled(false);

        EditText editText = mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        editText.setTextColor(Color.rgb(0, 0, 0));
        editText.setHintTextColor(Color.rgb(235, 235, 235));
    }

    /**
     * 输入提示回调
     *
     * @param tipList
     * @param rCode
     */
    @Override
    public void onGetInputtips(List<Tip> tipList, int rCode) {
        //数据源与view的绑定

        //magic num
        if (rCode == 1000) {// 正确返回
            mCurrentTipList = tipList;
            List<String> listString = new ArrayList<String>();
            for (int i = 0; i < tipList.size(); i++) {
                listString.add(tipList.get(i).getName());
            }
            mIntipAdapter = new InputTipsAdapter(
                    getApplicationContext(),
                    mCurrentTipList);
            mInputListView.setAdapter(mIntipAdapter);
            mIntipAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, String.valueOf(rCode), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //选择某个搜索结果时，保存信息，传递到main
        Tip tip = (Tip) adapterView.getItemAtPosition(i);
        Intent intent = new Intent();
        if (mCurrentTipList != null && !IsEmptyOrNullString(position)) {
            intent.putExtra(Constants.ROUTE_EXTRA_TIP, tip);
            setResult(position.equals("endPosition") ?
                    RoutePlanActivity.RESULT_CODE_END : RoutePlanActivity.RESULT_CODE_START, intent);
        } else {
            intent.putExtra(Constants.EXTRA_TIP, tip);
            setResult(MainActivity.RESULT_CODE_INPUTTIPS, intent);
        }
        this.finish();
    }

    /**
     * 按下确认键触发，本例为键盘回车或搜索键
     *
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_WORDS_NAME, query);
        setResult(MainActivity.RESULT_CODE_KEYWORDS, intent);
        this.finish();
        return false;
    }

    /**
     * 输入字符变化时触发
     *
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        //数据源请求逻辑
        //输入关键字非空时
        if (!IsEmptyOrNullString(newText)) {
            InputtipsQuery inputquery = new InputtipsQuery(newText, Constants.DEFAULT_CITY);
            Inputtips inputTips = new Inputtips(InputTipsActivity.this.getApplicationContext(), inputquery);
            inputTips.setInputtipsListener(this);
            inputTips.requestInputtipsAsyn();
            //返回后会回调
        } else {
            if (mIntipAdapter != null && mCurrentTipList != null) {
                mCurrentTipList.clear();
                mIntipAdapter.notifyDataSetChanged();
            }
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            this.finish();
        }
    }

    public static boolean IsEmptyOrNullString(String s) {
        return (s == null) || (s.trim().length() == 0) || s.equals("null");
    }
}
