package com.assignment.car.lease.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.assignment.car.lease.bean.LoginRequest;
import com.assignment.car.lease.bean.MessageResponse;
import com.assignment.car.lease.bean.SignupRequest;
import com.assignment.car.lease.bean.UserInfoResponse;
import com.assignment.car.lease.entity.ERole;
import com.assignment.car.lease.entity.Role;
import com.assignment.car.lease.entity.User;
import com.assignment.car.lease.repository.RoleRepository;
import com.assignment.car.lease.repository.UserRepository;
import com.assignment.car.lease.security.jwt.JwtUtils;
import com.assignment.car.lease.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Controller to provide authorization and authentication functionality within the application */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  public static final String ERROR_ROLE_IS_NOT_FOUND = "Error: Role is not found.";
  @Autowired AuthenticationManager authenticationManager;
  @Autowired UserRepository userRepository;
  @Autowired RoleRepository roleRepository;
  @Autowired PasswordEncoder encoder;
  @Autowired JwtUtils jwtUtils;

  /**
   * Method to signin
   *
   * @param loginRequest
   * @return
   */
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
    List<String> roles =
        userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());
    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
        .body(
            new UserInfoResponse(
                userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
  }

  /**
   * Method to signup
   *
   * @param signUpRequest
   * @return
   */
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }
    // Create new user's account
    User user =
        new User(
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));
    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();
    if (strRoles == null) {
      Role userRole =
          roleRepository
              .findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException(ERROR_ROLE_IS_NOT_FOUND));
      roles.add(userRole);
    } else {
      strRoles.forEach(
          role -> {
            switch (role) {
              case "admin":
                Role adminRole =
                    roleRepository
                        .findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException(ERROR_ROLE_IS_NOT_FOUND));
                roles.add(adminRole);
                break;
              case "mod":
                Role modRole =
                    roleRepository
                        .findByName(ERole.ROLE_MODERATOR)
                        .orElseThrow(() -> new RuntimeException(ERROR_ROLE_IS_NOT_FOUND));
                roles.add(modRole);
                break;
              default:
                Role userRole =
                    roleRepository
                        .findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException(ERROR_ROLE_IS_NOT_FOUND));
                roles.add(userRole);
            }
          });
    }
    user.setRoles(roles);
    userRepository.save(user);
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  /**
   * Method to signout
   *
   * @return
   */
  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("You've been signed out!"));
  }
}
