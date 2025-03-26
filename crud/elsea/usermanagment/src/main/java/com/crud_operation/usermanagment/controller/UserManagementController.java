package com.crud_operation.usermanagment.controller;

import com.crud_operation.usermanagment.dto.ReqRes;
import com.crud_operation.usermanagment.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    /**
     * Register a new user.
     *
     * @param registrationRequest The registration request containing user details.
     * @return A response indicating the result of the registration.
     */
    @PostMapping("/auth/register")
    public ReqRes registerUser(@RequestBody ReqRes registrationRequest) {
        return userManagementService.register(registrationRequest);
    }
    @GetMapping("/public/test")
    public String publicTest() {
        return "This is a public endpoint";
    }
    /**
     * Authenticate a user and generate a JWT token.
     *
     * @param loginRequest The login request containing email and password.
     * @return A response containing the JWT token and refresh token.
     */
    @PostMapping("/auth/login")
    public ReqRes loginUser(@RequestBody ReqRes loginRequest) {
        return userManagementService.login(loginRequest);
    }

    /**
     * Refresh a JWT token using a valid refresh token.
     *
     * @param refreshTokenRequest The refresh token request containing the expired token.
     * @return A response containing the new JWT token.
     */
    @PostMapping("/auth/refresh")
    public ReqRes refreshToken(@RequestBody ReqRes refreshTokenRequest) {
        return userManagementService.refreshToken(refreshTokenRequest);
    }

    /**
     * Retrieve all users.
     *
     * @return A response containing the list of all users.
     */
    @GetMapping("/admin/get-all-users")
    public ReqRes getAllUsers() {
        return userManagementService.findAllUsers();
    }

    /**
     * Retrieve a user by ID.
     *
     * @param id The ID of the user.
     * @return A response containing the user details.
     */
    @GetMapping("/admin/get-user/{id}")
    public ReqRes getUserById(@PathVariable Long id) {
        return userManagementService.findUserById(id);
    }

    /**
     * Delete a user by ID.
     *
     * @param id The ID of the user to delete.
     * @return A response indicating the result of the deletion.
     */
    @DeleteMapping("/admin/delete/{id}")
    public ReqRes deleteUser(@PathVariable Long id) {
        return userManagementService.deleteUser(id);
    }

    /**
     * Update an existing user by ID.
     *
     * @param id            The ID of the user to update.
     * @param updateRequest The update request containing the new user details.
     * @return A response containing the updated user details.
     */
    @PutMapping("/admin/update/{id}")
    public ReqRes updateUser(@PathVariable Long id, @RequestBody ReqRes updateRequest) {
        return userManagementService.updateUser(id, updateRequest);
    }
    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProrile(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();
        ReqRes response=userManagementService.getMyInfo(email);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/adminuser/update-profile")
    public ReqRes updateProrile(@RequestBody ReqRes updateRequest) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();
        return userManagementService.updateMyInfo(email, updateRequest);
    }
}