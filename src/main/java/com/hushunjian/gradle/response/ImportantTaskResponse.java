package com.hushunjian.gradle.response;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ImportantTaskResponse {
	
	@ApiModelProperty(value = "主键id")
	private Long id;
	
	@ApiModelProperty(value = "重点任务名称")
	private String importantTaskName;
	
	@ApiModelProperty(value = "重点任务组中成员")
	private List<ImportantTaskResponse> members;
	
	@ApiModelProperty(value = "关联任务")
	private TaskResponse task;
	
	@ApiModelProperty(value = "所属重点任务组")
	private ImportantTaskResponse group;
}
