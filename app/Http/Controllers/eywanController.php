<?php

namespace App\Http\Controllers;

use App\Models\eywanModel;
use Illuminate\Http\Request;

class eywanController extends Controller
{
    public function getallvip(){
        $value = "VIP";
        $data = eywanModel::where('status',$value)->get();
        return response([
            'data' => $data,
        ]);
    }
}
