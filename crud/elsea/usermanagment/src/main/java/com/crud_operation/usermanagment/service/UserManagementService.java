package com.crud_operation.usermanagment.service;

import com.crud_operation.usermanagment.dto.ReqRes;
import com.crud_operation.usermanagment.entity.OurUsers;
import com.crud_operation.usermanagment.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserManagementService {
    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ReqRes register(ReqRes registrationRequest) {
        ReqRes reqRes = new ReqRes();
        try {
            OurUsers ourUser = new OurUsers();
            ourUser.setEmail(registrationRequest.getEmail());
            ourUser.setCity(registrationRequest.getCity());
            ourUser.setRole(registrationRequest.getRole());
            ourUser.setName(registrationRequest.getName());
            ourUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword())); // Encode the password
            OurUsers ourUsersResult = usersRepo.save(ourUser);
            if (ourUsersResult.getId() > 0) {
                reqRes.setOurUser(ourUsersResult);
                reqRes.setMessage("User Saved Successfully");
                reqRes.setStatus(200);
            }
        } catch (Exception e) {
            reqRes.setError(e.getMessage());
            reqRes.setStatus(500);
        }
        return reqRes;
    }

    public ReqRes login(ReqRes loginRequest) {
        ReqRes response = new ReqRes();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            var user = usersRepo.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

            response.setStatus(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setExpirationTime("24hrs");
            response.setRefreshedToken(refreshToken);
            response.setMessage("Logged in successfully");
        } catch (Exception e) {
            response.setStatus(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenRequest) {
        ReqRes response = new ReqRes();
        try {
            String email = jwtUtils.extractUsername(refreshTokenRequest.getToken());
            OurUsers users = usersRepo.findByEmail(email).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), users)) {
                var jwt = jwtUtils.generateToken(users);
                response.setStatus(200);
                response.setToken(jwt);
                response.setRefreshedToken(refreshTokenRequest.getToken());
                response.setExpirationTime("24Hrs");
                response.setMessage("Token refreshed successfully");
            }
            response.setStatus(200);
            return response;
        } catch (Exception e) {
            response.setStatus(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    // Find all users
    public ReqRes findAllUsers() {
        ReqRes reqRes = new ReqRes();
        try {
            List<OurUsers> users = usersRepo.findAll();
            reqRes.setOurUserLists(users);
            reqRes.setMessage("Users retrieved successfully");
            reqRes.setStatus(200);
        } catch (Exception e) {
            reqRes.setError(e.getMessage());
            reqRes.setStatus(500);
        }
        return reqRes;
    }

    // Find user by ID
    public ReqRes findUserById(Long id) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<OurUsers> user = usersRepo.findById(id);
            if (user.isPresent()) {
                reqRes.setOurUser(user.get());
                reqRes.setMessage("User retrieved successfully");
                reqRes.setStatus(200);
            } else {
                reqRes.setMessage("User not found");
                reqRes.setStatus(400);
            }
        } catch (Exception e) {
            reqRes.setError(e.getMessage());
            reqRes.setStatus(500);
        }
        return reqRes;
    }

    // Delete user by ID
    public ReqRes deleteUser(Long id) {
        ReqRes reqRes = new ReqRes();
        try {
            if (usersRepo.existsById(id)) {
                usersRepo.deleteById(id);
                reqRes.setMessage("User deleted successfully");
                reqRes.setStatus(200);
            } else {
                reqRes.setMessage("User not found");
                reqRes.setStatus(400);
            }
        } catch (Exception e) {
            reqRes.setError(e.getMessage());
            reqRes.setStatus(500);
        }
        return reqRes;
    }

    // Update user by ID
    public ReqRes updateUser(Long id, ReqRes updateRequest) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<OurUsers> userOptional = usersRepo.findById(id);
            if (userOptional.isPresent()) {
                //Add uapdated valus to database
                OurUsers user = userOptional.get();
                user.setEmail(updateRequest.getEmail());
                user.setCity(updateRequest.getCity());
                user.setRole(updateRequest.getRole());
                user.setName(updateRequest.getName());
              //Add Updated Responses
                OurUsers updatedUser = usersRepo.save(user);
                reqRes.setOurUser(updatedUser);
                reqRes.setMessage("User updated successfully");
                reqRes.setStatus(200);
            } else {
                reqRes.setMessage("User not found");
                reqRes.setStatus(404);
            }
        } catch (Exception e) {
            reqRes.setError(e.getMessage());
            reqRes.setStatus(500);
        }
        return reqRes;
    }
    public ReqRes getMyInfo(String email) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<OurUsers> user = usersRepo.findByEmail(email);
            if (user.isPresent()) {
                reqRes.setOurUser(user.get());
                reqRes.setMessage("User retrieved successfully");
                reqRes.setStatus(200);
            } else {
                reqRes.setMessage("User not found");
                reqRes.setStatus(400);
            }
        } catch (Exception e) {
            reqRes.setError(e.getMessage());
            reqRes.setStatus(500);
        }
        return reqRes;
    }
    public ReqRes updateMyInfo(String email, ReqRes updateRequest) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<OurUsers> userOptional = usersRepo.findByEmail(email);
            if (userOptional.isPresent()) {
                //Add uapdated valus to database
                OurUsers user = userOptional.get();
                user.setEmail(updateRequest.getEmail());
                user.setCity(updateRequest.getCity());
                user.setName(updateRequest.getName());
                //Add Updated Responses
                OurUsers updatedUser = usersRepo.save(user);
                reqRes.setOurUser(updatedUser);
                reqRes.setMessage("Profile updated successfully");
                reqRes.setStatus(200);
            } else {
                reqRes.setMessage("User not found");
                reqRes.setStatus(404);
            }
        } catch (Exception e) {
            reqRes.setError(e.getMessage());
            reqRes.setStatus(500);
        }
        return reqRes;
    }
}