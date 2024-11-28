<?php

namespace App\Http\Controllers;

use App\Models\addressModel;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class addressCustome extends Controller
{
    public function getMarkAddress()
    {
        $data = addressModel::where('user_id', Auth::user()->id)->where('pick', 'TRUE')->first();
        return response([
            "data" => $data,
        ]);
    }
}
