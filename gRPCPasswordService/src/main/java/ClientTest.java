import com.google.protobuf.BoolValue;
import com.google.protobuf.ByteString;
import ie.gmit.sean.Passwords;
import ie.sean.password.PasswordService;
import ie.sean.password.passwordServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class ClientTest {
    private final ManagedChannel channel;
    private String password;
    private ByteString hashedPassword;
    private Passwords ps;
    private final passwordServiceGrpc.passwordServiceStub asyncPasswordService;
    private final passwordServiceGrpc.passwordServiceBlockingStub syncPasswordService;

    public ClientTest(String host, int port) {
        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        asyncPasswordService = passwordServiceGrpc.newStub(channel);
        syncPasswordService = passwordServiceGrpc.newBlockingStub(channel);

        hashPassword();

        validatePassword();

    }

    public static void main(String[] args){
        ClientTest testClient = new ClientTest("localHost", 8899);

    }

    public void hashPassword(){
        password = Passwords.generateRandomPassword(15);


        PasswordService.Credentials credentials = PasswordService.Credentials.newBuilder()
                .setId(123)
                .setPassword(password)
                .build();

        hashedPassword = syncPasswordService.hash(credentials).getHashedPassword();

        System.out.println("The password you sent is: "+password+" The hashed value is: "+hashedPassword);

    }

    public void hashResponse(){


    }

    public void validatePassword(){

        PasswordService.Compare com = PasswordService.Compare.newBuilder().setHashedPassword(hashedPassword)
                .setPassword(password).setSalt( Passwords.getNextSalt()).build();

        System.out.println("In the method");
       asyncPasswordService.validate(com, new StreamObserver<BoolValue>() {
           @Override
           public void onNext(BoolValue boolValue) {
               System.out.println("Do passwords match: ");
           }

           @Override
           public void onError(Throwable throwable) {

           }

           @Override
           public void onCompleted() {

           }
       });
    }
}
