package com.derby.football.config;


public interface EventBusCode {

    int FAILURE = -100;
    int STATUS_LESS_THAN_ZERO = -101;

    int SUCCESS_LOGIN = 1000;

    int SUCCESS_REGISTER = 2000;

    int SUCCESS_FIND_COURT = 3000;
    int SUCCESS_FIND_COURT_getlist = 3001;
    int SUCCESS_FIND_COURT_getinfo = 3002;
    int SUCCESS_FIND_COURT_go_order = 3003;
    int SUCCESS_FIND_COURT_getplace = 3004;
    int SUCCESS_FIND_COURT_order_add = 3005;
    int SUCCESS_FIND_COURT_order_del = 3006;

}
