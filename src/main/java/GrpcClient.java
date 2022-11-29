import demo.UserAccessGrpc;
import demo.UserRequests;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

    public static void main(String[] args) {
        // create a channel so that client can communicate to the server
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();

        // to communicate with the server APIs, we need to create a stub(already generated in GRPC file)
        UserAccessGrpc.UserAccessBlockingStub blockingStub = UserAccessGrpc.newBlockingStub(channel);

        UserRequests.LoginRequest loginRequest =
                UserRequests.LoginRequest.newBuilder().setUserName("jay").setPassword("jaypp").build();
        UserRequests.UserRequestResponse loginResponse = blockingStub.login(loginRequest);

        System.out.println(loginResponse.getResponseMessage());
        System.out.println(loginResponse.getResponseCode());

        UserRequests.LogoutRequest logoutRequest =
                UserRequests.LogoutRequest.newBuilder().setUsername("jay").build();
        UserRequests.UserRequestResponse logoutResponse = blockingStub.logout(logoutRequest);

        System.out.println(logoutResponse.getResponseMessage());
        System.out.println(logoutResponse.getResponseCode());

    }
}
