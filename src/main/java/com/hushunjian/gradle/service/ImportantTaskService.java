package com.hushunjian.gradle.service;

import java.time.ZonedDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.entity.ImportantTaskEntity;
import com.hushunjian.gradle.entity.TaskEntity;
import com.hushunjian.gradle.repo.ImportantTaskRepo;
import com.hushunjian.gradle.repo.TaskRepo;

@Service
@Transactional
public class ImportantTaskService {
	
	@Autowired
	private TaskRepo taskRepo;
	
	@Autowired
	private ImportantTaskRepo importantTaskRepo;

	/**
	 * 添加任务
	 * 
	 * @param taskName
	 * @return
	 */
	public TaskEntity addTask(String taskName) {
		TaskEntity task = new TaskEntity();
		task.setTaskName(taskName);
		taskRepo.save(task);
		return task;
	}

	/**
	 * 添加重点任务组
	 * 
	 * @param importantTaskGroupName
	 * @return
	 */
	public ImportantTaskEntity addImportantTaskGroup(String importantTaskGroupName) {
		ImportantTaskEntity importantTaskGroup = new ImportantTaskEntity();
		importantTaskGroup.setImportantTaskName(importantTaskGroupName);
		importantTaskGroup.setStartDate(ZonedDateTime.now());
		importantTaskRepo.save(importantTaskGroup);
		return importantTaskGroup;
	}
	
	/**
	 * 获取所有的任务列表
	 * 
	 * @return
	 */
	public List<TaskEntity> getAllTask() {
		return taskRepo.findAll();
	}

	/**
	 * 获取所有的重点任务组数据
	 * 
	 * @return
	 */
	public List<ImportantTaskEntity> getAllImportantTaskGroup() {
		List<ImportantTaskEntity> importantTaskGroups = importantTaskRepo.findByGroupIsNull();
		return importantTaskGroups;
	}

	/**
	 * 获取单个任务
	 * 
	 * @param taskId
	 * @return
	 */
	public TaskEntity findTaskById(Long taskId) {
		return taskRepo.findOne(taskId);
	}

	/**
	 * 获取单个重点任务组数据
	 * 
	 * @param groupId
	 * @return
	 */
	public ImportantTaskEntity findImportantGroupTaskById(Long groupId) {
		return importantTaskRepo.findOne(groupId);
	}

	/**
	 * 任务加入重点任务组
	 * 
	 * @param task
	 * @param importantGroupTask
	 */
	public void joinTaskImportantTaskGroup(TaskEntity task, ImportantTaskEntity importantGroupTask) {
		ImportantTaskEntity member = new ImportantTaskEntity();
		member.setGroup(importantGroupTask);
		member.setImportantTaskName(task.getTaskName());
		member.setTask(task);
		importantGroupTask.getMembers().add(member);
		importantTaskRepo.save(importantGroupTask);
	}

	/**
	 * 从重点任务组中移除任务
	 * 
	 * @param importantGroupTask
	 * @param member
	 */
	public void removeTaskFromImportantTaskGroup(ImportantTaskEntity importantGroupTask, ImportantTaskEntity member) {
		importantGroupTask.removeMember(member);
	}

	/**
	 * 删除总控任务
	 *
	 * @param task
	 */
	public void deleteTask(TaskEntity task) {
		taskRepo.delete(task);
	}

	/**
	 * 获取所有加入重点任务组的任务
	 *
	 * @return
	 */
	public List<TaskEntity> getJoinImportantGroupTask() {
		return  taskRepo.findByImportantTasksIsNotNull();
	}
}
