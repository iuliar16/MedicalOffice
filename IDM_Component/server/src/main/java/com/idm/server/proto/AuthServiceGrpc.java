package com.idm.server.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: schema.proto")
public final class AuthServiceGrpc {

  private AuthServiceGrpc() {}

  public static final String SERVICE_NAME = "AuthService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.idm.server.proto.Schema.JwtRequest,
      com.idm.server.proto.Schema.JwtToken> getAuthenticateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "authenticate",
      requestType = com.idm.server.proto.Schema.JwtRequest.class,
      responseType = com.idm.server.proto.Schema.JwtToken.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.idm.server.proto.Schema.JwtRequest,
      com.idm.server.proto.Schema.JwtToken> getAuthenticateMethod() {
    io.grpc.MethodDescriptor<com.idm.server.proto.Schema.JwtRequest, com.idm.server.proto.Schema.JwtToken> getAuthenticateMethod;
    if ((getAuthenticateMethod = AuthServiceGrpc.getAuthenticateMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getAuthenticateMethod = AuthServiceGrpc.getAuthenticateMethod) == null) {
          AuthServiceGrpc.getAuthenticateMethod = getAuthenticateMethod = 
              io.grpc.MethodDescriptor.<com.idm.server.proto.Schema.JwtRequest, com.idm.server.proto.Schema.JwtToken>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "AuthService", "authenticate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.idm.server.proto.Schema.JwtRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.idm.server.proto.Schema.JwtToken.getDefaultInstance()))
                  .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("authenticate"))
                  .build();
          }
        }
     }
     return getAuthenticateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.idm.server.proto.Schema.ValidateRequest,
      com.idm.server.proto.Schema.ValidateResponse> getValidateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "validate",
      requestType = com.idm.server.proto.Schema.ValidateRequest.class,
      responseType = com.idm.server.proto.Schema.ValidateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.idm.server.proto.Schema.ValidateRequest,
      com.idm.server.proto.Schema.ValidateResponse> getValidateMethod() {
    io.grpc.MethodDescriptor<com.idm.server.proto.Schema.ValidateRequest, com.idm.server.proto.Schema.ValidateResponse> getValidateMethod;
    if ((getValidateMethod = AuthServiceGrpc.getValidateMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getValidateMethod = AuthServiceGrpc.getValidateMethod) == null) {
          AuthServiceGrpc.getValidateMethod = getValidateMethod = 
              io.grpc.MethodDescriptor.<com.idm.server.proto.Schema.ValidateRequest, com.idm.server.proto.Schema.ValidateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "AuthService", "validate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.idm.server.proto.Schema.ValidateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.idm.server.proto.Schema.ValidateResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("validate"))
                  .build();
          }
        }
     }
     return getValidateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.idm.server.proto.Schema.AddUserRequest,
      com.idm.server.proto.Schema.AddUserResponse> getAddUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addUser",
      requestType = com.idm.server.proto.Schema.AddUserRequest.class,
      responseType = com.idm.server.proto.Schema.AddUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.idm.server.proto.Schema.AddUserRequest,
      com.idm.server.proto.Schema.AddUserResponse> getAddUserMethod() {
    io.grpc.MethodDescriptor<com.idm.server.proto.Schema.AddUserRequest, com.idm.server.proto.Schema.AddUserResponse> getAddUserMethod;
    if ((getAddUserMethod = AuthServiceGrpc.getAddUserMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getAddUserMethod = AuthServiceGrpc.getAddUserMethod) == null) {
          AuthServiceGrpc.getAddUserMethod = getAddUserMethod = 
              io.grpc.MethodDescriptor.<com.idm.server.proto.Schema.AddUserRequest, com.idm.server.proto.Schema.AddUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "AuthService", "addUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.idm.server.proto.Schema.AddUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.idm.server.proto.Schema.AddUserResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("addUser"))
                  .build();
          }
        }
     }
     return getAddUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.idm.server.proto.Schema.LogoutRequest,
      com.idm.server.proto.Schema.LogoutResponse> getLogoutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Logout",
      requestType = com.idm.server.proto.Schema.LogoutRequest.class,
      responseType = com.idm.server.proto.Schema.LogoutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.idm.server.proto.Schema.LogoutRequest,
      com.idm.server.proto.Schema.LogoutResponse> getLogoutMethod() {
    io.grpc.MethodDescriptor<com.idm.server.proto.Schema.LogoutRequest, com.idm.server.proto.Schema.LogoutResponse> getLogoutMethod;
    if ((getLogoutMethod = AuthServiceGrpc.getLogoutMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getLogoutMethod = AuthServiceGrpc.getLogoutMethod) == null) {
          AuthServiceGrpc.getLogoutMethod = getLogoutMethod = 
              io.grpc.MethodDescriptor.<com.idm.server.proto.Schema.LogoutRequest, com.idm.server.proto.Schema.LogoutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "AuthService", "Logout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.idm.server.proto.Schema.LogoutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.idm.server.proto.Schema.LogoutResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("Logout"))
                  .build();
          }
        }
     }
     return getLogoutMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuthServiceStub newStub(io.grpc.Channel channel) {
    return new AuthServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AuthServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuthServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AuthServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class AuthServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void authenticate(com.idm.server.proto.Schema.JwtRequest request,
        io.grpc.stub.StreamObserver<com.idm.server.proto.Schema.JwtToken> responseObserver) {
      asyncUnimplementedUnaryCall(getAuthenticateMethod(), responseObserver);
    }

    /**
     */
    public void validate(com.idm.server.proto.Schema.ValidateRequest request,
        io.grpc.stub.StreamObserver<com.idm.server.proto.Schema.ValidateResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getValidateMethod(), responseObserver);
    }

    /**
     */
    public void addUser(com.idm.server.proto.Schema.AddUserRequest request,
        io.grpc.stub.StreamObserver<com.idm.server.proto.Schema.AddUserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAddUserMethod(), responseObserver);
    }

    /**
     */
    public void logout(com.idm.server.proto.Schema.LogoutRequest request,
        io.grpc.stub.StreamObserver<com.idm.server.proto.Schema.LogoutResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLogoutMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAuthenticateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.idm.server.proto.Schema.JwtRequest,
                com.idm.server.proto.Schema.JwtToken>(
                  this, METHODID_AUTHENTICATE)))
          .addMethod(
            getValidateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.idm.server.proto.Schema.ValidateRequest,
                com.idm.server.proto.Schema.ValidateResponse>(
                  this, METHODID_VALIDATE)))
          .addMethod(
            getAddUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.idm.server.proto.Schema.AddUserRequest,
                com.idm.server.proto.Schema.AddUserResponse>(
                  this, METHODID_ADD_USER)))
          .addMethod(
            getLogoutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.idm.server.proto.Schema.LogoutRequest,
                com.idm.server.proto.Schema.LogoutResponse>(
                  this, METHODID_LOGOUT)))
          .build();
    }
  }

  /**
   */
  public static final class AuthServiceStub extends io.grpc.stub.AbstractStub<AuthServiceStub> {
    private AuthServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthServiceStub(channel, callOptions);
    }

    /**
     */
    public void authenticate(com.idm.server.proto.Schema.JwtRequest request,
        io.grpc.stub.StreamObserver<com.idm.server.proto.Schema.JwtToken> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAuthenticateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void validate(com.idm.server.proto.Schema.ValidateRequest request,
        io.grpc.stub.StreamObserver<com.idm.server.proto.Schema.ValidateResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValidateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addUser(com.idm.server.proto.Schema.AddUserRequest request,
        io.grpc.stub.StreamObserver<com.idm.server.proto.Schema.AddUserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void logout(com.idm.server.proto.Schema.LogoutRequest request,
        io.grpc.stub.StreamObserver<com.idm.server.proto.Schema.LogoutResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AuthServiceBlockingStub extends io.grpc.stub.AbstractStub<AuthServiceBlockingStub> {
    private AuthServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.idm.server.proto.Schema.JwtToken authenticate(com.idm.server.proto.Schema.JwtRequest request) {
      return blockingUnaryCall(
          getChannel(), getAuthenticateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.idm.server.proto.Schema.ValidateResponse validate(com.idm.server.proto.Schema.ValidateRequest request) {
      return blockingUnaryCall(
          getChannel(), getValidateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.idm.server.proto.Schema.AddUserResponse addUser(com.idm.server.proto.Schema.AddUserRequest request) {
      return blockingUnaryCall(
          getChannel(), getAddUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.idm.server.proto.Schema.LogoutResponse logout(com.idm.server.proto.Schema.LogoutRequest request) {
      return blockingUnaryCall(
          getChannel(), getLogoutMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AuthServiceFutureStub extends io.grpc.stub.AbstractStub<AuthServiceFutureStub> {
    private AuthServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.idm.server.proto.Schema.JwtToken> authenticate(
        com.idm.server.proto.Schema.JwtRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAuthenticateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.idm.server.proto.Schema.ValidateResponse> validate(
        com.idm.server.proto.Schema.ValidateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getValidateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.idm.server.proto.Schema.AddUserResponse> addUser(
        com.idm.server.proto.Schema.AddUserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAddUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.idm.server.proto.Schema.LogoutResponse> logout(
        com.idm.server.proto.Schema.LogoutRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_AUTHENTICATE = 0;
  private static final int METHODID_VALIDATE = 1;
  private static final int METHODID_ADD_USER = 2;
  private static final int METHODID_LOGOUT = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AuthServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AuthServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AUTHENTICATE:
          serviceImpl.authenticate((com.idm.server.proto.Schema.JwtRequest) request,
              (io.grpc.stub.StreamObserver<com.idm.server.proto.Schema.JwtToken>) responseObserver);
          break;
        case METHODID_VALIDATE:
          serviceImpl.validate((com.idm.server.proto.Schema.ValidateRequest) request,
              (io.grpc.stub.StreamObserver<com.idm.server.proto.Schema.ValidateResponse>) responseObserver);
          break;
        case METHODID_ADD_USER:
          serviceImpl.addUser((com.idm.server.proto.Schema.AddUserRequest) request,
              (io.grpc.stub.StreamObserver<com.idm.server.proto.Schema.AddUserResponse>) responseObserver);
          break;
        case METHODID_LOGOUT:
          serviceImpl.logout((com.idm.server.proto.Schema.LogoutRequest) request,
              (io.grpc.stub.StreamObserver<com.idm.server.proto.Schema.LogoutResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AuthServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.idm.server.proto.Schema.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AuthService");
    }
  }

  private static final class AuthServiceFileDescriptorSupplier
      extends AuthServiceBaseDescriptorSupplier {
    AuthServiceFileDescriptorSupplier() {}
  }

  private static final class AuthServiceMethodDescriptorSupplier
      extends AuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AuthServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AuthServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuthServiceFileDescriptorSupplier())
              .addMethod(getAuthenticateMethod())
              .addMethod(getValidateMethod())
              .addMethod(getAddUserMethod())
              .addMethod(getLogoutMethod())
              .build();
        }
      }
    }
    return result;
  }
}