package com.linkdog.service.impl;

import com.frameWork.dto.BaseResponse;
import com.frameWork.dto.ProjectDto;
import com.frameWork.dto.ReqDto;
import com.linkdog.baseConst.LinkDogConst;
import com.linkdog.service.PythonRunner;
import com.linkdog.service.TrainSetService;
import com.linkdog.utils.JsonParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TrainSetServiceImpl implements TrainSetService {

    @Autowired
    JsonParseUtil jsonParseUtil;

    @Autowired
    PythonRunner pythonRunner;

    @Override
    public BaseResponse uploadTraingSet(ProjectDto reqDto) {
        BaseResponse baseResponse = new BaseResponse();
        if (null != jsonParseUtil.queryProjectResult(reqDto)) {
            throw new RuntimeException("项目名称不可用");
        }
        vaidateReq(reqDto);
        reqDto.setStatus("3");
        jsonParseUtil.insertProject(reqDto);
        return baseResponse;
    }

    @Override
    public BaseResponse updateTraingSet(ProjectDto reqDto) {
        BaseResponse baseResponse = new BaseResponse();
        if (null == jsonParseUtil.queryProjectResult(reqDto)) {
            throw new RuntimeException("未找到项目");
        }
        jsonParseUtil.update(reqDto);
        return new BaseResponse();
    }

    private void vaidateReq(ProjectDto reqDto) {
        if (StringUtils.isEmpty(reqDto.getName())) {
            throw new RuntimeException("名称不能为空");
        }
        if (StringUtils.isEmpty(reqDto.getType())) {
            throw new RuntimeException("类型不能为空");
        }
        if (StringUtils.isEmpty(reqDto.getFilePath())) {
            throw new RuntimeException("数据集地址不能为空");
        }
    }

    @Override
    public BaseResponse startTraining(ProjectDto reqDto) {
        ProjectDto projectDto = jsonParseUtil.queryProjectResult(reqDto);
        if (null == projectDto) {
            throw new RuntimeException("未找到项目");
        }
        String script = projectDto.getType().equals(LinkDogConst.runType.TRAINING) ?
                LinkDogConst.TRAINING_SET_SCRIPT_PATH : LinkDogConst.TEST_SET_SCRIPT_PATH;
        pythonRunner.pythonRunner(script, projectDto);
        return new BaseResponse();
    }

    @Override
    public BaseResponse deleteTraingSet(ProjectDto reqDto) {
        BaseResponse baseResponse = new BaseResponse();
        if (null == jsonParseUtil.queryProjectResult(reqDto)) {
            throw new RuntimeException("未找到项目");
        }
        jsonParseUtil.deleteProject(reqDto);
        return new BaseResponse();
    }
}
