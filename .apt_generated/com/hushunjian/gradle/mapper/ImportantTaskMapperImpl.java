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
    date = "2018-12-02T15:54:04+0800",
    comments = "version: 1.2.0.CR1, compiler: Eclipse JDT (IDE) 1.2.0.v20150514-0146, environment: Java 1.8.0_161 (Oracle Corporation)"
)
public class ImportantTaskMapperImpl implements ImportantTaskMapper {

    @Override
    public ImportantTaskResponse toImportantTaskResponse(ImportantTaskEntity importantTask) {
        if ( importantTask == null ) {
            return null;
        }

        ImportantTaskResponse importantTaskResponse = new ImportantTaskResponse();

        importantTaskResponse.setGroup( importantTaskEntityToImportantTaskResponse( importantTask.getGroup() ) );
        importantTaskResponse.setId( importantTask.getId() );
        importantTaskResponse.setImportantTaskName( importantTask.getImportantTaskName() );
        importantTaskResponse.setMembers( toImportantTaskResponses( importantTask.getMembers() ) );
        importantTaskResponse.setTask( taskEntityToTaskResponse1( importantTask.getTask() ) );

        return importantTaskResponse;
    }

    @Override
    public List<ImportantTaskResponse> toImportantTaskResponses(List<ImportantTaskEntity> importantTasks) {
        if ( importantTasks == null ) {
            return null;
        }

        List<ImportantTaskResponse> list = new ArrayList<ImportantTaskResponse>( importantTasks.size() );
        for ( ImportantTaskEntity importantTaskEntity : importantTasks ) {
            list.add( toImportantTaskResponse( importantTaskEntity ) );
        }

        return list;
    }

    protected TaskResponse taskEntityToTaskResponse(TaskEntity taskEntity) {
        if ( taskEntity == null ) {
            return null;
        }

        TaskResponse taskResponse = new TaskResponse();

        taskResponse.setId( taskEntity.getId() );
        taskResponse.setImportantTasks( toImportantTaskResponses( taskEntity.getImportantTasks() ) );
        taskResponse.setTaskName( taskEntity.getTaskName() );

        return taskResponse;
    }

    protected ImportantTaskResponse importantTaskEntityToImportantTaskResponse(ImportantTaskEntity importantTaskEntity) {
        if ( importantTaskEntity == null ) {
            return null;
        }

        ImportantTaskResponse importantTaskResponse = new ImportantTaskResponse();

        importantTaskResponse.setGroup( toImportantTaskResponse( importantTaskEntity.getGroup() ) );
        importantTaskResponse.setId( importantTaskEntity.getId() );
        importantTaskResponse.setImportantTaskName( importantTaskEntity.getImportantTaskName() );
        importantTaskResponse.setTask( taskEntityToTaskResponse( importantTaskEntity.getTask() ) );

        return importantTaskResponse;
    }

    protected TaskResponse taskEntityToTaskResponse1(TaskEntity taskEntity) {
        if ( taskEntity == null ) {
            return null;
        }

        TaskResponse taskResponse = new TaskResponse();

        taskResponse.setId( taskEntity.getId() );
        taskResponse.setTaskName( taskEntity.getTaskName() );

        return taskResponse;
    }
}
