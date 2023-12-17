<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class eywan_soldModel extends Model
{
    use HasFactory;

    protected $table = "eywan_sold";
    protected $guarded = ['id'];
}
