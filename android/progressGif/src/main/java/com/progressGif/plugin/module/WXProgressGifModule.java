package com.progressGif.plugin.module;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.farwolf.view.FreeDialog;
import com.farwolf.weex.annotation.WeexModule;
import com.farwolf.weex.base.WXModuleBase;
import com.farwolf.weex.util.Const;
import com.farwolf.weex.util.Weex;
import com.farwolf.weex.view.LoadingDialog;
import com.farwolf.weex.view.LoadingDialog_;
import com.progressGif.plugin.R;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.utils.WXViewUtils;

import java.io.File;
import java.net.URI;
import java.util.HashMap;

@WeexModule(name = "progressGif")
public class WXProgressGifModule extends WXModuleBase {


//    protected DialogProgress progress;

    Dialog f;

    private static TextView tipTextView;

    @JSMethod
    public void show(HashMap map) {
        boolean isClickBackgroundClose = true;
        showFull("加载中", map);
    }

    @JSMethod
    public void showFull(String txt, HashMap map) {
        boolean cancle = true;
        float width = 288;
        float height = 288;
        String url = "";
        if (map.containsKey("isClickClose")) {
            cancle = (boolean) map.get("isClickClose");
        }
        if (map.containsKey("width")) {
//            WXViewUtils.getWeexPxByReal()
//            WXViewUtils.getRealPxByWidth()
            width = WXViewUtils.getRealPxByWidth(Float.parseFloat(map.get("width") + ""));
        }
        if (map.containsKey("height")) {
            height = WXViewUtils.getRealPxByWidth(Float.parseFloat(map.get("height") + ""));
        }
        if (map.containsKey("url")) {
            url = (String) map.get("url");

            if (url.startsWith("http")) {//网络地址要先下载
            } else {
                url = getFilePath(url, this.mWXSDKInstance);
                File file = new File(url);
            }
        }
        if (f == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View v = inflater.inflate(R.layout.loading, null);// 得到加载view

            ImageView iv = v.findViewById(R.id.loaddingImageView);
//            iv.setMaxWidth((int) width);
//            iv.setMaxHeight((int) height);

            RequestOptions options = new RequestOptions()
                    .override((int) width, (int) height)
                    .centerCrop();

            tipTextView = v.findViewById(R.id.tv_toast_content);// 提示文字

            if (url.startsWith("http")) {//网络地址要先下载
                Glide.with(this.mWXSDKInstance.getContext())
                        .load(Uri.parse(url))
                        .apply(options)
                        .into(iv);
            } else {
                Glide.with(this.mWXSDKInstance.getContext())
                        .load(new File(url))
                        .apply(options)
                        .into(iv);
            }
            f = new Dialog(getContext(), R.style.MyDialogStyle);// 创建自定义样式dialog

            f.setContentView(v, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
            Window window = f.getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
//            lp.width = 288;
//            lp.height = 288;
//            lp.y = -144;
            window.setGravity(Gravity.CENTER_HORIZONTAL);
            window.setAttributes(lp);
            window.setWindowAnimations(R.style.PopWindowAnimStyle);
        }
        if (map.get("msg") == null || map.get("msg").toString() == null || map.get("msg").toString().length() == 0) {
            tipTextView.setVisibility(View.GONE);
        } else {
            tipTextView.setText(map.get("msg").toString());// 设置加载信息
            tipTextView.setVisibility(View.VISIBLE);
        }

        f.setCancelable(cancle);
        f.setCanceledOnTouchOutside(cancle);
        f.show();
    }


    @JSMethod
    public boolean isShowing() {
        return f != null && f.isShowing();
    }


    @JSMethod
    public void dismiss() {
        if (f == null || !f.isShowing())
            return;
        Activity a = (Activity) this.mWXSDKInstance.getContext();
        if (a == null || a.isFinishing()) {
            return;
        }
        f.dismiss();
    }


    @Override
    public void onActivityDestroy() {
        super.onActivityDestroy();
        if (f != null && f.isShowing()) {
            f.dismiss();
        }
    }

    private String getFilePath(String filePath, WXSDKInstance ctx) {
        if (filePath != null && filePath.startsWith(Const.PREFIX_SDCARD)) {
            filePath = filePath.replace(Const.PREFIX_SDCARD, "");
        }
        filePath = Weex.getRootPath(filePath, ctx);
        return filePath;
    }
}
