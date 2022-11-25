package cn.com.mvvm.base.util.ImageEngine;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.luck.picture.lib.photoview.PhotoView;
import com.lxj.xpopup.util.ImageDownloadTarget;
import com.lxj.xpopup.util.SSIVListener;
import com.lxj.xpopup.util.XPopupUtils;

import java.io.File;



public class MySmartGlideImageLoader {
    private int errImg;
    private boolean mBigImage;

    public MySmartGlideImageLoader() { }

    public MySmartGlideImageLoader(int errImgRes) {
        errImg = errImgRes;
    }
    public void loadSnapshot(@NonNull Object uri, @NonNull PhotoView snapshot) {
        if(mBigImage){
            Glide.with(snapshot).downloadOnly().load(uri)
                    .into(new ImageDownloadTarget() {
                        @Override
                        public void onLoadFailed(Drawable errorDrawable) {
                            super.onLoadFailed(errorDrawable);
                        }

                        @Override
                        public void onResourceReady(@NonNull File resource, Transition<? super File> transition) {
                            super.onResourceReady(resource, transition);
                            int degree = XPopupUtils.getRotateDegree(resource.getAbsolutePath());
                            int maxW = XPopupUtils.getAppWidth(snapshot.getContext());
                            int maxH = XPopupUtils.getScreenHeight(snapshot.getContext());
                            int[] size = XPopupUtils.getImageSize(resource);
                            if (size[0] > maxW || size[1] > maxH) {
                                //缩放加载
                                Bitmap rawBmp = XPopupUtils.getBitmap(resource, maxW, maxH);
                                snapshot.setImageBitmap(XPopupUtils.rotate(rawBmp, degree, size[0]/2f, size[1]/2f));
                            } else {
                                Glide.with(snapshot).load(resource).apply(new RequestOptions().override(size[0], size[1])).into(snapshot);
                            }
                        }
                    });
        }else {
            Glide.with(snapshot).load(uri).override(Target.SIZE_ORIGINAL).into(snapshot);
        }
    }

    public View loadImage(int position, @NonNull Object uri, Context context,@NonNull ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        final View imageView = mBigImage ? buildBigImageView(context, progressBar, position)
                : buildPhotoView(context,  position);

        Glide.with(imageView).downloadOnly().load(uri)
                .into(new ImageDownloadTarget() {
                    @Override
                    public void onLoadFailed(Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        progressBar.setVisibility(View.GONE);
                        if (imageView instanceof PhotoView) {
                            ((PhotoView) imageView).setImageResource(errImg);
                            ((PhotoView) imageView).setZoomable(false);
                        } else {
                            ((SubsamplingScaleImageView) imageView).setImage(ImageSource.resource(errImg));
                        }
                    }

                    @Override
                    public void onResourceReady(@NonNull File resource, Transition<? super File> transition) {
                        super.onResourceReady(resource, transition);
                        int maxW = XPopupUtils.getAppWidth(context) * 2;
                        int maxH = XPopupUtils.getScreenHeight(context) * 2;

                        int[] size = XPopupUtils.getImageSize(resource);
                        int degree = XPopupUtils.getRotateDegree(resource.getAbsolutePath());
                        //photo view加载
                        if (imageView instanceof PhotoView) {
                            progressBar.setVisibility(View.GONE);
                            ((PhotoView) imageView).setZoomable(true);
                            Log.e("tag", "degree: "+ degree);
                            if (size[0] > maxW || size[1] > maxH) {
                                //TODO: 可能导致大图GIF展示不出来
                                Bitmap rawBmp = XPopupUtils.getBitmap(resource, maxW, maxH);
                                ((PhotoView) imageView).setImageBitmap(XPopupUtils.rotate(rawBmp, degree, size[0]/2f, size[1]/2f));
                            } else {

                                Glide.with(imageView).load(resource)
                                        .apply(new RequestOptions().error(errImg).override(size[0], size[1])).into(((PhotoView) imageView));
                            }
                        } else {
                            //大图加载
                            SubsamplingScaleImageView bigImageView = (SubsamplingScaleImageView) imageView;
                            boolean longImage = false;
                            if (size[1] * 1f / size[0] > XPopupUtils.getScreenHeight(context) * 1f / XPopupUtils.getAppWidth(context)) {
                                longImage = true;
                                bigImageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_START);
                            } else {
                                longImage = false;
                                bigImageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_INSIDE);
                            }
                            bigImageView.setOrientation(degree);
                            bigImageView.setOnImageEventListener(new SSIVListener(bigImageView, progressBar, errImg, longImage));
                            Bitmap preview = XPopupUtils.getBitmap(resource, XPopupUtils.getAppWidth(context), XPopupUtils.getScreenHeight(context));
                            bigImageView.setImage(ImageSource.uri(Uri.fromFile(resource)).dimensions(size[0], size[1]),
                                    ImageSource.cachedBitmap(preview));
                        }
                    }
                });
        return imageView;
    }

    public File getImageFile(@NonNull Context context, @NonNull Object uri) {
        try {
            return Glide.with(context).downloadOnly().load(uri).submit().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private SubsamplingScaleImageView buildBigImageView(Context context,ProgressBar progressBar, final int realPosition) {
        final SubsamplingScaleImageView ssiv = new SubsamplingScaleImageView(context);
//        ssiv.setOrientation(SubsamplingScaleImageView.ORIENTATION_USE_EXIF);
        ssiv.setOnStateChangedListener(new SubsamplingScaleImageView.DefaultOnStateChangedListener() {
            @Override
            public void onCenterChanged(PointF newCenter, int origin) {
                super.onCenterChanged(newCenter, origin);
                //TODO 同步SubsamplingScaleImageView的滚动给snapshot
//                    Log.e("tag", "y: " + newCenter.y   + " vh: "+ ssiv.getMeasuredHeight()
//                    + "  dy: "+ (newCenter.y - ssiv.getMeasuredHeight()/2));
            }
        });
        ssiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ssiv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });
        return ssiv;
    }

    private PhotoView buildPhotoView(Context context, final int realPosition) {
        final PhotoView photoView = new PhotoView(context);
        photoView.setZoomable(false);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)context).finishAfterTransition();
            }
        });
        photoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });
        return photoView;
    }
}
