package com.hushunjian.gradle.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "pf_task_table")
@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
public class TaskEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * 任务名称
	 */
	@Column(name = "task_name", nullable = false)
	private String taskName;
	
	/**
	 * 对应的重点任务数据
	 */
	@OneToMany(mappedBy = "task",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<ImportantTaskEntity> importantTasks = new ArrayList<>();
}
