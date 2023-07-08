package com.linkdog.controller;


import com.frameWork.dto.BaseResponse;
import com.frameWork.dto.ProjectDto;
import com.frameWork.dto.ReqDto;
import com.linkdog.service.CommonService;
import com.linkdog.service.TrainSetService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrainingController {
    @Autowired
    TrainSetService trainSetService;

    @Autowired
    CommonService commonService;

    @RequestMapping("/queryProjectList")
    public BaseResponse queryProjectList(@RequestBody ProjectDto reqDto){
        BaseResponse baseResponse = new BaseResponse(true);
        baseResponse.setData(commonService.queryProjectList(reqDto));
        return baseResponse;
    }

    @RequestMapping("/uploadTraingSet")
    public BaseResponse uploadTraingSet(@RequestBody ProjectDto reqDto){
        BaseResponse baseResponse = new BaseResponse(true);
        trainSetService.uploadTraingSet(reqDto);
        baseResponse.setMessage("新增项目成功");
        return baseResponse;
    }
    @RequestMapping("/updateTraingSet")
    public BaseResponse updateTraingSet(@RequestBody ProjectDto reqDto){
        BaseResponse baseResponse = new BaseResponse(true);
        trainSetService.updateTraingSet(reqDto);
        baseResponse.setMessage("修改项目成功");
        return baseResponse;
    }
    @RequestMapping("/deleteTraingSet")
    public BaseResponse deleteTraingSet(@RequestBody ProjectDto reqDto){
        BaseResponse baseResponse = new BaseResponse(true);
        trainSetService.deleteTraingSet(reqDto);
        baseResponse.setMessage("删除项目成功");
        return baseResponse;
    }
    @RequestMapping("/startTraining")
    public BaseResponse startTraining(@RequestBody ProjectDto reqDto){
        return  trainSetService.startTraining(reqDto);
    }

    //文件下载接口
    @RequestMapping(value = "/downloadResult")
    public HttpServletResponse getFile(@RequestBody ProjectDto reqDto,HttpServletResponse response)  {
     return commonService.getFile(reqDto,response);
    }

    @GetMapping("/test")
    public String test(){
        return "hello springboot";
    }

}