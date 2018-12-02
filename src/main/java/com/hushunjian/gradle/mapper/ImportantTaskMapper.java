package com.hushunjian.gradle.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.hushunjian.gradle.entity.ImportantTaskEntity;
import com.hushunjian.gradle.response.ImportantTaskResponse;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImportantTaskMapper {
	ImportantTaskMapper INSTANCE = Mappers.getMapper(ImportantTaskMapper.class);

	@Mapping(target = "task.importantTasks", ignore = true)
	@Mapping(target = "group.members", ignore = true)
	ImportantTaskResponse toImportantTaskResponse(ImportantTaskEntity importantTask);

	List<ImportantTaskResponse> toImportantTaskResponses(List<ImportantTaskEntity> importantTasks);
}