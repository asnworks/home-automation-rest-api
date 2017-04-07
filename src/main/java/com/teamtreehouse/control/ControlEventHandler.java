package com.teamtreehouse.control;

import com.teamtreehouse.room.Room;
import com.teamtreehouse.user.User;
import com.teamtreehouse.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(User.class)
public class ControlEventHandler {
    private final UserRepository users;
    private final ControlRepository controls;

    @Autowired
    public ControlEventHandler(UserRepository users, ControlRepository controls) {
        this.users = users;
        this.controls = controls;
    }

    @HandleBeforeSave
    public void saveControlBasedOnLoggedInUser(Control control, Room room) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByName(name);
        if (room.getAdministrators().contains(user)) {
            controls.save(control);
        } else {
            throw new AccessDeniedException("Access Denied - user must be a room administrator in order to modify controls");
        }
    }

    @HandleAfterSave
    public void setLastModifiedAfterSave(Control control) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByName(name);
        control.setLastModifiedBy(user);
    }

    @HandleBeforeDelete
    public void deleteControlBasedOnLoggedInUser(Control control, Room room) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByName(name);
        if (room.getAdministrators().contains(user)) {
            controls.delete(control);
        } else {
            throw new AccessDeniedException("Access Denied - user must be a room administrator in order to delete controls");
        }
    }
}
