package com.progressGif.plugin.component;

import android.content.Context;
import androidx.annotation.NonNull;

import com.progressGif.plugin.view.ProgressGifView;
import com.farwolf.weex.annotation.WeexComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;

//此progress演示了如何制作一个界面组件
//可以改名后直接在这上面改，也可以删除了自己写
//name:在vue中标签的名字
//此注解将自动注册组件到weex
@WeexComponent(name="progressGif")
public class WXProgressGifComponent extends WXComponent<ProgressGifView> {
    public WXProgressGifComponent(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
    }

    //**1.在这个方法里返回目标view
    @Override
    protected ProgressGifView initComponentHostView(@NonNull Context context) {
        return new ProgressGifView(context);
    }

    //2.此函数用来初始化
    @Override
    protected void onHostViewInitialized(ProgressGifView host) {
        super.onHostViewInitialized(host);

    }


    //**组件的方法只支持异步，不支持同步，请用callback接收值
    @JSMethod
    public void getData(JSCallback callback){

    }

   //**android 是通过注解定义属性的
    @WXComponentProp(name = "color")
    public void setColor(String color){
        //setSeekBarColor(getHostView(), Color.parseColor(color));

    }

}
