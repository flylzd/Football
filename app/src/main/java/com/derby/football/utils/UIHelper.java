package com.derby.football.utils;


import android.content.Context;
import android.content.Intent;

import com.derby.football.ui.activity.CityAreaActivity;
import com.derby.football.ui.activity.CourtActivity;
import com.derby.football.ui.activity.CourtDetailActivity;
import com.derby.football.ui.activity.CourtChooseActivity;
import com.derby.football.ui.activity.LoginActivity;
import com.derby.football.ui.activity.MainActivity;
import com.derby.football.ui.activity.MatchActivity;
import com.derby.football.ui.activity.MatchDetailsActivity;
import com.derby.football.ui.activity.MineEditActivity;
import com.derby.football.ui.activity.MineInfoActivity;
import com.derby.football.ui.activity.RegisterActivity;
import com.derby.football.ui.activity.TeamActivity;
import com.derby.football.ui.activity.TeamDetailActivity;
import com.derby.football.ui.activity.TrainingActivity;
import com.derby.football.ui.activity.TrainingDetailsActivity;

public class UIHelper {

    public static void showLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public static void showRegisterActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    public static void showMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    /**
     * 选择城市
     */
    public static void showCityAreaActivity(Context context) {
        Intent intent = new Intent(context, CityAreaActivity.class);
        context.startActivity(intent);
    }

    /**
     * 球场列表
     */
    public static void showCourtActivity(Context context) {
        Intent intent = new Intent(context, CourtActivity.class);
        context.startActivity(intent);
    }

    /**
     * 场地详细
     */
    public static void showFindCourtDetailActivity(Context context,String mid) {
        Intent intent = new Intent(context, CourtDetailActivity.class);
        intent.putExtra("mid",mid);
        context.startActivity(intent);
    }

    /**
     * 场次选择
     */
    public static void showCourtChooseActivity(Context context,String mid,String date) {
        Intent intent = new Intent(context, CourtChooseActivity.class);
        intent.putExtra("mid",mid);
        intent.putExtra("date",date);
        context.startActivity(intent);
    }

    /**
     * 球队列表
     */
    public static void showTeamActivity(Context context) {
        Intent intent = new Intent(context, TeamActivity.class);
        context.startActivity(intent);
    }

    /**
     * 球队详情
     */
    public static void showTeamDetailActivity(Context context) {
        Intent intent = new Intent(context, TeamDetailActivity.class);
        context.startActivity(intent);
    }

    /**
     * 集训
     */
    public static void showTrainingActivity(Context context) {
        Intent intent = new Intent(context, TrainingActivity.class);
        context.startActivity(intent);
    }

    /**
     * 集训详情
     */
    public static void showTrainingDetailsActivity(Context context) {
        Intent intent = new Intent(context, TrainingDetailsActivity.class);
        context.startActivity(intent);
    }

    /**
     * 赛事
     */
    public static void showMatchActivity(Context context) {
        Intent intent = new Intent(context, MatchActivity.class);
        context.startActivity(intent);
    }

    /**
     * 赛事详情
     */
    public static void showMatchDetailsActivity(Context context) {
        Intent intent = new Intent(context, MatchDetailsActivity.class);
        context.startActivity(intent);
    }

    /**
     * 个人资料
     */
    public static void showMineInfoActivity(Context context) {
        Intent intent = new Intent(context, MineInfoActivity.class);
        context.startActivity(intent);
    }

    /**
     * 完善个人资料
     */
    public static void showMineEditActivity(Context context) {
        Intent intent = new Intent(context, MineEditActivity.class);
        context.startActivity(intent);
    }

}
