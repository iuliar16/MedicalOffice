from fastapi import FastAPI, Header, HTTPException, Request
import requests
from fastapi.middleware.cors import CORSMiddleware
import uvicorn
import grpc
from pydantic import BaseModel
from authorization_utils import check_token

import proto.schema_pb2
import proto.schema_pb2_grpc

app = FastAPI()

app.add_middleware(CORSMiddleware, allow_origins=["*"], allow_credentials=True, allow_methods=["*"],
                   allow_headers=["*"])

users_service_url = "http://localhost:8081/api/medical_office/physicians"


def forward_request(request_method: str, url: str, headers: dict, params=None, data=None, json=None):
    print("forward")
    response = requests.request(method=request_method, url=url, headers=headers, params=params, data=data, json=json)
    return response.json()


class AuthRequest(BaseModel):
    username: str
    password: str


class LogoutRequest(BaseModel):
    jwtToken: str

@app.post("/autentificare")
def autentificare(authRequest: AuthRequest):
    username = authRequest.username
    password = authRequest.password
    role = "pacient"
    with grpc.insecure_channel('localhost:9090') as channel:
        stub = proto.schema_pb2_grpc.AuthServiceStub(channel)
        request = proto.schema_pb2.AddUserRequest(username=username, password=password, role=role)
        try:
            response = stub.addUser(request)
            print(response)
            return {"username": response.username,
                    "id_user": response.id_user,
                    "role": response.role}
        except grpc.RpcError as e:
            raise HTTPException(status_code=400, detail=str(e))

@app.post("/logout")
def logout(logoutRequest: LogoutRequest):
    print("here")
    token = logoutRequest.jwtToken
    with grpc.insecure_channel('localhost:9090') as channel:
        print("here2")
        stub = proto.schema_pb2_grpc.AuthServiceStub(channel)
        request = proto.schema_pb2.LogoutRequest(jwtToken=token)
        try:
            response = stub.Logout(request)
            return {"message": "Logout reușit - token adaugat la blacklist"}
        except grpc.RpcError as e:
            raise HTTPException(status_code=400, detail=str(e))

@app.post("/login")
def login(authRequest: AuthRequest):
    username = authRequest.username
    password = authRequest.password
    with grpc.insecure_channel('localhost:9090') as channel:
        stub = proto.schema_pb2_grpc.AuthServiceStub(channel)
        request = proto.schema_pb2.JwtRequest(username=username, password=password)
        try:
            response = stub.authenticate(request)
            return {"jwtToken": response.jwtToken}
        except grpc.RpcError as e:
            raise HTTPException(status_code=400, detail=str(e))

@app.post("/addDoctor")
def adaugaDoctor(authRequest: AuthRequest):
    username = authRequest.username
    password = authRequest.password
    role = "doctor"
    with grpc.insecure_channel('localhost:9090') as channel:
        stub = proto.schema_pb2_grpc.AuthServiceStub(channel)
        request = proto.schema_pb2.AddUserRequest(username=username, password=password, role=role)
        try:
            response = stub.addUser(request)
            print(response)
            return {"username": response.username,
                    "id_user": response.id_user,
                    "role": response.role}
        except grpc.RpcError as e:
            raise HTTPException(status_code=400, detail=str(e))


@app.api_route(path="/api/doctors/{path:path}", methods=["GET", "PUT", "DELETE"])
def doctors_proxy(path: str, request: Request):
    base_url = "http://localhost:8081/api/medical_office/physicians"
    if path:
        service_url = f"{base_url}/{path}"
    else:
        service_url = base_url
    headers_to_forward = dict(request.headers)
    del headers_to_forward['host']

    if 'authorization' in headers_to_forward:
        token = headers_to_forward['authorization'].split(" ")[1]
    else:
        raise HTTPException(status_code=401, detail="Authentication header missing")

    if not check_token(token):
        raise HTTPException(status_code=403, detail="Bearer token forbidden")

    if request.method in ("GET", "DELETE"):
        return forward_request(request.method, service_url, headers_to_forward, params=request.query_params)
    elif request.method in "PUT":
        return forward_request(request.method, service_url, headers_to_forward, json=request.json())
    else:
        raise HTTPException(status_code=405, detail="Method Not Allowed")


