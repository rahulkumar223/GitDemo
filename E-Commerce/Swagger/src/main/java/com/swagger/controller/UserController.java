package com.swagger.controller;

import com.swagger.dto.UserDTO;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "User management API's", description = "These apis are used to manage the users ")
public class UserController {

    @Operation(summary = "User get home endpoint", description = " description")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Server issue"),
    })

    @Hidden
    @GetMapping("/home")
    public Map<String, String> getHome() {
        Map<String, String> map = new HashMap<>();

        map.put("Status", "Running");
        map.put("Endpoint ", "Home get method");

        return map;
    }

    @Operation(summary = "Get user  endpoint", description = " description")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Server issue"),
    })
    @GetMapping("/user/{id}")
    public UserDTO getUser(@PathVariable int id) {


        return new UserDTO(id, "john", "john@gmail.com");
    }

    @Operation(summary = "Create user  endpoint", description = " description")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Server issue"),

    })
    @PostMapping("/user")
    public String createUser(@RequestBody UserDTO userDTO) {

        System.out.println(userDTO.getName() + "  " + userDTO.getEmail());
        return "user saved ";
    }

}
