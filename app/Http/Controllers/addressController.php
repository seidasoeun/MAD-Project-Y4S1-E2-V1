<?php

namespace App\Http\Controllers;

use App\Models\addressModel;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class addressController extends Controller
{

    public function index()
    {
        $data = addressModel::where('user_id', Auth::user()->id)->get();
        return response([
            "data" => $data,
        ]);
    }

    public function store(Request $req)
    {
        $req->merge(["user_id" => Auth::user()->id]);
        $data = addressModel::create($req->all());
        return response([
            "data" => $data,
        ]);
    }

    public function destroy($id)
    {
        $data = addressModel::where('id', $id)->delete();
        return ($data);
    }

    public function update($id, Request $req)
    {
        //Update to FALSE AND SET TO CURRENT PICK = TRUE
        addressModel::where('id', '!=', $id)->where('user_id', Auth::user()->id)->update(['pick' => 'FALSE']);
        $data = addressModel::where('id', $id)->update($req->all());
        return response([
            "data" => $data,
        ]);
    }
}
