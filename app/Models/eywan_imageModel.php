<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class eywan_imageModel extends Model
{
    use HasFactory;

    protected $table = "eywan_image";
    protected $guarded = ['id'];
}
