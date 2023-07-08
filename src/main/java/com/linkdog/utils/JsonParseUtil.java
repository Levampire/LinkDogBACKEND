package com.linkdog.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.frameWork.dto.ProjectDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;

@Component
public class JsonParseUtil {
    static String JSON_PATH = "src/main/java/com/linkdog/pythonRes/config/setProject.json";

    public  String getProjectFilePath(String projectName) {
        ArrayList<ProjectDto> dataList=new ArrayList<>();
        String findPath = "";
        //读取用户数据存放到ArrayList集合当中
        try (BufferedReader br = new BufferedReader(new FileReader(JSON_PATH))) {
            String json = "";
            String line;
            while ((line = br.readLine()) != null) {
                json += line + "\n";
            }
            br.close();
            Gson gson = new Gson();
            dataList = gson.fromJson(json, new TypeToken<ArrayList<ProjectDto>>() {
            }.getType());
            if(!CollectionUtils.isEmpty(dataList) ){
                for (ProjectDto project : dataList) {
                    if (project.getName().equals(projectName)) {
                        findPath=project.getFilePath();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return findPath;
    }

    public void insertProject(ProjectDto projectDto) {
        //读取用户数据存放到ArrayList集合当中
        try (BufferedReader br = new BufferedReader(new FileReader(JSON_PATH))) {
            String json = "";
            String line;
            while ((line = br.readLine()) != null) {
                json += line + "\n";
            }
            Gson gson = new Gson();
            ArrayList<ProjectDto> dataList = gson.fromJson(json, new TypeToken<ArrayList<ProjectDto>>() {
            }.getType());
            //修改数据
            dataList.add(projectDto);
            String jsonString = gson.toJson(dataList); // 将List<User>对象转换为JSON字符串
            //将新数据写入data.json文件中（此时修改原有的数据，但其他数据不会改变）
            File file = new File(JSON_PATH);
            BufferedWriter writer = null; // 创建BufferedWriter对象
            try {
                writer = new BufferedWriter(new FileWriter(file));
                writer.write(jsonString); // 将JSON字符串写入文件
                writer.close(); // 关闭BufferedWriter对象
                System.out.println("数据修改成功");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void update(ProjectDto projectDto) {
        //读取用户数据存放到ArrayList集合当中
        try (BufferedReader br = new BufferedReader(new FileReader(JSON_PATH))) {
            String json = "";
            String line;
            while ((line = br.readLine()) != null) {
                json += line + "\n";
            }
            Gson gson = new Gson();
            ArrayList<ProjectDto> dataList = gson.fromJson(json, new TypeToken<ArrayList<ProjectDto>>() {
            }.getType());
            for (ProjectDto project : dataList) {
                if (project.getName().equals(projectDto.getName())) {
                    if(!StringUtils.isEmpty(projectDto.getFilePath())){
                        project.setFilePath(projectDto.getFilePath());
                    }
                    if(!StringUtils.isEmpty(projectDto.getStatus())){
                        project.setStatus(projectDto.getStatus());
                    }
                    if(!StringUtils.isEmpty(projectDto.getResult())){
                        project.setResult(projectDto.getResult());
                    }
                    if(!StringUtils.isEmpty(projectDto.getNewName())){
                        project.setName(projectDto.getNewName());
                    }
                    if(!StringUtils.isEmpty(projectDto.getRunningTime())){
                        project.setRunningTime(projectDto.getRunningTime());
                    }
                }
            }
            String jsonString = gson.toJson(dataList); // 将List<User>对象转换为JSON字符串
            //将新数据写入data.json文件中（此时修改原有的数据，但其他数据不会改变）
            File file = new File(JSON_PATH);
            BufferedWriter writer = null; // 创建BufferedWriter对象
            try {
                writer = new BufferedWriter(new FileWriter(file));
                writer.write(jsonString); // 将JSON字符串写入文件
                writer.close(); // 关闭BufferedWriter对象
                System.out.println("数据修改成功");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProjectDto queryProjectResult(ProjectDto projectDto) {
        //读取用户数据存放到ArrayList集合当中
        try (BufferedReader br = new BufferedReader(new FileReader(JSON_PATH))) {
            String json = "";
            String line;
            while ((line = br.readLine()) != null) {
                json += line + "\n";
            }
            Gson gson = new Gson();
            ArrayList<ProjectDto> dataList = gson.fromJson(json, new TypeToken<ArrayList<ProjectDto>>() {
            }.getType());
            for (ProjectDto project : dataList) {
                if (project.getName().equals(projectDto.getName())) {
                   return project;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public  ArrayList<ProjectDto> queryProjectList(ProjectDto projectDto) {
        ArrayList<ProjectDto> resList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(JSON_PATH))) {
            String json = "";
            String line;
            while ((line = br.readLine()) != null) {
                json += line + "\n";
            }
            Gson gson = new Gson();
            ArrayList<ProjectDto> dataList = gson.fromJson(json, new TypeToken<ArrayList<ProjectDto>>() {
            }.getType());

            for (ProjectDto project : dataList) {
                if (project.getType().equals(projectDto.getType())) {
                    resList.add(project);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resList;
    }


    public void deleteProject(ProjectDto reqDto) {
        //读取用户数据存放到ArrayList集合当中
        try (BufferedReader br = new BufferedReader(new FileReader(JSON_PATH))) {
            String json = "";
            String line;
            while ((line = br.readLine()) != null) {
                json += line + "\n";
            }
            Gson gson = new Gson();
            ArrayList<ProjectDto> dataList = gson.fromJson(json, new TypeToken<ArrayList<ProjectDto>>() {
            }.getType());
           ArrayList<ProjectDto> newDataList = new ArrayList<>();
            for (ProjectDto project : dataList) {
                if (!project.getName().equals(reqDto.getName())) {
                    newDataList.add(project);
                }
            }
            String jsonString = gson.toJson(newDataList); // 将List<User>对象转换为JSON字符串
            //将新数据写入data.json文件中（此时修改原有的数据，但其他数据不会改变）
            File file = new File(JSON_PATH);
            BufferedWriter writer = null; // 创建BufferedWriter对象
            try {
                writer = new BufferedWriter(new FileWriter(file));
                writer.write(jsonString); // 将JSON字符串写入文件
                writer.close(); // 关闭BufferedWriter对象
                System.out.println("数据修改成功");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
