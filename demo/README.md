# Auth API

### Instruction
1.I add test cases JSON document into the folder "testcase", 
which is exported by Postman, if you need, welcome to use 
it by importing into tools for test.  
2.You can see "Current user list" and "Current role list" 
in console producted by LOGGER when you change the content of them.

### Introduction of API
Function|API|POST Request Body
:---|:---|:---
Create User|/demo/user/create|{"userName":"uuu","password":"ppp"}
Delete User|/demo/user/delete|{"userName":"uuu"}
Create Role|/demo/role/create|{"roleName":"rrr"}
Delete Role|/demo/role/delete|{"roleName":"rrr"}
Add Role to User|/demo/user/add-role|{"userName":"uuu","roleName":"rrr"}
Authenticate|/demo/user/auth|{"userName":"uuu","password":"ppp"}
Invalidate|/demo/user/invalid|{"token":"ttt"}
Check Role|/demo/role/check|{"token":"ttt","roleName":"rrr"}
All Roles|/demo/role/all|{"token":"ttt"}

