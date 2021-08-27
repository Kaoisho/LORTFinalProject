package com.kaoishoworks.lotrfinalproject.util;

import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * Esta clase implementa la animacion de las cartas al batallar entre ellas.
 * @author Jesús Mendoza Escudero
 * @version 1.0
 */
public class CardMovement {
    private static final Duration TRANSLATE_DURATION = Duration.seconds(0.25);

    public TranslateTransition createTranslateTransition(final AnchorPane card) {
        final TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, card);
        transition.setOnFinished(t -> {
            card.setTranslateX(card.getTranslateX() + card.getTranslateX());
            card.setTranslateY(card.getTranslateY() + card.getTranslateY());
            card.setTranslateX(0);
            card.setTranslateY(0);
        });
        return transition;
    }
    /**
     * Método que moverá las cartas de los héroes.
     * @param scene Scene en la que se encuentra la carta
     * @param heroCard Carta del héroe.
     * @param transition coordenadas XY de la carta.
     */
    public void moveHeroCard(Scene scene, final AnchorPane heroCard, final TranslateTransition transition) {
        if (heroCard.getId().equals("heroCard1") || heroCard.getId().equals("heroCard4")) {
            transition.setToX(heroCard.getTranslateX());
            transition.setToY(heroCard.getTranslateY()- 50 - scene.getY());
            transition.playFromStart();
        } else {
            transition.setToX(heroCard.getTranslateX());
            transition.setToY(heroCard.getTranslateY()- 20 - scene.getY());
            transition.playFromStart();
        }
    }
    /**
     * Método que moverá las cartas de las bestias.
     * @param scene Scene en la que se encuentra la carta.
     * @param beastCard Carta de la bestia.
     * @param transition coordenadas XY de la carta.
     */
    public void moveBeastCard(Scene scene, final AnchorPane beastCard, final TranslateTransition transition) {
        if (beastCard.getId().equals("beastCard1") || beastCard.getId().equals("beastCard4")) {
            transition.setToX(beastCard.getTranslateX());
            transition.setToY(beastCard.getTranslateY()+ 50 + scene.getY());
            transition.playFromStart();
        } else {
            transition.setToX(beastCard.getTranslateX());
            transition.setToY(beastCard.getTranslateY()+ 20 + scene.getY());
            transition.playFromStart();
        }
    }
}
