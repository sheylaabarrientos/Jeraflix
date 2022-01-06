package com.jera.jeraflixx.model

class ExceptionModel(message: String = "Falha ao conectar na Internet") : Exception(message) {
}