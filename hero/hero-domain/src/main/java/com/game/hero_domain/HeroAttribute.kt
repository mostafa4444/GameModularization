package com.game.hero_domain

sealed class HeroAttribute(
    val uiValue: String,
    val abbreviation: String
){
    object Agility: HeroAttribute(
        uiValue = "Agility",
        abbreviation = "agi"
    )

    object Strength: HeroAttribute(
        uiValue = "Strength",
        abbreviation = "str"
    )

    object Intelligence: HeroAttribute(
        uiValue = "Intelligence",
        abbreviation = "int"
    )

    object Unknown: HeroAttribute(
        uiValue = "Unknown",
        abbreviation = "Unknown"
    )

}

fun getHeroAttrFromUiValue(uiValue: String): HeroAttribute{
    return when(uiValue){
        HeroAttribute.Agility.uiValue->{
            HeroAttribute.Agility
        }
        HeroAttribute.Strength.uiValue->{
            HeroAttribute.Strength
        }
        HeroAttribute.Intelligence.uiValue->{
            HeroAttribute.Intelligence
        }
        else -> HeroAttribute.Unknown
    }
}

fun getHeroAttrFromAbbreviation(abbreviation : String): HeroAttribute{
    return when(abbreviation){
        HeroAttribute.Agility.abbreviation->{
            HeroAttribute.Agility
        }
        HeroAttribute.Strength.abbreviation->{
            HeroAttribute.Strength
        }
        HeroAttribute.Intelligence.abbreviation->{
            HeroAttribute.Intelligence
        }
        else -> HeroAttribute.Unknown
    }
}

fun Hero.minAttackDmg(): Int {
    return when(this.primaryAttribute){
        is HeroAttribute.Strength -> {
            this.baseAttackMin + this.baseStr
        }
        is HeroAttribute.Agility -> {
            this.baseAttackMin + this.baseAgi
        }
        is HeroAttribute.Intelligence -> {
            this.baseAttackMin + this.baseInt
        }
        else -> {
            0
        }
    }
}

fun Hero.maxAttackDmg(): Int {
    return when(this.primaryAttribute){
        is HeroAttribute.Strength -> {
            this.baseAttackMax + this.baseStr
        }
        is HeroAttribute.Agility -> {
            this.baseAttackMax + this.baseAgi
        }
        is HeroAttribute.Intelligence -> {
            this.baseAttackMax + this.baseInt
        }
        else -> {
            0
        }
    }
}
