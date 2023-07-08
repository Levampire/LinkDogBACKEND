package com.linkdog.baseConst;

public class LinkDogConst {
    public static String TRAINING_SET_SCRIPT_PATH="src\\main\\java\\com\\linkdog\\pythonRes\\script\\train.py";

    public static String TEST_SET_SCRIPT_PATH="src\\main\\java\\com\\linkdog\\pythonRes\\script\\test.py";
    public static String TEST_timing_SCRIPT_PATH="src\\main\\java\\com\\linkdog\\pythonRes\\script\\tming.py";

    public static String DOWNLOAD_RESULT_ROOTPATH="src\\main\\java\\com\\linkdog\\pythonRes\\file\\resultFile";
    public class runStatus{
        // 0 执行中 1 执行成功 2- 执行失败
        public static String RUNNING = "0";
        public static String SUCCESS = "1";
        public static String ERROR = "2";
    }
    public class runType{
        //1-训练集 2- 测试集
        public static String TRAINING = "1";
        public static String TEST = "2";
    }

}
