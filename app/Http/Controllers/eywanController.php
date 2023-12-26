<?php

namespace App\Http\Controllers;

use App\Models\eywan_cartModel;
use App\Models\eywan_imageModel;
use App\Models\eywanModel;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class eywanController extends Controller
{
    public function getallvip(){
        $value = "VIP";
        $data = eywanModel::where('status',$value)->get();
        return response([
            'data' => $data,
        ]);
    }

    public function getallmedium(){
        $value = "MEDIUM";
        $data = eywanModel::where('status',$value)->get();
        return response([
            'data' => $data,
        ]);
    }

    public function getallstandard(){
        $value = "STANDARD";
        $data = eywanModel::where('status',$value)->get();
        return response([
            'data' => $data,
        ]);
    }

    public function geteywanimagebyid($id){
        $data = eywan_imageModel::where('eywan_id',$id)->get();
        return response([
            'data' => $data,
        ]);
    }

    public function getclothes(){
        $data = eywanModel::where('type',"CLOTHES")->get();
        return response([
            'data' => $data,
        ]);
    }

    public function getaccessory(){
        $data = eywanModel::where('type',"ACCESSORY")->get();
        return response([
            'data' => $data,
        ]);
    }

    public function getmaterial(){
        $data = eywanModel::where('type',"MATERIAL")->get();
        return response([
            'data' => $data,
        ]);
    }

    public function getother(){
        $data = eywanModel::where('type',"OTHER")->get();
        return response([
            'data' => $data,
        ]);
    }

    public function filter(Request $req){
        $fil = '%' . $req->text . '%';
        $data = eywanModel::where('title','like',$fil)->get();
        return response([
            'data' => $data,
        ]);
    }

    public function cart(Request $req){
        $req->merge(["user_id"=>Auth::user()->id]);
        $data = eywan_cartModel::create($req->all());
        return response([
            'data' => $data,
        ]);
    }

    public function getcart(){
        $current_user = Auth::user()->id;
        $data = eywan_cartModel::where('user_id',$current_user)->get();
        return response([
            'data' => $data,
        ]);
    }

    public function getcartdelete($id){
        $data = eywan_cartModel::where('id',$id)->delete();
        return ($data);
    }

}
