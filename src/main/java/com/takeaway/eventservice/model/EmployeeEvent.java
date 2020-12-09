package com.takeaway.eventservice.model;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.fasterxml.jackson.databind.JsonNode;
import com.takeaway.eventservice.kafka.events.EmployeeData;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

import lombok.Data;

@Data
@Entity
@Table(name = "employee_events")
@TypeDefs({
    @TypeDef(name = "json", typeClass = JsonStringType.class)
})
public class EmployeeEvent {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "event_type")
    private String eventType;

    @Column(name= "source_id")
    private String sourceId;

    @Column(name = "created_at")
    private Long createdAt;
    
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private EmployeeData employeeData;

    @PrePersist
    private void prePersist(){
        this.createdAt = Instant.now().getEpochSecond();
    }

}
