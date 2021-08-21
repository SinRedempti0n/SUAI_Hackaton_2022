package com.example.hackaton

import java.io.Serializable

class Place{
    var id: String
    var name: String
    var longitude: Double
    var latitude: Double
    var history: String



    constructor(id: String, name: String, longitude: Double, latitude: Double, history: String){
        this.id = id
        this.name = name
        this.longitude = longitude
        this.latitude = latitude
        this.history = history
    }

}