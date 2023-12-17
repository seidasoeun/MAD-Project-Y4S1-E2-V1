<?php

namespace App\Http\Controllers;

use App\Models\eywanModel;
use Illuminate\Http\Request;
use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Routing\Controller as BaseController;
use Illuminate\Support\Facades\Auth;

class Controller extends BaseController
{
    use AuthorizesRequests, ValidatesRequests;

    public function index(){
        $data = eywanModel::all();
        return ($data);
    }

    public function store(Request $req){
        $req->merge(["user_id"=>Auth::user()->id]);
        $data = eywanModel::create($req->all());
        return response([
            "data"=>$data,
        ]);
    }

    public function destroy($id){
        $data = eywanModel::where('id',$id)->delete();
        return ($data);
    }

    public function update($id,Request $req){
        $data = eywanModel::where('id',$id)->update($req->all());
        return ($data);
    }
}
