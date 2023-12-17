<?php

use App\Http\Controllers\admin\adminController;
use App\Http\Controllers\admin\adminCustome;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\Controller;
use App\Http\Controllers\eywan_imageController;
use App\Http\Controllers\eywanController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

Route::post('/register',[AuthController::class,'register']);
Route::post('/login',[AuthController::class,'login']);

// Admin
Route::resource('getalluser',adminController::class);
Route::get('countuser',[adminCustome::class,'count_user']);
Route::get('counteywan',[adminCustome::class,'count_eywan']);
Route::get('counteywansold',[adminCustome::class,'count_eywansold']);
    //profile
Route::get('adminprofile',[adminCustome::class,'adminprofile']);


Route::middleware('auth:sanctum')->group(function(){

    //admin
    Route::resource('/eywan',Controller::class);
    Route::resource('/eywan_image',eywan_imageController::class);
    Route::get('adminprofile',[adminCustome::class,'adminprofile']);
    Route::delete('deleteuserbyid/{id}',[adminCustome::class,'deleteuserbyid']);
    Route::resource('/eywan',Controller::class);
    Route::resource('/eywan_image',eywan_imageController::class);

    //user
    Route::get('/getallvip',[eywanController::class,'getallvip']);

});



