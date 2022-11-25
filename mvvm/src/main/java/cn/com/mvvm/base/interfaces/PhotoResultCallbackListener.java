package cn.com.mvvm.base.interfaces;

import java.util.List;

public interface PhotoResultCallbackListener<T> {
    public void onResult(List<T> result);
}
