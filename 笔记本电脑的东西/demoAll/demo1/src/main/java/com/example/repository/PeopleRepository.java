package com.example.repository;

import com.example.bean.People;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hhr
 * @date 2020-06-18 09:28
 * @description:
 */

public interface PeopleRepository extends JpaRepository<People,Integer> {

}