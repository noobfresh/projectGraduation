package com.cqu.graduation.pyf.graduationprojectavgtraveltime.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.R;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.bean.AvgTime;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.model.IRequestAvgTime;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.util.AMapUtil;
import com.cqu.graduation.pyf.graduationprojectavgtraveltime.view.BusRouteDetailActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BusResultListAdapter extends BaseAdapter {
	private Context mContext;
	private List<BusPath> mBusPathList;
	private BusRouteResult mBusRouteResult;

	private String date;
	private String time;



	private static final String httpurl = "127.0.0.1/index";
	private static final String TAG = "BusResultListAdapter";

	public BusResultListAdapter(Context context, BusRouteResult busrouteresult,
								String date, String time) {
		mContext = context;
		mBusRouteResult = busrouteresult;
		mBusPathList = busrouteresult.getPaths();
		this.date = date;
		this.time = time;


	}
	
	@Override
	public int getCount() {
		return mBusPathList.size();
	}

	@Override
	public Object getItem(int position) {
		return mBusPathList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(mContext, R.layout.item_bus_result, null);
			holder.title = convertView.findViewById(R.id.bus_path_title);
			holder.des = convertView.findViewById(R.id.bus_path_des);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		final BusPath item = mBusPathList.get(position);
		//

//		item.setDuration(1);
		holder.title.setText(AMapUtil.getBusPathTitle(item));
		holder.des.setText(AMapUtil.getBusPathDes(item));
		
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext.getApplicationContext(),
						BusRouteDetailActivity.class);
				intent.putExtra("bus_path", item);
				intent.putExtra("bus_result", mBusRouteResult);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				mContext.startActivity(intent);
				
			}
		});
		
		return convertView;
	}
	
	private class ViewHolder {
		TextView title;
		TextView des;
	}

	private long duration = 0L;

	public void setDatas(BusRouteResult busRouteResult){
		Log.d(TAG, "setDatas: ");
		this.mBusRouteResult = busRouteResult;
		this.mBusPathList = busRouteResult.getPaths();
		notifyDataSetChanged();
	}

}
