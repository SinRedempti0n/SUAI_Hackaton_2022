package com.example.hackaton

import java.io.Serializable

class Place{
    var id: String
    var name: String
    var longitude: Double
    var latitude: Double
    var history: String
    var url: String



    constructor(id: String, name: String, longitude: Double, latitude: Double, history: String, url: String){
        this.id = id
        this.name = name
        this.longitude = longitude
        this.latitude = latitude
        this.history = history
        this.url = url
    }

//    constructor(){
//        this.id = "null_id"
//        this.name = "null_name"
//        this.longitude = 0.0
//        this.latitude = 0.0
//        this.history = "history"
//    }
}