package com.example.demo.service;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.exceptions.UserException;
import com.example.demo.external.AddressResolver;
import com.example.demo.mapper.UserMapper;
import com.example.demo.oth.Address;
import com.example.demo.repository.UserRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import retrofit2.Retrofit;
import io.jsonwebtoken.Jwts;


import java.security.Key;
import java.util.*;

@Slf4j
@Service
public class UserService {

    @Value("${jwt.secretkey}")
    private String SECRET;

    private final UserRepository userRepository;
    //private final RestTemplate restTemplate;
    private final UserMapper userMapper;
    private AddressResolver addressResolver;
    private Retrofit retrofit;

    @Autowired
    public UserService(Retrofit retrofit, UserMapper userMapper, RestTemplate restTemplate, UserRepository userRepository) {
        this.userRepository = userRepository;
        addressResolver = new AddressResolver(retrofit);
        this.userMapper = userMapper;
        //this.restTemplate = restTemplate;
    }

    public UserDTO createUser(UserDTO userDTO) throws Exception{

        logDetails(userDTO);

        List<User> users = userRepository.findByName(userDTO.getName()).orElseThrow(() -> new UserException("USER DOES NOT EXIST"));

        if(users.size() > 0){
            throw new UserException("MORE THAN ONE ENTRY FOUND :( , TRY DIFFERENT USERNAME");
        }

        //Address address = saveAddress(userDTO.getAddress());
        Address address = addressResolver.saveAddress(userDTO.getAddress());

        log.info("ADDRESS SAVED "+ address.getId());


        User user = userMapper.toDAO(userDTO);
        user.setAddressID(address.getId());
        User savedUser = userRepository.save(user);
        log.info("USER SAVED WITH ADDRESS" + savedUser.getAddressID());
        user.setId(savedUser.getId());

        return userMapper.toDTO(user, address);

    }

    public UserDTO getUser(UUID id) throws Exception{
        log.info("FETCHING USER DETAILS");
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserException("USER DOES NOT EXIST"));

        log.info("FETCHING ADDRESS DETAILS");
        //Address address = getAddress(existingUser.getAddressID());

        Address address = addressResolver.getAddress(existingUser.getAddressID());

        log.info("ADDRESS DETAILS FETCHED");
        return userMapper.toDTO(existingUser, address);
    }


    public AuthResponse fetchDetails(AuthRequest authRequest) throws UserException{

        List<User> users = userRepository.findByName(authRequest.getUsername()).orElseThrow(() -> new UserException("USER DOES NOT EXIST"));

        if(users.size() > 1){
            throw new UserException("MORE THAN ONE ENTRY FOUND :(");
        }

        if(users.get(0).getPassword().equals(authRequest.getPassword())){
            return new AuthResponse(generateToken(authRequest.getUsername()));
        }

        throw  new UserException("PASSWORD MISMATCH");
    }




    public String generateToken(String username){

        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*1200))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public Key getKey(){

        log.info("ACCESSING SECRET KEY "+ SECRET);

        byte[] keyBytes = Decoders.BASE64.decode(SECRET);

        return Keys.hmacShaKeyFor(keyBytes);
    }






    private void logDetails(UserDTO userDTO){
        log.info("USER DETAILS "+userDTO.getEmail() + "  "+ userDTO.getName());
        log.info("ADDRESS DETAILS "+ userDTO.getAddress().getStreet()+" " + userDTO.getAddress().getCity());
    }


















    // ------------------------------------- EXTERNAL SERVICE ACCESS USING REST TEMPLATE --------------------------------------- //

//    private Address saveAddress(Address address) {
//        log.info("SAVING USER LINKED ADDRESS");
//        Address savedAddress = restTemplate.postForObject("http://localhost:8081/api/v1/address", address, Address.class);
//        return  savedAddress;
//    }
//
//    private Address getAddress(UUID id){
//        log.info("ACCESSING ADDRESS SERVICE");
//        Address existingAddress = restTemplate.getForObject("http://localhost:8081/api/v1/address/"+id, Address.class);
//        return existingAddress;
//    }


}


