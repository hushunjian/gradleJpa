package com.hushunjian.gradle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.hushunjian.gradle.entity.ImportantTaskEntity;
import com.hushunjian.gradle.entity.TaskEntity;
import com.hushunjian.gradle.mapper.ImportantTaskMapper;
import com.hushunjian.gradle.mapper.TaskMapper;
import com.hushunjian.gradle.response.ImportantTaskResponse;
import com.hushunjian.gradle.response.TaskResponse;
import com.hushunjian.gradle.service.ImportantTaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("ImportantTaskController")
@RequestMapping(value = "/importantTask")
@Api(value = "ImportantTaskController", description = "重点任务相关接口", produces = MediaType.ALL_VALUE)
public class ImportantTaskController extends BaseController {

	@Autowired
	private ImportantTaskService importantTaskService;

	@ResponseBody
	@ApiOperation(value = "添加任务接口")
	@GetMapping(value = "addTask")
	public Object addTask(@RequestParam String taskName) {
		TaskEntity task = importantTaskService.addTask(taskName);
		TaskResponse taskResponse = TaskMapper.INSTANCE.toTaskResponse(task);
		return success(taskResponse);
	}

	@ApiOperation(value = "添加重点任务组接口")
	@GetMapping(value = "addImportantTaskGroup")
	public Object addImportantTaskGroup(@RequestParam String importantTaskGroupName) {
		ImportantTaskEntity importantTaskGroup = importantTaskService.addImportantTaskGroup(importantTaskGroupName);
		ImportantTaskResponse importantTaskResponse = ImportantTaskMapper.INSTANCE.toImportantTaskResponse(importantTaskGroup);
		return success(importantTaskResponse);
	}

	@ApiOperation(value = "获取所有的任务")
	@GetMapping(value = "getAllTask")
	public Object getAllTask() {
		List<TaskEntity> tasks = importantTaskService.getAllTask();
		List<TaskResponse> taskResponses = TaskMapper.INSTANCE.toTaskResponses(tasks);
		return success(taskResponses);
	}

	@ApiOperation(value = "获取所有的重点任务组")
	@GetMapping(value = "getAllImportantTaskGroup")
	public Object getAllImportantTaskGroup() {
		List<ImportantTaskEntity> importantTaskGroups = importantTaskService.getAllImportantTaskGroup();
		List<ImportantTaskResponse> importantTaskGroupResponse = ImportantTaskMapper.INSTANCE.toImportantTaskResponses(importantTaskGroups);
		return success(importantTaskGroupResponse);
	}

	@ApiOperation(value = "任务加入重点任务组")
	@GetMapping(value = "joinTaskImportantTaskGroup")
	public Object joinTaskImportantTaskGroup(@RequestParam Long groupId, @RequestParam Long taskId) {
		TaskEntity task = importantTaskService.findTaskById(taskId);
		if (task == null) {
			return failure("任务数据不存在!");
		}
		ImportantTaskEntity importantGroupTask = importantTaskService.findImportantGroupTaskById(groupId);
		if (importantGroupTask == null) {
			return failure("重点任务组数据不存在!");
		}
		List<ImportantTaskEntity> members = importantGroupTask.getMembers();
		// 判断当前任务是否已经加入过当前重点任务组
		for (ImportantTaskEntity member : members) {
			if (member.getTask() == task) {
				return failure("所选任务已加入当前重点任务组");
			}
		}
		importantTaskService.joinTaskImportantTaskGroup(task, importantGroupTask);
		ImportantTaskResponse importantTaskResponse = ImportantTaskMapper.INSTANCE.toImportantTaskResponse(importantGroupTask);
		return success(importantTaskResponse);
	}

	@ApiOperation(value = "从重点任务组中移除任务")
	@GetMapping(value = "removeTaskFromImportantTaskGroup")
	public Object removeTaskFromImportantTaskGroup(@RequestParam Long groupId, @RequestParam Long taskId) {
		TaskEntity task = importantTaskService.findTaskById(taskId);
		if (task == null) {
			return failure("任务数据不存在!");
		}
		ImportantTaskEntity importantGroupTask = importantTaskService.findImportantGroupTaskById(groupId);
		if (importantGroupTask == null) {
			return failure("重点任务组数据不存在!");
		}
		List<ImportantTaskEntity> members = importantGroupTask.getMembers();
		// 判断当前任务是否已经加入过当前重点任务组
		for (ImportantTaskEntity member : members) {
			if (member.getTask() == task) {
				// 加入了当前任务组
				importantTaskService.removeTaskFromImportantTaskGroup(importantGroupTask,member);
				ImportantTaskResponse importantTaskResponse = ImportantTaskMapper.INSTANCE.toImportantTaskResponse(importantGroupTask);
				return success(importantTaskResponse);
			}
		}
		return failure("所选任务未加入当前任务组,不需要移除");
	}

	@ApiOperation(value = "根据id获取任务")
	@GetMapping(value = "findTaskById")
	public Object findTaskById(@RequestParam Long id) {
		TaskEntity task = importantTaskService.findTaskById(id);
		TaskResponse taskResponse = TaskMapper.INSTANCE.toTaskResponse(task);
		return success(taskResponse);
	}

	@ApiOperation(value = "根据id获取重点任务组")
	@GetMapping(value = "findImportantTaskGroupById")
	public Object findImportantTaskGroupById(@RequestParam Long id) {
		ImportantTaskEntity importantTaskGroup = importantTaskService.findImportantGroupTaskById(id);
		ImportantTaskResponse importantTaskResponse = ImportantTaskMapper.INSTANCE.toImportantTaskResponse(importantTaskGroup);
		return success(importantTaskResponse);
	}

	@ApiOperation(value = "删除总控计划")
	@GetMapping(value = "deleteTask")
	public Object deleteTask(@RequestParam Long id){
		TaskEntity task = importantTaskService.findTaskById(id);
		if (task == null){
			return failure("总控任务不存在");
		}
		importantTaskService.deleteTask(task);
		return success("总控任务删除成功");
	}

	@ApiOperation(value = "获取所有加入重点任务组的任务")
	@GetMapping(value = "getJoinImportantGroupTask")
	public Object getJoinImportantGroupTask(){
		List<TaskEntity> tasks = importantTaskService.getJoinImportantGroupTask();
		List<TaskResponse> taskResponses = TaskMapper.INSTANCE.toTaskResponses(tasks);
		return success(taskResponses);
	}
}
