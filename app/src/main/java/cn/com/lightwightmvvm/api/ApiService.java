package cn.com.lightwightmvvm.api;

import cn.com.lightwightmvvm.bean.BiYingResponse;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

/**
 * 所有的Api网络接口
 */
public interface ApiService {
    /**
     * 必应每日一图
     */
    @GET("/HPImageArchive.aspx?format=js&idx=0&n=1")
    Observable<BiYingResponse> biying();
}
