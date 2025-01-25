package com.example.app.repository;

import com.example.app.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    // got error
//    @EntityGraph(attributePaths = {"formFields", "serviceItems"})
//    @Query("SELECT s FROM ServiceEntity s JOIN FETCH s.formFields JOIN FETCH s.serviceItems")
//    List<ServiceEntity> findAll();

    ServiceEntity findByCategory(String category);
}
