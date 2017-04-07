package com.teamtreehouse.room;

import com.teamtreehouse.device.Device;
import com.teamtreehouse.user.User;
import com.teamtreehouse.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Room.class)
public class RoomEventHandler {
    private final UserRepository users;

    @Autowired
    public RoomEventHandler(UserRepository users) {
        this.users = users;
    }

    @HandleBeforeCreate
    public void addRoomBasedOnLoggedInUser(Room room) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByName(name);
        if (user.hasAdminRole()) {
            room.addAdministrator(user);
        } else {
            throw new AccessDeniedException("Access Denied - user must have the ROLE_ADMIN role in order to create rooms");
        }
    }

    @HandleBeforeCreate
    public void addDeviceBasedOnLoggedInUser(Room room, Device device) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByName(name);
        if (room.getAdministrators().contains(user)) {
            room.addDevice(device);
        } else {
            throw new AccessDeniedException("Access Denied - user must be a room administrator in order to create devices");
        }
    }
}
