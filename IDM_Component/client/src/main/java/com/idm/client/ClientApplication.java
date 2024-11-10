package com.idm.client;

import com.idm.client.proto.AuthServiceGrpc;
import com.idm.client.proto.Schema;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.User;

import java.util.Scanner;


@SpringBootApplication
public class ClientApplication {
    private static void addUser(AuthServiceGrpc.AuthServiceBlockingStub stub, Scanner scanner){
        System.out.print("Nume user: ");
        String username = scanner.nextLine();
        System.out.print("Parola: ");
        String password = scanner.nextLine();
        System.out.print("Rol: ");
        String role = scanner.nextLine();

        Schema.AddUserRequest user = Schema.AddUserRequest.newBuilder()
                .setRole(role)
                .setUsername(username)
                .setPassword(password)
                .build();

        Schema.AddUserResponse response = stub.addUser(user);
        System.out.println("Utilizator creat cu ID: " + response.getIdUser());
    }
    private static void authenticate(AuthServiceGrpc.AuthServiceBlockingStub stub, Scanner scanner){
        System.out.print("Nume utilizator pentru autentificare: ");
        String username = scanner.nextLine();
        System.out.print("Parola: ");
        String password = scanner.nextLine();

        Schema.JwtRequest request = Schema.JwtRequest.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();

        try {
            Schema.JwtToken response = stub.authenticate(request);
            System.out.println("Token JWT: " + response.getJwtToken());
            validate(stub, response.getJwtToken());
        } catch (Exception e) {
            System.out.println("Eroare la autentificare: " + e.getMessage());
        }
    }
    private static void validate(AuthServiceGrpc.AuthServiceBlockingStub stub, String token){
        Schema.ValidateRequest request = Schema.ValidateRequest.newBuilder()
                .setJwtToken(token)
                .build();

        try {
            Schema.ValidateResponse response = stub.validate(request);
            System.out.println("Utilizator cu id-ul "+  response.getUsername() + " validat cu succes");
        } catch (Exception e) {
            System.out.println("Eroare la validare: " + e.getMessage());
        }
    }
    private static void logout(AuthServiceGrpc.AuthServiceBlockingStub stub, String token){
        System.out.println("logout");
        Schema.LogoutRequest request = Schema.LogoutRequest.newBuilder()
                .setJwtToken(token)
                .build();

        try {
            Schema.LogoutResponse response = stub.logout(request);
            System.out.println("Token invalidat!");
        } catch (Exception e) {
            System.out.println("Eroare la invalidare: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();

        AuthServiceGrpc.AuthServiceBlockingStub userStub = AuthServiceGrpc.newBlockingStub(channel);
        Scanner scanner = new Scanner(System.in);

        // addUser(userStub, scanner);
        //authenticate(userStub, scanner);

        authenticate(userStub, scanner);
        System.out.print("token: ");
        String token = scanner.nextLine();
        logout(userStub,token);

        channel.shutdownNow();
    }

}
