package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.LatLng;
import com.bw.movie.R;
import com.bw.movie.activity.MoreActivity;
import com.bw.movie.activity.SearchActivity;
import com.bw.movie.adapter.ComingSoonAdapter;
import com.bw.movie.adapter.HotAdapter;
import com.bw.movie.adapter.ReeAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.ComingSoonBean;
import com.bw.movie.bean.ComingSoonResultBean;
import com.bw.movie.bean.HotBean;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.contract.HomeContract;
import com.bw.movie.dao.ComingSoonResultBeanDao;
import com.bw.movie.dao.DaoMaster;
import com.bw.movie.dao.DaoSession;
import com.bw.movie.presenter.HomePresenter;
import com.bw.movie.utils.RetUtils;
import com.stx.xhb.xbanner.XBanner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

//import com.bawei.wutenglongapp.R;


public class HomeFragment extends BaseFragment implements HomeContract.IView, LocationSource, AMapLocationListener {

    @BindView(R.id.tv_size)
    TextView tvSize;
    @BindView(R.id.rv1)
    RecyclerView rv1;
    @BindView(R.id.rv2)
    RecyclerView rv2;
    @BindView(R.id.rv3)
    RecyclerView rv3;
    @BindView(R.id.iv_wei)
    TextView ivWei;
    Unbinder unbinder;
    @BindView(R.id.tv_gengduo1)
    TextView tvGengduo1;
    @BindView(R.id.tv_gengduo2)
    TextView tvGengduo2;
    @BindView(R.id.tv_gengduo3)
    TextView tvGengduo3;
    Unbinder unbinder1;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    Unbinder unbinder2;
    private XBanner xb;
    ArrayList<Integer> integers = new ArrayList<>();


    //AMap是地图对象
    private AMap aMap;
    private MapView mapView;
    //声明AMapLocationClient类对象，定位发起端
    private AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象，定位参数
    public AMapLocationClientOption mLocationOption = null;
    //声明mListener对象，定位监听器
    private OnLocationChangedListener mListener = null;
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    private String city1;

    private ComingSoonResultBeanDao comingSoonResultBeanDao;

    @Override
    protected BasePresenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initView(View view) {
        xb = view.findViewById(R.id.xb);

        mapView = (MapView) view.findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        if (aMap == null) {
            aMap = mapView.getMap();
            //设置显示定位按钮 并且可以点击
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource(this);//设置了定位的监听
            // 是否显示定位按钮
            settings.setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
        }
        location();

    }

    @Override
    protected void initData() {

        DaoSession name = DaoMaster.newDevSession(getContext(), "name");
        comingSoonResultBeanDao = name.getComingSoonResultBeanDao();

        if (city1 != null) {
            ivWei.setText(city1 + "");
        }
        integers.clear();
        integers.add(R.mipmap.banner_1);
        integers.add(R.mipmap.banner_2);
        integers.add(R.mipmap.banner_3);
        integers.add(R.mipmap.banner_4);
        int size = integers.size() - 1;
        tvSize.setText(size + "/4");
        xb.setData(integers, null);
        xb.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                ImageView iv = (ImageView) view;
                iv.setImageResource(integers.get(position));
            }
        });

        if (RetUtils.getInstance().isNetWork(getContext())){
            BasePresenter presenter = getPresenter();
            if (presenter instanceof HomePresenter) {
                ((HomePresenter) presenter).getHot(1, 4);
                ((HomePresenter) presenter).getComingSoon(1, 3);
                ((HomePresenter) presenter).getRelease(1, 4);
            }
        }else {

            List<ComingSoonResultBean> list = comingSoonResultBeanDao.queryBuilder().list();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            rv2.setLayoutManager(linearLayoutManager);
            ComingSoonAdapter comingSoonAdapter = new ComingSoonAdapter(getContext(), list);
            rv2.setAdapter(comingSoonAdapter);
        }



    }


    @Override
    public void onHotSuccess(HotBean hotBean) {

        Log.i("xxx", hotBean.getMessage());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv1.setLayoutManager(linearLayoutManager);

        List<HotBean.ResultBean> result = hotBean.getResult();
        ReeAdapter releaseAdapter = new ReeAdapter(getContext(), result);
        rv1.setAdapter(releaseAdapter);



    }

    @Override
    public void onComingSoonSuccess(ComingSoonBean comingSoonBean) {

//        List<ComingSoonBean.ResultBean> result = comingSoonBean.getResult();
        List<ComingSoonResultBean> result = comingSoonBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv2.setLayoutManager(linearLayoutManager);
        ComingSoonAdapter comingSoonAdapter = new ComingSoonAdapter(getContext(), result);
        rv2.setAdapter(comingSoonAdapter);

        for (int i=0;i<result.size();i++){
            comingSoonResultBeanDao.insertOrReplace(result.get(i));
        }


    }

    @Override
    public void onReleaseSuccess(ReleaseBean releaseBean) {

        List<ReleaseBean.ResultBean> result = releaseBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
        rv3.setLayoutManager(linearLayoutManager);
        HotAdapter hotAdapter = new HotAdapter(getContext(), result);
        rv3.setAdapter(hotAdapter);

    }

    @Override
    public void onHotFailure(String str) {

        Log.i("hot", str);
    }

    @Override
    public void onComingSoonFailure(String str) {
        Log.i("Com", str);

    }

    @Override
    public void onReleaseFailure(String str) {
        Log.i("onReleaseFailure", str);

    }

    private void location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);
                    //添加图钉
                    //  aMap.addMarker(getMarkerOptions(amapLocation));
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());
                    //Toast.makeText(getContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                    city1 = aMapLocation.getCity();

                    //Toast.makeText(getContext(), ""+city1+"11", Toast.LENGTH_SHORT).show();
                    ivWei.setText(city1 + "");
                    isFirstLoc = false;
                }


            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                Toast.makeText(getContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;
    }


    @OnClick({R.id.tv_gengduo1, R.id.tv_gengduo2, R.id.tv_gengduo3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_gengduo1:
                Intent intent = new Intent(getContext(), MoreActivity.class);

                startActivity(intent);
                break;
            case R.id.tv_gengduo2:
                Intent intent1 = new Intent(getContext(), MoreActivity.class);

                startActivity(intent1);
                break;
            case R.id.tv_gengduo3:
                Intent intent2 = new Intent(getContext(), MoreActivity.class);

                startActivity(intent2);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder2 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder2.unbind();
    }

    @OnClick(R.id.iv_search)
    public void onViewClicked() {


        Intent intent = new Intent(getContext(), SearchActivity.class);
        startActivity(intent);

    }
}
