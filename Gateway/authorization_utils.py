import grpc
from fastapi import HTTPException
import proto.schema_pb2
import proto.schema_pb2_grpc


def check_token(jwt_token):
    with grpc.insecure_channel('localhost:9090') as channel:
        stub = proto.schema_pb2_grpc.AuthServiceStub(channel)

        validate_request = proto.schema_pb2.ValidateRequest(jwtToken=jwt_token)
        try:
            response = stub.validate(validate_request)
            return response
        except grpc.RpcError as e:
            raise HTTPException(status_code=400, detail=str(e))
