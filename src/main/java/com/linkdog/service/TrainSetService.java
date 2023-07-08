package com.linkdog.service;

import com.frameWork.dto.BaseResponse;
import com.frameWork.dto.ProjectDto;
import com.frameWork.dto.ReqDto;
import org.springframework.stereotype.Component;

@Component
public interface TrainSetService {

    BaseResponse uploadTraingSet(ProjectDto reqDto);
    BaseResponse updateTraingSet(ProjectDto reqDto);

    BaseResponse startTraining(ProjectDto reqDto);

    BaseResponse deleteTraingSet(ProjectDto reqDto);
}
