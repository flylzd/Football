package com.derby.football.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.derby.football.R;
import com.derby.football.base.BaseActivity;
import com.derby.football.ui.fragment.FindFragment;
import com.derby.football.ui.fragment.HomeFragment;
import com.derby.football.ui.fragment.MessageFragment;
import com.derby.football.ui.fragment.MineFragment;
import com.derby.football.ui.fragment.TeamFragment;
import com.derby.football.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;
import me.tabak.fragmentswitcher.FragmentStateArrayPagerAdapter;
import me.tabak.fragmentswitcher.FragmentSwitcher;

public class MainActivity extends BaseActivity {

    @Bind(R.id.fragment_container)
    FragmentSwitcher fragmentContainer;
    @Bind(R.id.rgMainTab)
    RadioGroup rgMainTab;
    @Bind(R.id.rbMainTabTeam)
    RadioButton rbMainTabTeam;
    @Bind(R.id.rbMainTabFind)
    RadioButton rbMainTabFind;
    @Bind(R.id.rbMainTabMine)
    RadioButton rbMainTabMine;

    private FragmentStateArrayPagerAdapter fragmentAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.main_tab_home;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        registerExitReceiver();

        initFragmentSwitcher();

    }

    private void initFragmentSwitcher() {
        fragmentContainer = (FragmentSwitcher) findViewById(R.id.fragment_container);
        fragmentAdapter = new FragmentStateArrayPagerAdapter(getSupportFragmentManager());
        fragmentContainer.setAdapter(fragmentAdapter);

        HomeFragment homeFragment = HomeFragment.newInstance();
        MessageFragment messageFragment = MessageFragment.newInstance();
        MineFragment mineFragment = MineFragment.newInstance();

        fragmentAdapter.add(homeFragment);
        fragmentAdapter.add(messageFragment);
        fragmentAdapter.add(mineFragment);
    }

    @OnClick({R.id.rbMainTabTeam, R.id.rbMainTabFind, R.id.rbMainTabMine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbMainTabTeam:
                fragmentContainer.setCurrentItem(0);
                break;
            case R.id.rbMainTabFind:
                fragmentContainer.setCurrentItem(1);
                break;
            case R.id.rbMainTabMine:
                fragmentContainer.setCurrentItem(2);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(exitReceiver);
    }

    /*************************
     * 退出
     ************************/

    private static final String ACTION_EXIT = "action.exit";
    private ExitReceiver exitReceiver = new ExitReceiver();

    private void registerExitReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_EXIT);
        registerReceiver(exitReceiver, filter);
    }

    class ExitReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            MainActivity.this.finish();
        }
    }

    private void exit() {
        startActivity(new Intent(MainActivity.this, MainActivity.class));
        Intent intent = new Intent();
        intent.setAction(ACTION_EXIT);
        sendBroadcast(intent);
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.showShort("再按一次退出应用");
                exitTime = System.currentTimeMillis();
            } else {
                exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
