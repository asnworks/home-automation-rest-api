package com.teamtreehouse.device;

import com.teamtreehouse.user.User;
import com.teamtreehouse.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Device.class)
public class DeviceEventHandler {
    private final UserRepository users;

    @Autowired
    public DeviceEventHandler(UserRepository users) {
        this.users = users;
    }

    @HandleBeforeCreate
    public void addDeviceBasedOnLoggedInUser(Device device) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByName(name);
        if (!device.getRoom().getAdministrators().contains(user)) {
            throw new AccessDeniedException("Access Denied - user must be a room administrator in order to create controls");
        }
    }

    @HandleBeforeSave
    public void saveDeviceBasedOnLoggedInUser(Device device) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByName(name);
        if (!device.getRoom().getAdministrators().contains(user)) {
            throw new AccessDeniedException("Access Denied - user must be a room administrator in order to modify devices");
        }
    }

    @HandleBeforeDelete
    public void deleteDeviceBasedOnLoggedInUser(Device device) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByName(name);
        if (!device.getRoom().getAdministrators().contains(user)) {
            throw new AccessDeniedException("Access Denied - user must be a room administrator in order to delete devices");
        }
    }
}
