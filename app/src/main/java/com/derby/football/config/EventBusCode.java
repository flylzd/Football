package com.derby.football.config;


public interface EventBusCode {

    int FAILURE = -100;
    int STATUS_LESS_THAN_ZERO = -101;

    int SUCCESS_LOGIN = 1000;

    int SUCCESS_REGISTER = 2000;

    int SUCCESS_COURT = 3000;
    int SUCCESS_COURT_getlist = 3001;
    int SUCCESS_COURT_getinfo = 3010;
    int SUCCESS_COURT_go_choose = 3011;
    int SUCCESS_COURT_choose_date_change = 3020;
    int SUCCESS_COURT_getplace = 3021;
    int SUCCESS_COURT_place_add = 3022;
    int SUCCESS_COURT_place_del = 3023;
    int SUCCESS_COURT_place_send = 3024;

}
