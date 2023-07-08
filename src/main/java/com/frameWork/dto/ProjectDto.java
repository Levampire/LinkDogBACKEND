package com.frameWork.dto;

import java.util.List;

public class ProjectDto {
    //项目名称
    String name;


    //项目原名称
    String newName;
    //1-训练集 2- 测试集
    String type;

    //数据集路径
    String filePath;

    //状态 0 执行中 1 执行成功 2- 执行失败 3- 未执行
    String status;


    boolean hasResultPath;


    String resultPath;


    List<String> logList;

    //python 最后一句输出日志
    String result;

    //总运行时间
    String runningTime;

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public List<String> getLogList() {
        return logList;
    }

    public void setLogList(List<String> logList) {
        this.logList = logList;
    }

    public boolean isHasResultPath() {
        return hasResultPath;
    }

    public void setHasResultPath(boolean hasResultPath) {
        this.hasResultPath = hasResultPath;
    }

    public String getResultPath() {
        return resultPath;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }

}
