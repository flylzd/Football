package com.derby.football.ui.fragment;


import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.derby.football.R;
import com.derby.football.base.BaseFragment;
import com.derby.football.utils.UIHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment {


    @Bind(R.id.gridView)
    GridView gridView;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    protected void initView() {
        super.initView();

        String[] from = {"image", "text"};
        int[] to = {R.id.imgHome, R.id.tvName};
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), getData(), R.layout.item_home, from, to);
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        UIHelper.showCourtActivity(getActivity());
                        break;
                    case 1:
                        break;
                    case 2:
                        UIHelper.showTeamActivity(getActivity());
                        break;
                    case 3:
                        UIHelper.showTrainingActivity(getActivity());
                        break;
                    case 4:
                        UIHelper.showMatchActivity(getActivity());
                        break;
                }
            }
        });
    }


    // 图片封装为一个数组
    private int[] icons = {R.mipmap.home_item_1, R.mipmap.home_item_2, R.mipmap.home_item_3,
            R.mipmap.home_item_4, R.mipmap.home_item_5,};

    //    private int[] name = {};
    public List<Map<String, Object>> getData() {
        String[] name = getResources().getStringArray(R.array.item_home_names);
        //cion和iconName的长度是相同的，这里任选其一都可以
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < icons.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icons[i]);
            map.put("text", name[i]);
            dataList.add(map);
        }
        return dataList;
    }

}
