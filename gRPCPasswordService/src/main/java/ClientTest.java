import ie.sean.password.PasswordService;
import ie.sean.password.passwordServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClientTest {
    private final ManagedChannel channel;
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

    }

    public static void main(String[] args){
        ClientTest testClient = new ClientTest("localHost", 8899);
    }

    public void hashPassword(){
        String password = Passwords.generateRandomPassword(5);


        PasswordService.Credentials credentials = PasswordService.Credentials.newBuilder()
                .setId(123)
                .setPassword(password)
                .build();

        byte[] hashedPassword = syncPasswordService.hash(credentials).getHashedPassword().toByteArray();
        System.out.println("The password you sent is: "+password+" The hashed value is: "+hashedPassword);
    }

    public void hasResponse(){

    }

    public void validatePassword(){

    }
}
