package com.hushunjian.gradle.mapper;

import com.hushunjian.gradle.entity.ImportantTaskEntity;
import com.hushunjian.gradle.entity.TaskEntity;
import com.hushunjian.gradle.response.ImportantTaskResponse;
import com.hushunjian.gradle.response.TaskResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-12-02T15:13:41+0800",
    comments = "version: 1.2.0.CR1, compiler: Eclipse JDT (IDE) 1.2.0.v20150514-0146, environment: Java 1.8.0_161 (Oracle Corporation)"
)
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskResponse toTaskResponse(TaskEntity task) {
        if ( task == null ) {
            return null;
        }

        TaskResponse taskResponse = new TaskResponse();

        taskResponse.setId( task.getId() );
        taskResponse.setImportantTasks( importantTaskEntityListToImportantTaskResponseList( task.getImportantTasks() ) );
        taskResponse.setTaskName( task.getTaskName() );

        return taskResponse;
    }

    @Override
    public List<TaskResponse> toTaskResponses(List<TaskEntity> tasks) {
        if ( tasks == null ) {
            return null;
        }

        List<TaskResponse> list = new ArrayList<TaskResponse>( tasks.size() );
        for ( TaskEntity taskEntity : tasks ) {
            list.add( toTaskResponse( taskEntity ) );
        }

        return list;
    }

    protected ImportantTaskResponse importantTaskEntityToImportantTaskResponse(ImportantTaskEntity importantTaskEntity) {
        if ( importantTaskEntity == null ) {
            return null;
        }

        ImportantTaskResponse importantTaskResponse = new ImportantTaskResponse();

        importantTaskResponse.setId( importantTaskEntity.getId() );
        importantTaskResponse.setImportantTaskName( importantTaskEntity.getImportantTaskName() );

        return importantTaskResponse;
    }

    protected List<ImportantTaskResponse> importantTaskEntityListToImportantTaskResponseList(List<ImportantTaskEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<ImportantTaskResponse> list1 = new ArrayList<ImportantTaskResponse>( list.size() );
        for ( ImportantTaskEntity importantTaskEntity : list ) {
            list1.add( importantTaskEntityToImportantTaskResponse( importantTaskEntity ) );
        }

        return list1;
    }
}
