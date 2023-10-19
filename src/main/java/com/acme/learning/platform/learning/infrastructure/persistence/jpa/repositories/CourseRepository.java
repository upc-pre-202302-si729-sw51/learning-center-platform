package com.acme.learning.platform.learning.infrastructure.persistence.jpa.repositories;

import com.acme.learning.platform.learning.domain.model.aggregates.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
