package com.takeaway.eventservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.takeaway.eventservice.model.EmployeeEvent;

@Repository
public interface IEmployeeEventRepository extends JpaRepository<EmployeeEvent, String> {
    Optional<List<EmployeeEvent>> findAllBySourceIdOrderByCreatedAt(String id);
}
