package com.takeaway.eventservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.takeaway.eventservice.model.EmployeeEvent;
import com.takeaway.eventservice.repository.IEmployeeEventRepository;
import com.takeaway.eventservice.util.Constants;

@Service
public class KafkaConsumerService {
	
    @Autowired
    private IEmployeeEventRepository employeeEventRepository;

    @KafkaListener(topics = Constants.EMPLOYEE_EVENTS_TOPIC)
    public void messageListener(com.takeaway.eventservice.kafka.events.EmployeeEvent value) throws JsonProcessingException {
        employeeEventRepository.save(convertKafkaEventToEmployeeEvent(value));
    }

    private EmployeeEvent convertKafkaEventToEmployeeEvent(com.takeaway.eventservice.kafka.events.EmployeeEvent event) throws JsonProcessingException {
        EmployeeEvent entity = new EmployeeEvent();
        entity.setSourceId(event.getSourceId());
        entity.setEventType(event.getEventType().toString());
        entity.setEmployeeData(event.getData());
        return entity;
    }

}
