package com.derby.football.ui.fragment;


import android.widget.LinearLayout;
import android.widget.TextView;

import com.derby.football.R;
import com.derby.football.base.BaseFragment;
import com.derby.football.utils.UIHelper;

import butterknife.Bind;
import butterknife.OnClick;

public class MessageFragment extends BaseFragment {


    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }


}
