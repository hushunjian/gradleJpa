package com.hushunjian.gradle.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.hushunjian.gradle.entity.TaskEntity;
import com.hushunjian.gradle.response.TaskResponse;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
    
    TaskResponse toTaskResponse(TaskEntity task);
    
    List<TaskResponse> toTaskResponses(List<TaskEntity> tasks);
}