package com.teamtreehouse.control;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

public interface ControlRepository extends PagingAndSortingRepository<Control, Long> {

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or " + "#control.device.room.hasAdministrator(authentication.principal)")
    Control save(@Param("control") Control control);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or " + "#control.device.room.hasAdministrator(authentication.principal)")
    void delete(Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or " + "#control.device.room.hasAdministrator(authentication.principal)")
    void delete(Control entity);
}
