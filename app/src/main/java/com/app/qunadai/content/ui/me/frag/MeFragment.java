package com.app.qunadai.content.ui.me.frag;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.utils.ImgUtil;

import butterknife.BindView;

/**
 * Created by wayne on 2017/5/10.
 */

public class MeFragment extends BaseFragment {
    @BindView(R.id.iv_me_avatar)
    ImageView iv_me_avatar;

    public static MeFragment getInstance() {
        MeFragment meFragment = new MeFragment();
        Bundle bundle = new Bundle();
        meFragment.setArguments(bundle);
        return meFragment;
    }


    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_me,null);
        return root;
    }


    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        ImgUtil.loadRound(getActivity(),R.mipmap.default_avatar,iv_me_avatar);
    }
}
