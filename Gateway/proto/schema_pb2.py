# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: schema.proto
# Protobuf Python Version: 4.25.0
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import symbol_database as _symbol_database
from google.protobuf.internal import builder as _builder
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\x0cschema.proto\"0\n\nJwtRequest\x12\x10\n\x08username\x18\x01 \x01(\t\x12\x10\n\x08password\x18\x02 \x01(\t\"S\n\x0e\x41\x64\x64UserRequest\x12\x0f\n\x07id_user\x18\x01 \x01(\x05\x12\x10\n\x08username\x18\x02 \x01(\t\x12\x10\n\x08password\x18\x03 \x01(\t\x12\x0c\n\x04role\x18\x04 \x01(\t\"S\n\x0f\x41\x64\x64UserResponse\x12\x0f\n\x07success\x18\x01 \x01(\x08\x12\x0f\n\x07id_user\x18\x02 \x01(\x05\x12\x10\n\x08username\x18\x03 \x01(\t\x12\x0c\n\x04role\x18\x04 \x01(\t\"#\n\x0fValidateRequest\x12\x10\n\x08jwtToken\x18\x01 \x01(\t\"B\n\x10ValidateResponse\x12\x10\n\x08username\x18\x01 \x01(\t\x12\x0c\n\x04role\x18\x02 \x01(\t\x12\x0e\n\x06status\x18\x03 \x01(\x08\"\x1c\n\x08JwtToken\x12\x10\n\x08jwtToken\x18\x01 \x01(\t\"!\n\rLogoutRequest\x12\x10\n\x08jwtToken\x18\x01 \x01(\t\"2\n\x0eLogoutResponse\x12\x0f\n\x07success\x18\x01 \x01(\x08\x12\x0f\n\x07message\x18\x02 \x01(\t2\xc5\x01\n\x0b\x41uthService\x12(\n\x0c\x61uthenticate\x12\x0b.JwtRequest\x1a\t.JwtToken\"\x00\x12\x31\n\x08validate\x12\x10.ValidateRequest\x1a\x11.ValidateResponse\"\x00\x12.\n\x07\x61\x64\x64User\x12\x0f.AddUserRequest\x1a\x10.AddUserResponse\"\x00\x12)\n\x06Logout\x12\x0e.LogoutRequest\x1a\x0f.LogoutResponseB\x07\n\x05protob\x06proto3')

_globals = globals()
_builder.BuildMessageAndEnumDescriptors(DESCRIPTOR, _globals)
_builder.BuildTopDescriptorsAndMessages(DESCRIPTOR, 'schema_pb2', _globals)
if _descriptor._USE_C_DESCRIPTORS == False:
  _globals['DESCRIPTOR']._options = None
  _globals['DESCRIPTOR']._serialized_options = b'\n\005proto'
  _globals['_JWTREQUEST']._serialized_start=16
  _globals['_JWTREQUEST']._serialized_end=64
  _globals['_ADDUSERREQUEST']._serialized_start=66
  _globals['_ADDUSERREQUEST']._serialized_end=149
  _globals['_ADDUSERRESPONSE']._serialized_start=151
  _globals['_ADDUSERRESPONSE']._serialized_end=234
  _globals['_VALIDATEREQUEST']._serialized_start=236
  _globals['_VALIDATEREQUEST']._serialized_end=271
  _globals['_VALIDATERESPONSE']._serialized_start=273
  _globals['_VALIDATERESPONSE']._serialized_end=339
  _globals['_JWTTOKEN']._serialized_start=341
  _globals['_JWTTOKEN']._serialized_end=369
  _globals['_LOGOUTREQUEST']._serialized_start=371
  _globals['_LOGOUTREQUEST']._serialized_end=404
  _globals['_LOGOUTRESPONSE']._serialized_start=406
  _globals['_LOGOUTRESPONSE']._serialized_end=456
  _globals['_AUTHSERVICE']._serialized_start=459
  _globals['_AUTHSERVICE']._serialized_end=656
# @@protoc_insertion_point(module_scope)
