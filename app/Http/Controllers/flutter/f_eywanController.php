<?php

namespace App\Http\Controllers\flutter;

use App\Http\Controllers\Controller;
use App\Models\eywanModel;
use Illuminate\Http\Request;

class f_eywanController extends Controller
{
    public function f_getallaccessory()
    {
        $Vip_accessory = eywanModel::where('status', 'VIP')->where('type', 'ACCESSORY')->get();
        $Medium_accessory = eywanModel::where('status', 'MEDIUM')->where('type', 'ACCESSORY')->get();
        $Standard_accessory = eywanModel::where('status', 'STANDARD')->where('type', 'ACCESSORY')->get();
        return response([
            'Vip_accessory' => $Vip_accessory,
            'Medium_accessory' => $Medium_accessory,
            'Standard_accessory' => $Standard_accessory,
        ]);
    }

    public function f_getallmaterial()
    {
        $Vip_material = eywanModel::where('status', 'VIP')->where('type', 'MATERIAL')->get();
        $Medium_material = eywanModel::where('status', 'MEDIUM')->where('type', 'MATERIAL')->get();
        $Standard_material = eywanModel::where('status', 'STANDARD')->where('type', 'MATERIAL')->get();
        return response([
            'Vip_material' => $Vip_material,
            'Medium_material' => $Medium_material,
            'Standard_material' => $Standard_material,
        ]);
    }

    public function f_getallclothes()
    {
        $Vip_clothes = eywanModel::where('status', 'VIP')->where('type', 'CLOTHES')->get();
        $Medium_clothes = eywanModel::where('status', 'MEDIUM')->where('type', 'CLOTHES')->get();
        $Standard_clothes = eywanModel::where('status', 'STANDARD')->where('type', 'CLOTHES')->get();
        return response([
            'Vip_clothes' => $Vip_clothes,
            'Medium_clothes' => $Medium_clothes,
            'Standard_clothes' => $Standard_clothes,
        ]);
    }

    public function f_getallother()
    {
        $Vip_other = eywanModel::where('status', 'VIP')->where('type', 'OTHER')->get();
        $Medium_other = eywanModel::where('status', 'MEDIUM')->where('type', 'OTHER')->get();
        $Standard_other = eywanModel::where('status', 'STANDARD')->where('type', 'OTHER')->get();
        return response([
            'Vip_other' => $Vip_other,
            'Medium_other' => $Medium_other,
            'Standard_other' => $Standard_other,
        ]);
    }

    public function f_getEywanbyid($id)
    {
        $data = eywanModel::where('id', $id)->first();
        return response([
            'data' => $data,
        ]);
    }
}
