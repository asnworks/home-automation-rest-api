package com.teamtreehouse.room;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {

    @RestResource(rel = "room-name", path = "roomName")
    Page<Room> findByNameContaining(@Param("name") String name, Pageable page);

    @RestResource(rel = "room-area", path = "roomArea")
    Page<Room> findByAreaLessThan(@Param("area") int area, Pageable page);
}
