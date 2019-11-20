import com.google.protobuf.BoolValue;
import com.google.protobuf.ByteString;
import ie.gmit.sean.PasswordServiceImpl;
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
    private PasswordService.HashResponse hashResponse;
    private PasswordService.Compare compare;
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


        hashResponse = syncPasswordService.hash(credentials);

        System.out.println("The password you sent is: "+password+" The hashed value is: "+hashResponse);

        hashedPassword = hashResponse.getHashedPassword();

    }


    public void validatePassword(){

        compare = PasswordService.Compare.newBuilder()
                .setHashedPassword(hashResponse.getHashedPassword())
                .setPassword(password)
                .setSalt(hashResponse.getSalt())
                .build();

        BoolValue isTrue = syncPasswordService.validate(compare);
        //passwordServiceGrpc

        System.out.println("Validate returned: "+isTrue);

    }
}
