package io.isott.expertise.infra.adapter.expertise.jpa.repository;

import io.isott.expertise.infra.adapter.expertise.jpa.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionJpaRepository extends JpaRepository<QuestionEntity, Long> {
}
