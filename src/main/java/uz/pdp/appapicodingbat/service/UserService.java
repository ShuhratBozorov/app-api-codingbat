package uz.pdp.appapicodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appapicodingbat.entity.User;
import uz.pdp.appapicodingbat.payload.ApiResponse;
import uz.pdp.appapicodingbat.payload.UserDto;
import uz.pdp.appapicodingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public ApiResponse addUser(UserDto userDto) {
        boolean exists = userRepository.existsByEmail(userDto.getEmail());
        if (exists) {
            return new ApiResponse("Bunday user mavjud", false);
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new ApiResponse("User saved", true);
    }

    public ApiResponse editUser(UserDto userDto, Integer id) {
        boolean exists = userRepository.existsByEmail(userDto.getEmail());
        if (exists) {
            return new ApiResponse("Bunday email mavjud", false);
        }

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("Bunday user mavjud emas", false);

        User user = optionalUser.get();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new ApiResponse("User edited", true);
    }

    public ApiResponse deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
            return new ApiResponse("User deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }
}