@app.api_route(path="/api/patients/{path:path}", methods=["GET", "PUT", "DELETE"])
def patients_proxy(path: str, request: Request):
    print("patients")
    base_url = "http://localhost:8080/api/medical_office/patients"
    if path:
        service_url = f"{base_url}/{path}"
    else:
        service_url = base_url

    headers_to_forward = dict(request.headers)
    del headers_to_forward['host']

    if 'authorization' in headers_to_forward:
        token = headers_to_forward['authorization'].split(" ")[1]
    else:
        raise HTTPException(status_code=401, detail="Authentication header missing")

    if not check_token(token):
        raise HTTPException(status_code=403, detail="Bearer token forbidden")

    if request.method in ("GET", "DELETE"):
        print("Get")
        return forward_request(request.method, service_url, headers_to_forward, params=request.query_params)
    elif request.method in "PUT":
        return forward_request(request.method, service_url, headers_to_forward, json=request.json())
    else:
        raise HTTPException(status_code=405, detail="Method Not Allowed")


@app.post("/api/patients")
async def create_patient(request: Request):
    service_url = f"http://localhost:8080/api/medical_office/patients"
    headers_to_forward = dict(request.headers)
    del headers_to_forward['host']

    json_data = await request.json()
    print(json_data)
    return forward_request(request.method, service_url, headers_to_forward, json=json_data)


@app.post("/api/doctors")
async def create_doctor(request: Request):
    service_url = f"http://localhost:8081/api/medical_office/physicians"
    headers_to_forward = dict(request.headers)
    del headers_to_forward['host']

    if 'authorization' in headers_to_forward:
        token = headers_to_forward['authorization'].split(" ")[1]
    else:
        raise HTTPException(status_code=401, detail="Authentication header missing")

    if not check_token(token):
        raise HTTPException(status_code=403, detail="Bearer token forbidden")

    json_data = await request.json()
    print(json_data)
    return forward_request(request.method, service_url, headers_to_forward, json=json_data)


@app.api_route(path="/api/programari/{path:path}", methods=["GET", "POST", "PUT", "DELETE"])
async def programari_proxy(path: str, request: Request):
    base_url = "http://localhost:8082/api/medical_office"
    if path:
        service_url = f"{base_url}/{path}"
    else:
        service_url = base_url
    headers_to_forward = dict(request.headers)
    del headers_to_forward['host']

    if 'authorization' in headers_to_forward:
        token = headers_to_forward['authorization'].split(" ")[1]
    else:
        raise HTTPException(status_code=401, detail="Authentication header missing")

    if not check_token(token):
        raise HTTPException(status_code=403, detail="Bearer token forbidden")

    if request.method in ("GET", "DELETE"):
        return forward_request(request.method, service_url, headers_to_forward, params=request.query_params)
    elif request.method in ("POST", "PUT"):
        json_data = await request.json()
        return forward_request(request.method, service_url, headers_to_forward, json=json_data)
    else:
        raise HTTPException(status_code=405, detail="Method Not Allowed")


@app.api_route(path="/api/consultatii/{path:path}", methods=["GET", "POST", "PUT", "DELETE"])
async def consultatii_proxy(path: str, request: Request):
    base_url = "http://localhost:8084/api/medical_office/consultatii"
    if path:
        service_url = f"{base_url}/{path}"
    else:
        service_url = base_url
    headers_to_forward = dict(request.headers)
    del headers_to_forward['host']

    if 'authorization' in headers_to_forward:
        token = headers_to_forward['authorization'].split(" ")[1]
    else:
        raise HTTPException(status_code=401, detail="Authentication header missing")

    if not check_token(token):
        raise HTTPException(status_code=403, detail="Bearer token forbidden")

    if request.method in ("GET", "DELETE"):
        return forward_request(request.method, service_url, headers_to_forward, params=request.query_params)
    elif request.method in ("POST", "PUT"):
        json_data = await request.json()
        print(request.json)
        return forward_request(request.method, service_url, headers_to_forward, json=json_data)
    else:
        raise HTTPException(status_code=405, detail="Method Not Allowed")

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)
