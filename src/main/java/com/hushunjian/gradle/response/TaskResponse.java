package com.hushunjian.gradle.response;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TaskResponse {
	
	@ApiModelProperty(value = "主键id")
	private Long id;
	
	@ApiModelProperty(value = "任务名称")
	private String taskName;

	@ApiModelProperty(value = "重点任务集合")
	private List<ImportantTaskResponse> importantTasks;
}
