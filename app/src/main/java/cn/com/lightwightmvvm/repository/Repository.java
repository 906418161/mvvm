package cn.com.lightwightmvvm.repository;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import cn.com.lightwightmvvm.api.ApiService;
import cn.com.lightwightmvvm.bean.BiYingResponse;
import cn.com.mvvm.base.network.BaseObserver;
import cn.com.mvvm.base.network.NetworkApi;
import cn.com.mvvm.base.network.utils.KLog;

public class Repository {
    @SuppressLint("CheckResult")
    public MutableLiveData<BiYingResponse> getBiYing() {
        System.out.println("调用请求");
        final MutableLiveData<BiYingResponse> biyingImage = new MutableLiveData<>();
        ApiService apiService = NetworkApi.createService(ApiService.class);
        apiService.biying().compose(NetworkApi.applySchedulers(new BaseObserver<BiYingResponse>() {
            @Override
            public void onSuccess(BiYingResponse biYingImgResponse) {
                KLog.d(new Gson().toJson(biYingImgResponse));
                biyingImage.setValue(biYingImgResponse);
            }

            @Override
            public void onFailure(Throwable e) {
                KLog.e("BiYing Error: " + e.toString());
            }
        }));
        return biyingImage;
    }
}
