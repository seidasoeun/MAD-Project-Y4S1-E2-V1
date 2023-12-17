<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;

class AuthController extends Controller
{
    public function register(Request $req){
        $user = User::create($req->all());
        return $user;
    }

    public function login(Request $req)
    {
        $user = User::where('email', $req->email)->first();
        if (!$user) {
            return response([
                "status" => 0,
            ]);
        }

        // if($req->email == "admin@gmail.com" && $req->password == "321"){
        //     $admin = "admin";
        // }else{
        //     $admin = "not admin";
        // }

        $status = 1;


        if(Hash::check($req->password,$user->password)){
            if($req->email == "admin@gmail.com" && $req->password == "321"){
                $status = 2;
            }
            $access_token = $user->createToken('authToken')->plainTextToken;
            return response([
                'status' => $status,
                'user' => $user,
                'Token' => $access_token,
            ]);
        }
        else{
            return response([
                "status" => 0,
            ]);
        }
    }
}
