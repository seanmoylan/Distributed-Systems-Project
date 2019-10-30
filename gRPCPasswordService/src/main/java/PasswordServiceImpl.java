import com.google.protobuf.BoolValue;
import com.google.protobuf.ByteString;
import ie.sean.password.PasswordService;
import ie.sean.password.passwordServiceGrpc.passwordServiceImplBase;
import io.grpc.stub.StreamObserver;

public class PasswordServiceImpl extends passwordServiceImplBase {

    /*
      Extend the passwordServiceImplBase
    -------------------------------------
    Override the hash and validate methods then
    Implement the Passwords.java class to
    hash and compare client passwords
    */

    @Override
    public void hash(PasswordService.Credentials request, StreamObserver<PasswordService.HashResponse> responseObserver) {

        // Get id value from clients request
        int id = request.getId();

        // Get salt then run it through the hash method with client password
        byte[] salt = Passwords.getNextSalt();
        byte[] hashedPassword = Passwords.hash(request.getPassword().toCharArray(), salt);

        // Build response to pass to response observer
        PasswordService.HashResponse requestResponse = PasswordService.HashResponse.newBuilder()
                .setSalt(ByteString.copyFrom(salt))
                .setUserId(id)
                .setHashedPassword(ByteString.copyFrom(hashedPassword))
                .build();

        responseObserver.onNext(requestResponse);
        responseObserver.onCompleted();

    }

    @Override
    public void validate(PasswordService.Compare request, StreamObserver<BoolValue> responseObserver) {

        // get the password from client request
        String password = request.getPassword();

        // Compare users hashed password with stubs
        byte[] salt = request.getSalt().toByteArray();
        byte[] hashedPassword = request.getHashedPassword().toByteArray();

        boolean isPasswordSame = Passwords.isExpectedPassword(password.toCharArray(), salt, hashedPassword);

        responseObserver.onNext(BoolValue.of(isPasswordSame));
        responseObserver.onCompleted();

    }
}
