package cn.com.lightwightmvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import cn.com.lightwightmvvm.bean.BiYingResponse;
import cn.com.lightwightmvvm.repository.Repository;
import cn.com.mvvm.base.viewmodel.BaseViewModel;

public class ImageViewModel extends BaseViewModel {
    public MutableLiveData<BiYingResponse> biyingImage;
    public ObservableField<String> url = new ObservableField<>();
    public ImageViewModel(@NonNull Application application) {
        super(application);
    }

    public void getBiying(){
        biyingImage = new Repository().getBiYing();
    }

}
