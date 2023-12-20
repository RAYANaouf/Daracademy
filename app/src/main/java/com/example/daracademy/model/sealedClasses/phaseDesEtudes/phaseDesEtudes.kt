package com.example.daracademyadmin.model.sealedClasses.phaseDesEtudes

sealed class PhaseDesEtudes(val phase : String){
    class Primaire : PhaseDesEtudes("Primaire")
    class CEM      : PhaseDesEtudes("CEM")
    class Lycee    : PhaseDesEtudes("Lycee")
}
