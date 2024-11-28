<?php

namespace App\Http\Controllers\user;

use App\Http\Controllers\Controller;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class userController extends Controller
{
    public function index()
    {
        $data = Auth::user();
        return response([
            'data' => $data,
        ]);
    }

    public function update($id, Request $req)
    {
        $data = User::where('id', $id)->update($req->all());
        return ($data);
    }
}
