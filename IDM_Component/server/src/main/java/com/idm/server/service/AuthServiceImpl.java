package com.idm.server.service;

import com.idm.server.db.InvalidToken;
import com.idm.server.db.InvalidTokenRepository;
import com.idm.server.db.User;
import com.idm.server.db.UserRepository;
import com.idm.server.proto.AuthServiceGrpc;
import com.idm.server.proto.Schema;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {

    private UserRepository userRepository = new UserRepository();
    private  JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    private InvalidTokenRepository invalidTokenRepository = new InvalidTokenRepository();

    @Override
    public void addUser(Schema.AddUserRequest request, StreamObserver<Schema.AddUserResponse> responseObserver) {
        String username = request.getUsername();
        String password = request.getPassword();
        String role = request.getRole();
        //int id=request.getIdUser();

        // Verifica daca utilizatorul exista deja
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            Schema.AddUserResponse response = Schema.AddUserResponse.newBuilder()
                    .setSuccess(false)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }

        User newUser = new User(username, password, role);

        try {
            User savedUser = userRepository.save(newUser);
            int id = savedUser.getUser_id();

            Schema.AddUserResponse response = Schema.AddUserResponse.newBuilder()
                    .setSuccess(true)
                    .setUsername(username)
                    .setIdUser(id)
                    .setRole(role)
                    .build();
            responseObserver.onNext(response);
        } catch (Exception e) {
            responseObserver.onError(new Exception("Eroare la adaugarea utilizatorului: " + e.getMessage()));
        } finally {
            responseObserver.onCompleted();
        }
    }
    @Override
    public void authenticate(Schema.JwtRequest request, StreamObserver<Schema.JwtToken> responseObserver) {
        String username = request.getUsername();
        String password = request.getPassword();

        System.out.println(username);
        User user = userRepository.findByUsername(username);

        System.out.println(user);
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (user != null && password.equals(user.getPassword())) {
            String token = jwtTokenUtil.generateToken(user);

            Schema.JwtToken jwtTokenResponse = Schema.JwtToken.newBuilder()
                    .setJwtToken(token)
                    .build();
            responseObserver.onNext(jwtTokenResponse);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new Exception("Autentificare esuata"));
        }
    }

    @Override
    public void validate(Schema.ValidateRequest request, StreamObserver<Schema.ValidateResponse> responseObserver) {
        String jwtToken = request.getJwtToken();

        InvalidToken existingToken = invalidTokenRepository.findByToken(jwtToken);
//        if(!jwtTokenUtil.validateToken(jwtToken)) //token expirat sau invalid
//        {
//            InvalidToken invalidToken=new InvalidToken(jwtToken,"");
//            invalidTokenRepository.save(invalidToken);
//        }
        if (jwtTokenUtil.validateToken(jwtToken) && existingToken==null) {
            String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            String role = jwtTokenUtil.getRoleFromToken(jwtToken);

            Schema.ValidateResponse validateResponse = Schema.ValidateResponse.newBuilder()
                    .setUsername(username)
                    .setRole(role)
                    .build();

            responseObserver.onNext(validateResponse);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new Exception("Token expirat sau invalid"));
        }
    }
    @Override
    public void logout(Schema.LogoutRequest request, StreamObserver<Schema.LogoutResponse> responseObserver) {
        String jwtToken = request.getJwtToken();
        InvalidToken existingToken= invalidTokenRepository.findByToken(jwtToken);
        if (existingToken != null) {
            Schema.LogoutResponse response = Schema.LogoutResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Eroare la logout")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }

        InvalidToken newInvalidToken = new InvalidToken(jwtToken,"");

        try {
            InvalidToken savedInvalidToken = invalidTokenRepository.save(newInvalidToken);
            Schema.LogoutResponse response = Schema.LogoutResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Logout reușit.")
                    .build();
            responseObserver.onNext(response);
        } catch (Exception e) {
            responseObserver.onError(new Exception("Eroare la adaugarea token-ului la invalidate: " + e.getMessage()));
        } finally {
            responseObserver.onCompleted();
        }
    }


}

