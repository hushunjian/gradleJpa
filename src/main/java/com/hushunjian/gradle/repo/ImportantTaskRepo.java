package com.hushunjian.gradle.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.ImportantTaskEntity;

public interface ImportantTaskRepo extends JpaRepository<ImportantTaskEntity, Long>{

	List<ImportantTaskEntity> findByGroupIsNull();

}

