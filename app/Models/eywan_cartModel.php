<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class eywan_cartModel extends Model
{
    use HasFactory;

    protected $table = "eywan_cart";
    protected $guarded = ['id'];
}
