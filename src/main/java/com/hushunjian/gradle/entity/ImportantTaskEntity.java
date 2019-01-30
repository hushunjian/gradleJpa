package com.hushunjian.gradle.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "pf_important_task_table")
@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
public class ImportantTaskEntity implements Serializable {
	
	private static final long serialVersionUID = 1L; 

	/**
	 * 主键id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * 重点任务组名/组中成员名称
	 */
	@Column(name = "important_task_name", nullable = false)
	private String importantTaskName;
	
	/**
	 * 重点任务组中成员关联的任务
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id")
	private TaskEntity task;

	/**
	 * 开始时间
	 */
	@Column(name = "start_date", columnDefinition = "datetime comment '开始时间'")
	private ZonedDateTime startDate;

	/**
	 * 组成员所属的重点任务组id
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	private ImportantTaskEntity group;
	
	/**
	 * 重点任务组中的成员
	 */
	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	private List<ImportantTaskEntity> members = new ArrayList<>();
	
	/**
	 * 从重点任务组中移除单个任务
	 * 
	 * @param member
	 */
	public void removeMember(ImportantTaskEntity member){
		this.members.remove(member);
	}

	/**
	 * 将任务添加到重点任务组中
	 * 
	 * @param member
	 */
	public void addMember(ImportantTaskEntity member){
		if(this.members == null){
			this.members = new ArrayList<>();
		}
		this.members.add(member);
	}
}
