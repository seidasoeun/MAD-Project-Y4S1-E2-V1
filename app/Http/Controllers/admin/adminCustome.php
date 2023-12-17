<?php

namespace App\Http\Controllers\admin;

use App\Http\Controllers\Controller;
use App\Models\eywan_soldModel;
use App\Models\eywanModel;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class adminCustome extends Controller
{
    public function count_user(){
        $data = User::count();
        return ($data);
    }

    public function count_eywan(){
        $data = eywanModel::count();
        return ($data);
    }

    public function count_eywansold(){
        $data = eywan_soldModel::count();
        return ($data);
    }

    // Profile & all image user
    public function adminprofile(){
        $data = Auth::user();
        return ($data);
    }

    // delete user by id
    public function deleteuserbyid($id){
        $data = User::where('id',$id)->delete();
        return ($data);
    }
}
