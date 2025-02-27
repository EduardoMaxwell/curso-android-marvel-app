package br.com.eduardomaxwell.testing.model

import br.com.eduardomaxwell.core.domain.model.Character

class CharacterFactory {
    fun create(hero: Hero) = when (hero) {
        Hero.ABomb -> Character(
            "A-Bomb (HAS)",
            "https://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg"
        )
        Hero.ThreeDMan -> Character(
            "3-D Man",
            "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
        )
    }

    sealed class Hero {
        object ThreeDMan : Hero()
        object ABomb : Hero()
    }
}