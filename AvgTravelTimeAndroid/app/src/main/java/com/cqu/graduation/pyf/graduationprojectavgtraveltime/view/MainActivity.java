package com.cqu.graduation.pyf.graduationprojectavgtraveltime.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cqu.graduation.pyf.graduationprojectavgtraveltime.R;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.databinding.ActivityMainBinding;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.bean.UserBean;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);
        UserBean userBean = new UserBean("PYF", 22);
        binding.setUser(userBean);
    }
}
