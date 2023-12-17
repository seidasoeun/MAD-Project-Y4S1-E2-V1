<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class eywanModel extends Model
{
    use HasFactory;

    protected $table = "eywan";
    protected $guarded = ['id'];
}
