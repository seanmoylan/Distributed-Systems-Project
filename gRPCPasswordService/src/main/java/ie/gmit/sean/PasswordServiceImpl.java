package ie.gmit.sean;

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
    Implement the ie.gmit.sean.Passwords.java class to
    hash and compare client passwords
    */

    @Override
    public void hash(PasswordService.Credentials request, StreamObserver<PasswordService.HashResponse> responseObserver) {

        System.out.println("== Hash method called ==");
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

        System.out.println("== Validate method called ==");
        // get the password from client request
        String password = request.getPassword();

        // Compare users hashed password with stubs
        byte[] salt = request.getSalt().toByteArray();
        byte[] hashedPassword = request.getHashedPassword().toByteArray();

        boolean isPasswordSame = Passwords.isExpectedPassword(password.toCharArray(), salt, hashedPassword);
        System.out.println("Passwords match = "+isPasswordSame);

        BoolValue response = BoolValue.newBuilder().setValue(isPasswordSame).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
