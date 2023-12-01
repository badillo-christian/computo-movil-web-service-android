package com.tizzone.getaround.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.tizzone.getaround.domain.model.Car

import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class CarDtoModel(

    @field:SerializedName("model")
    val model: String? = null,

    @field:SerializedName("brand")
    val brand: String? = null,

    @field:SerializedName("precio")
    val precio: String? = null,

    @field:SerializedName("anio")
    val anio: String? = null,

    @field:SerializedName("km")
    val km: String? = null,

    @field:SerializedName("motor")
    val motor: String? = null,

    @field:SerializedName("garantia")
    val garantia: String? = null,

    @field:SerializedName("picture_url")
    val picture_url: String? = null,

    @field:SerializedName("nombrePais")
    val nombrePais: String? = null,

    @field:SerializedName("nombreEstado")
    val nombreEstado: String? = null,

    @field:SerializedName("nombreCapital")
    val nombreCapital: String? = null,

    ) : Parcelable {
    fun toDomain(): Car {
        return Car(
            id = UUID.randomUUID().toString(),
            model = model!!,
            brand = brand!!,
            precio = precio!!,
            anio = anio!!,
            km = km!!,
            motor = motor!!,
            garantia = garantia!!,
            picture_url = picture_url!!,
            nombrePais = nombrePais!!,
            nombreEstado = nombreEstado!!,
            nombreCapital = nombreCapital!!
        )
    }
}


