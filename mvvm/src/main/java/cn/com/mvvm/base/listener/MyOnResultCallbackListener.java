package cn.com.mvvm.base.listener;

import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;

import java.util.ArrayList;
import java.util.List;

import cn.com.mvvm.base.interfaces.PhotoResultCallbackListener;
import cn.com.mvvm.base.model.PhotoBean;
import cn.com.mvvm.base.model.VideoBean;
import cn.com.mvvm.base.util.ListUtils;
import cn.com.mvvm.base.util.StringUtils;

public class MyOnResultCallbackListener implements OnResultCallbackListener<LocalMedia> {
    PhotoResultCallbackListener listener;
    private List<String> result;
    private List<Object> objects;
    private Class aClass;
    public MyOnResultCallbackListener(PhotoResultCallbackListener listener){
        this.listener = listener;
    }

    public MyOnResultCallbackListener(PhotoResultCallbackListener listener,Class aClass){
        this.listener = listener;
        this.aClass = aClass;

    }

    @Override
    public void onResult(ArrayList<LocalMedia> localMedias) {
        if (aClass!=null&&aClass.getName().equals(PhotoBean.class.getName())){
            objects = new ArrayList<>();
            if (!ListUtils.isEmpty(localMedias)){
                String url = "";
                for (LocalMedia localMedia:localMedias){
                    if (!StringUtils.isEmpty(localMedia.getCompressPath()))url = localMedia.getCompressPath();//鍘嬬缉璺緞
                    else if (!StringUtils.isEmpty(localMedia.getRealPath()))url = localMedia.getRealPath();//缁濆璺緞
                    else if (!StringUtils.isEmpty(localMedia.getAvailablePath()))url = localMedia.getAvailablePath();
                    else if (!StringUtils.isEmpty(localMedia.getPath()))url = localMedia.getPath();
                    objects.add(new PhotoBean(url,localMedia.getFileName(),localMedia.getWidth(),localMedia.getHeight()));
                }
                if (listener!=null)listener.onResult(objects);
            }
        }else if (aClass!=null&&aClass.getName().equals(VideoBean.class.getName())){
            objects = new ArrayList<>();
            if (!ListUtils.isEmpty(localMedias)){
                String url = "";
                for (LocalMedia localMedia:localMedias){
                    if (!StringUtils.isEmpty(localMedia.getCompressPath()))url = localMedia.getCompressPath();//鍘嬬缉璺緞
                    else if (!StringUtils.isEmpty(localMedia.getRealPath()))url = localMedia.getRealPath();//缁濆璺緞
                    else if (!StringUtils.isEmpty(localMedia.getAvailablePath()))url = localMedia.getAvailablePath();
                    else if (!StringUtils.isEmpty(localMedia.getPath()))url = localMedia.getPath();
                    objects.add(new VideoBean(localMedia.getFileName(),url,localMedia.getDuration()));
                }
                if (listener!=null)listener.onResult(objects);
            }
        }else {
            result = new ArrayList<>();
            if (!ListUtils.isEmpty(localMedias)){
                for (LocalMedia localMedia:localMedias){
                    if (!StringUtils.isEmpty(localMedia.getCompressPath()))result.add(localMedia.getCompressPath());//鍘嬬缉璺緞
                    else if (!StringUtils.isEmpty(localMedia.getRealPath()))result.add(localMedia.getRealPath());//缁濆璺緞
                    else if (!StringUtils.isEmpty(localMedia.getAvailablePath()))result.add(localMedia.getAvailablePath());
                    else if (!StringUtils.isEmpty(localMedia.getPath()))result.add(localMedia.getPath());
                }
            }
            if (listener!=null)listener.onResult(result);
        }

    }

    @Override
    public void onCancel() {

    }
}
