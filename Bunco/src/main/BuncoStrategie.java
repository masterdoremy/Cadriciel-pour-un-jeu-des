import java.util.ArrayList;

public class BuncoStrategie implements Strategy {
    private CollectionDes collectionDes = new CollectionDes(3);
    private int nbTour = 1;

    public void setJeuBunco() {
        for(int i=0; i<3; i++){
            collectionDes.ajouterDe(new De(6));
        }

    }


    public void rollDices(){
      Iterateur<De> iterateur =  collectionDes.getIterateur();
      iterateur.currentItem().roll();
        while (iterateur.hasNext()) {
            iterateur.next().roll();
        }
    }
    public int calculerPointage() {
        int scoreSelonBrassage = 0;
        rollDices();
        int dePremier = this.collectionDes.getListeDes().get(0).getNbFaces();
        int deDeuxieme = this.collectionDes.getListeDes().get(1).getNbFaces();
        int deTroisieme= this.collectionDes.getListeDes().get(2).getNbFaces();

        if (dePremier== deDeuxieme && dePremier == deTroisieme) {

            if (dePremier == this.nbTour) {
                scoreSelonBrassage = 21;
            } else {
                scoreSelonBrassage = 5;
            }
        }
        else {
            for (De de : this.collectionDes.getListeDes()
            ) {
                if (de.getNbFaces() == nbTour) {
                    scoreSelonBrassage++;
                }
            }
        }

        return  scoreSelonBrassage;
    }


    @Override
    public void calculerLeVainqueur(Jeu jeu){

    }



    @Override
    public int calculerScoreTour(Jeu jeu) {
        /***
         * Dans le cas où un joueur obtient trois chiffres identiques et égaux au numéro du tour, le score calculé est 21 points et si le joueur passe la main au joueur suivant.
         * Dans le cas d'obtention de 3 chiffres identiques mais différents du numéro de tour si le score est de 5 points et le joueur garde la main.
         * Dans le cas d'obtention d'un seul chiffre correspondant au numéro de tour si le score est correct et si le joueur garde la main.
         * Dans le cas d'obtention de 0 point si le joueur passe la main au joueur suivant.
         * Le classement final des joueurs avec différents scores.
         */
        De dePremier = this.collectionDes.getListeDes().get(0);
        De deDeuxieme = this.collectionDes.getListeDes().get(1);
        De deTroisieme = this.collectionDes.getListeDes().get(2);


        int numeroTour = 1;

        int score=0;

        boolean actif = true; // pour plus tard pas maintenant

        Iterateur<Joueur> iterateurJoueur = jeu.getCollectionJoueur().getIterateur();

        do {
            rollDices();
            System.out.println("Numéro du tour : " + numeroTour );
            System.out.println("Dé Un : " + dePremier.getNbFaces() + " Dé Deux : " + deDeuxieme.getNbFaces() + " Dé Trois : " +deTroisieme.getNbFaces() );
            /*
            Section pour faire les tests, exemple changer les valeurs des dés pour voir si l'algorithme marche
             */
            if (dePremier.getNbFaces() == deDeuxieme.getNbFaces() && dePremier.getNbFaces() == deTroisieme.getNbFaces()) { ;
                if (dePremier.getNbFaces() == numeroTour )  {

                    score += 21;
                    iterateurJoueur.currentItem().setScore(score);
                    System.out.println(iterateurJoueur.currentItem().getNom() + " ScoreActuel : "+ iterateurJoueur.currentItem().getScore());
                    actif = false;

                } else {

                    score += 5;
                    iterateurJoueur.currentItem().setScore(score);
                    System.out.println(iterateurJoueur.currentItem().getNom() + " ScoreActuel "+ iterateurJoueur.currentItem().getScore());
                    numeroTour++;


                    actif = true;

                }
            }
            else  if (dePremier.getNbFaces() != numeroTour && deDeuxieme.getNbFaces() != numeroTour && deTroisieme.getNbFaces() != numeroTour) {
                //iterateurJoueur.currentItem().setScore(0);
                score += 0;
                System.out.println(iterateurJoueur.currentItem().getNom() + " Score Actuel "+ iterateurJoueur.currentItem().getScore());
                numeroTour++;
                actif = false;
                // continuer ici lire en haut les contraintes et appliquer selon elles les différents if.
            }
            else {
                for (De de : this.collectionDes.getListeDes()
                ) {
                    if (de.getNbFaces() == numeroTour) {
                        iterateurJoueur.currentItem().additionerScore(1);
                        score+=1;
                    }
                }
                iterateurJoueur.currentItem().setScore(score);
                System.out.println(iterateurJoueur.currentItem().getNom() + " ScoreActuel "+ iterateurJoueur.currentItem().getScore());
                actif = true;
                numeroTour++;


            }
        }
        while (numeroTour <= 6 && actif && score <= 21);


        return iterateurJoueur.currentItem().getScore();
    }

}