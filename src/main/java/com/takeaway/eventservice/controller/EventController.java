package com.takeaway.eventservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takeaway.eventservice.helper.TakeawayHttpResponse;
import com.takeaway.eventservice.model.EmployeeEvent;
import com.takeaway.eventservice.repository.IEmployeeEventRepository;
import com.takeaway.eventservice.util.Constants;

@RestController
@RequestMapping(Constants.EVENTS_BASE_URL)
public class EventController {
	
	@Autowired
    private IEmployeeEventRepository employeeEventRepository;

    @GetMapping(Constants.ID_URL)
    public TakeawayHttpResponse getEmployeeEvents(@PathVariable("id") String id) {
       Optional<List<EmployeeEvent>> employeeEventsOptional = employeeEventRepository.
    		   findAllBySourceIdOrderByCreatedAt(id);
       if(employeeEventsOptional.isPresent()) {
    	   return TakeawayHttpResponse.success(Constants.EVENTS_FOUND_MESSAGE, HttpStatus.OK, 
    			   employeeEventsOptional.get());
       } else {
    	   return TakeawayHttpResponse.failure(Constants.EVENTS_NOT_FOUND_MESSAGE, HttpStatus.BAD_REQUEST);
       }
    }
	

}
