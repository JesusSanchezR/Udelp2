package com.example.estudiantes.Data

data class StudentsEntity (
    var id:Int,
    var name:String,
    var lastName:String,
    var apellidoMaterno:String,
    var gender:Int,
    var birthday:String,
    var nivelAcademico :Int,
    var escuelaProcedencia:Int,
    var telefono: String,
    var correo: String




){constructor() : this(0,"","","",0,"",0,0,"","") }

