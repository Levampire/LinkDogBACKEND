package com.linkdog.service.impl;

import com.frameWork.dto.ProjectDto;
import com.linkdog.baseConst.LinkDogConst;
import com.linkdog.service.PythonRunner;
import com.linkdog.utils.JsonParseUtil;
import com.linkdog.webSocketService.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PythonRunnerImpl implements PythonRunner {
    @Autowired
    JsonParseUtil jsonParseUtil;

    @Autowired
    MyWebSocketHandler myWebSocketHandler;

    @Override
    @Async
    public void pythonRunner(String scriptPath, ProjectDto projectDto) {
        String path = projectDto.getFilePath();
        String projectName = projectDto.getName();
        long start = System.currentTimeMillis();
        long end = 0L;
        String result = "";
        List<String> arguments = new ArrayList<>();
        int re = 0;
        boolean errorflag = true;
        try {
            myWebSocketHandler.sendMessageToProject(projectName,
                    composeMessage("开始执行Python脚本：" + scriptPath + "数据集路径：" + path));
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.redirectErrorStream(true);
            List<String> commands = new ArrayList<>();
            commands.add("python");
            commands.add(scriptPath);
            commands.add("-u");
            commands.addAll(arguments);
            // 加载命令
            processBuilder.command(commands);
            // 启动进程
            Process process = processBuilder.start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
            try {
                //循环等待进程输出，判断进程存活则循环获取输出流数据
                while (process.isAlive()) {
                    while (bufferedReader.ready()) {
                        String s = bufferedReader.readLine();
                        if (!StringUtils.isEmpty(s)) {
                            //推送日志
                            myWebSocketHandler.sendMessageToProject(projectName, composeMessage(s));
                            result = composeMessage(s);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            re = process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
            errorflag = false;
            //推送日志
            try {
                myWebSocketHandler.sendMessageToProject(projectName, composeMessage(e.getMessage()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("运行脚本失败：", e);
        } finally {
            end = System.currentTimeMillis();
            System.out.println("运行时间:" + (end - start));
            //更新json记录
            projectDto.setStatus(errorflag ? LinkDogConst.runStatus.SUCCESS : LinkDogConst.runStatus.ERROR);
            projectDto.setResult(composeMessage("JAVA执行脚本成功 - PYTHON执行结果：" + result));
            projectDto.setRunningTime(String.valueOf(end - start));
            projectDto.setHasResultPath(true);
            projectDto.setResultPath("FR_model.joblib");
            try {
                myWebSocketHandler.sendMessageToProject(projectName, composeMessage("JAVA执行脚本结束：用时" + (end - start) + "ms - PYTHON执行结果："
                        + result));
                myWebSocketHandler.sendMessageToProject(projectName, "ScriptEndLinkDog");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            jsonParseUtil.update(projectDto);
        }

    }

    private String composeMessage(String message) {
        return "[" + getNowDate() + "]>" + message;
    }

    private String getNowDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}