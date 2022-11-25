package cn.com.lightwightmvvm.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import cn.com.lightwightmvvm.R;
import cn.com.lightwightmvvm.databinding.FragmentImageBinding;
import cn.com.lightwightmvvm.viewmodel.ImageViewModel;
import cn.com.mvvm.base.fragment.BaseFragment;
import cn.com.mvvm.base.util.GlideUtils;

public class ImageFragment extends BaseFragment {

    public static ImageFragment newInstance(int type){
        ImageFragment imageFragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        imageFragment.setArguments(bundle);
        return imageFragment;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_image;
    }

    ImageViewModel viewModel;
    FragmentImageBinding binding;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
//        ImageView img = contentView.findViewById(R.id.image);
        binding = (FragmentImageBinding) getBinding();
        viewModel = (ImageViewModel) getViewModel();
        binding.setViewmodel(viewModel);
        viewModel.getBiying();
        viewModel.biyingImage.observe(this,biYingResponse -> {
            viewModel.url.set("http://cn.bing.com"+viewModel.biyingImage.getValue().getImages().get(0).getUrl());
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected Class bindViewModel() {
        return ImageViewModel.class;
    }
}
