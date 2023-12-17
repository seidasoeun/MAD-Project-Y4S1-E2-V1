<?php

namespace App\Http\Controllers;

use App\Models\eywan_imageModel;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class eywan_imageController extends Controller
{
    public function store(Request $req){
        $req->merge(["user_id"=>Auth::user()->id]);
        $data = eywan_imageModel::create($req->all());
        return ($data);
    }

}
