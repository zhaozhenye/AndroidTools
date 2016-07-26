package com.gjson.autoscrollview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.gjson.autoscrollview.R;
import com.gjson.autoscrollview.commonview.VerticalAutoScrollView;
import com.gjson.autoscrollview.entity.AdInfo;
import com.gjson.autoscrollview.utils.ToastManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private VerticalAutoScrollView mAutoScrollView;


    // 退出时间
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mAutoScrollView = getView(R.id.top_autoscrllo);
        mAutoScrollView.setData(getData());
        mAutoScrollView.setClickListener(new VerticalAutoScrollView.OnItemClickListener<AdInfo>() {
            @Override
            public void onItemClick(View v, AdInfo adInfo) {

                startActivity(new Intent(mContext, CouponActivity.class));
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /*获取数据*/
    private List<AdInfo> getData() {
        List<AdInfo> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            AdInfo item;
            if (i % 2 == 0) {
                item = new AdInfo("this is WELCOME I= ", "s");
            } else {
                item = new AdInfo("电视专场 客户一张优惠券点击查看详情 ", "s");
            }

            datas.add(item);
        }
        return datas;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastManager.showToast(mContext, "再按一次退出程序",1);
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
