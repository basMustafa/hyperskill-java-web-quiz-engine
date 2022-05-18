package engine.controller;

import engine.dto.UserDTO;
import engine.mapper.ModelMapper;
import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
public class AuthController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/api/register")
    public void registerUser(@Valid @RequestBody UserDTO userDTO) {
        userService.saveUser(modelMapper.mapToEntity(userDTO));
    }
}
