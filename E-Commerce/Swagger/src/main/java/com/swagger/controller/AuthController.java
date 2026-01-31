package com.swagger.controller;


import com.swagger.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Authentication API'", description = "Authentication related apis")
public class AuthController {

    @Operation(summary = "Login and get the details")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Successfully authenticated user"),
                    @ApiResponse(responseCode = "404", description = "Invalid username or password")
            }
    )
    @PostMapping("/auth")
    public String login(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        return "User logged in";
    }


}
