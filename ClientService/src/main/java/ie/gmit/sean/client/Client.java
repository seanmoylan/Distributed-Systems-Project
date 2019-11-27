package ie.gmit.sean.client;

import com.google.protobuf.ByteString;
import ie.gmit.sean.api.User;
import ie.gmit.sean.database.UserDB;
import ie.sean.password.PasswordServiceGrpc;
import ie.sean.password.PasswordServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class Client {
    private final ManagedChannel channel;
    private final PasswordServiceGrpc.PasswordServiceBlockingStub syncPasswordService;
    private final PasswordServiceGrpc.PasswordServiceStub asyncPasswordService;

    private static final Logger logger = Logger.getLogger(Client.class.getName());

    public Client(String host, int port) {
        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        asyncPasswordService = PasswordServiceGrpc.newStub(channel);
        syncPasswordService = PasswordServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException{
        channel.shutdown().awaitTermination(4, TimeUnit.SECONDS);
    }

    public void Hash(User user){
        StreamObserver<PasswordServiceOuterClass.HashResponse> responseStreamObserver = new StreamObserver<PasswordServiceOuterClass.HashResponse>() {
            @Override
            public void onNext(PasswordServiceOuterClass.HashResponse hashResponse) {
                User u = new User(user.getUser_id(),
                        user.getUser_name(),
                        user.getEmail(),
                        hashResponse.getHashedPassword(),
                        hashResponse.getSalt());

                UserDB.createUser(u.getUser_id(), u);
            }

            @Override
            public void onError(Throwable throwable) {
                Status status = Status.fromThrowable(throwable);
            }

            @Override
            public void onCompleted() {

            }
        };
        try {
            asyncPasswordService.hash(PasswordServiceOuterClass.Credentials.newBuilder()
                    .setId(user.getUser_id())
                    .setPassword(user.getPassword())
                    .build(), responseStreamObserver);
        } catch (StatusRuntimeException ex) {
            logger.warning("Exception: " + ex);
        }

    }

    public boolean Validate(String password, ByteString hashedPassword, ByteString salt) {

        boolean isTrue;

        PasswordServiceOuterClass.Compare validateRequest = PasswordServiceOuterClass.Compare.newBuilder()
                .setPassword(password)
                .setHashedPassword(hashedPassword)
                .setSalt(salt)
                .build();

        isTrue = syncPasswordService.validate(validateRequest).getValue();

        return isTrue;
    }



}
