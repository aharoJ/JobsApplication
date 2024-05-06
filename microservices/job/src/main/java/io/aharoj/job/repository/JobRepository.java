package io.aharoj.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.aharoj.job.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

}
