package ie.sean.password;

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
    value = "by gRPC proto compiler (version 1.23.0)",
    comments = "Source: passwordService.proto")
public final class passwordServiceGrpc {

  private passwordServiceGrpc() {}

  public static final String SERVICE_NAME = "passwordService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ie.sean.password.PasswordService.Credentials,
      ie.sean.password.PasswordService.HashResponse> getHashMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "hash",
      requestType = ie.sean.password.PasswordService.Credentials.class,
      responseType = ie.sean.password.PasswordService.HashResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ie.sean.password.PasswordService.Credentials,
      ie.sean.password.PasswordService.HashResponse> getHashMethod() {
    io.grpc.MethodDescriptor<ie.sean.password.PasswordService.Credentials, ie.sean.password.PasswordService.HashResponse> getHashMethod;
    if ((getHashMethod = passwordServiceGrpc.getHashMethod) == null) {
      synchronized (passwordServiceGrpc.class) {
        if ((getHashMethod = passwordServiceGrpc.getHashMethod) == null) {
          passwordServiceGrpc.getHashMethod = getHashMethod =
              io.grpc.MethodDescriptor.<ie.sean.password.PasswordService.Credentials, ie.sean.password.PasswordService.HashResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "hash"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ie.sean.password.PasswordService.Credentials.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ie.sean.password.PasswordService.HashResponse.getDefaultInstance()))
              .setSchemaDescriptor(new passwordServiceMethodDescriptorSupplier("hash"))
              .build();
        }
      }
    }
    return getHashMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ie.sean.password.PasswordService.Compare,
      com.google.protobuf.BoolValue> getValidateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "validate",
      requestType = ie.sean.password.PasswordService.Compare.class,
      responseType = com.google.protobuf.BoolValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ie.sean.password.PasswordService.Compare,
      com.google.protobuf.BoolValue> getValidateMethod() {
    io.grpc.MethodDescriptor<ie.sean.password.PasswordService.Compare, com.google.protobuf.BoolValue> getValidateMethod;
    if ((getValidateMethod = passwordServiceGrpc.getValidateMethod) == null) {
      synchronized (passwordServiceGrpc.class) {
        if ((getValidateMethod = passwordServiceGrpc.getValidateMethod) == null) {
          passwordServiceGrpc.getValidateMethod = getValidateMethod =
              io.grpc.MethodDescriptor.<ie.sean.password.PasswordService.Compare, com.google.protobuf.BoolValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "validate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ie.sean.password.PasswordService.Compare.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.BoolValue.getDefaultInstance()))
              .setSchemaDescriptor(new passwordServiceMethodDescriptorSupplier("validate"))
              .build();
        }
      }
    }
    return getValidateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static passwordServiceStub newStub(io.grpc.Channel channel) {
    return new passwordServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static passwordServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new passwordServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static passwordServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new passwordServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class passwordServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Hash Method - Hashes salts password then returns it
     * </pre>
     */
    public void hash(ie.sean.password.PasswordService.Credentials request,
        io.grpc.stub.StreamObserver<ie.sean.password.PasswordService.HashResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHashMethod(), responseObserver);
    }

    /**
     * <pre>
     * Validate method - Returns true if the passwords matches
     * </pre>
     */
    public void validate(ie.sean.password.PasswordService.Compare request,
        io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue> responseObserver) {
      asyncUnimplementedUnaryCall(getValidateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getHashMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ie.sean.password.PasswordService.Credentials,
                ie.sean.password.PasswordService.HashResponse>(
                  this, METHODID_HASH)))
          .addMethod(
            getValidateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ie.sean.password.PasswordService.Compare,
                com.google.protobuf.BoolValue>(
                  this, METHODID_VALIDATE)))
          .build();
    }
  }

  /**
   */
  public static final class passwordServiceStub extends io.grpc.stub.AbstractStub<passwordServiceStub> {
    private passwordServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private passwordServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected passwordServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new passwordServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Hash Method - Hashes salts password then returns it
     * </pre>
     */
    public void hash(ie.sean.password.PasswordService.Credentials request,
        io.grpc.stub.StreamObserver<ie.sean.password.PasswordService.HashResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHashMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Validate method - Returns true if the passwords matches
     * </pre>
     */
    public void validate(ie.sean.password.PasswordService.Compare request,
        io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValidateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class passwordServiceBlockingStub extends io.grpc.stub.AbstractStub<passwordServiceBlockingStub> {
    private passwordServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private passwordServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected passwordServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new passwordServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Hash Method - Hashes salts password then returns it
     * </pre>
     */
    public ie.sean.password.PasswordService.HashResponse hash(ie.sean.password.PasswordService.Credentials request) {
      return blockingUnaryCall(
          getChannel(), getHashMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Validate method - Returns true if the passwords matches
     * </pre>
     */
    public com.google.protobuf.BoolValue validate(ie.sean.password.PasswordService.Compare request) {
      return blockingUnaryCall(
          getChannel(), getValidateMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class passwordServiceFutureStub extends io.grpc.stub.AbstractStub<passwordServiceFutureStub> {
    private passwordServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private passwordServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected passwordServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new passwordServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Hash Method - Hashes salts password then returns it
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ie.sean.password.PasswordService.HashResponse> hash(
        ie.sean.password.PasswordService.Credentials request) {
      return futureUnaryCall(
          getChannel().newCall(getHashMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Validate method - Returns true if the passwords matches
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.BoolValue> validate(
        ie.sean.password.PasswordService.Compare request) {
      return futureUnaryCall(
          getChannel().newCall(getValidateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HASH = 0;
  private static final int METHODID_VALIDATE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final passwordServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(passwordServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HASH:
          serviceImpl.hash((ie.sean.password.PasswordService.Credentials) request,
              (io.grpc.stub.StreamObserver<ie.sean.password.PasswordService.HashResponse>) responseObserver);
          break;
        case METHODID_VALIDATE:
          serviceImpl.validate((ie.sean.password.PasswordService.Compare) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.BoolValue>) responseObserver);
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

  private static abstract class passwordServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    passwordServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ie.sean.password.PasswordService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("passwordService");
    }
  }

  private static final class passwordServiceFileDescriptorSupplier
      extends passwordServiceBaseDescriptorSupplier {
    passwordServiceFileDescriptorSupplier() {}
  }

  private static final class passwordServiceMethodDescriptorSupplier
      extends passwordServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    passwordServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (passwordServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new passwordServiceFileDescriptorSupplier())
              .addMethod(getHashMethod())
              .addMethod(getValidateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
