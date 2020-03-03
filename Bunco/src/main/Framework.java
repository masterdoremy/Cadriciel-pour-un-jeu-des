package main;

public abstract class Framework {

    private ArrayList<Joueur> playerList;
    private ArrayList<De> deList;

    final void gameStart() {

        debutPartie();
        jouerTour();
        finPartie();

    }

    public void debutPartie() {}

    public void jouerTour() {}

    public void finPartie() {

        calculerLeVainceur();
        calculerScoreTour();

    }

    abstract void calculerLeVainceur(Jeu jeu);

    abstract void calculerScoreTour(Jeu jeu);

}