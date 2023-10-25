public class Crab extends UFO{

    /** constructeur de la classe
     * @param x double position en abscisse de l'alien
     * @param y double position en ordonnée de l'alien
     */
    public Crab(double x, double y){
        super(x, y);
        this.chaine.ajouteChaine(this.posX, this.posY-0, "  ▀▄   ▄▀");
        this.chaine.ajouteChaine(this.posX, this.posY-1, " ▄█▀███▀█▄");
        this.chaine.ajouteChaine(this.posX, this.posY-2, "█▀███████▀█");
        this.chaine.ajouteChaine(this.posX, this.posY-3, "█ █▀▀▀▀▀█ █");
        this.chaine.ajouteChaine(this.posX, this.posY-4, "   ▀▀ ▀▀");
    }

    /** méthode d'obtention de la représentation graphique de l'alien
     * @return EnsembleChaines ensemble des chaînes de caractères représentant l'alien
     */
    public EnsembleChaines getEnsembleChaines(){
        this.nbTours++;
        if (!this.vivant || this.nbTours % 60 < 30){return this.chaine;}
        else{
            EnsembleChaines autreApparence = new EnsembleChaines();
            autreApparence.ajouteChaine(this.posX, this.posY-0, "  ▀▄   ▄▀");
            autreApparence.ajouteChaine(this.posX, this.posY-1, " ▄█▀███▀█▄");
            autreApparence.ajouteChaine(this.posX, this.posY-2, "█▀███████▀█");
            autreApparence.ajouteChaine(this.posX, this.posY-3, "█ █▀▀▀▀▀█ █");
            autreApparence.ajouteChaine(this.posX, this.posY-4, " ▀▀     ▀▀");
            return autreApparence;
        }
    }

    @Override
    /** méthode de changement d'apparence */
    protected void changementApparence(){
        if (this.numApparence == 36){
            this.chaine = new EnsembleChaines();
            this.actif = false;
        }
        if (this.numApparence < 36){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-2, "    ██");
        }
        if (this.numApparence < 33){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-1, "   ██");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "    ██");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "   ▀▀");
        }
        if (this.numApparence < 30){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-1, "    ██");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "  █  ██");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "  █▀▀▀");
        }
        if (this.numApparence < 27){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-1, "   ▀███");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "  █  ███▀");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "  █▀▀▀");
        }
        if (this.numApparence < 24){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "   ▄");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "   ▀███");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "  █ ████▀");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "  █▀▀▀");
            this.chaine.ajouteChaine(this.posX, this.posY-4, "   ▀");
        }
        if (this.numApparence < 21){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "   ▄");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  █▀███▀");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "  █ ████▀");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "  █▀▀▀▀");
            this.chaine.ajouteChaine(this.posX, this.posY-4, "   ▀");
        }
        if (this.numApparence < 18){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "   ▄");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  █▀███▀");
            this.chaine.ajouteChaine(this.posX, this.posY-2, " ▀█ ████▀");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "  █▀▀▀▀▀█");
            this.chaine.ajouteChaine(this.posX, this.posY-4, "   ▀");
        }
        if (this.numApparence < 15){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "   ▄");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  █▀███▀█");
            this.chaine.ajouteChaine(this.posX, this.posY-2, " ▀█ ████▀");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "  █▀▀▀▀▀█");
            this.chaine.ajouteChaine(this.posX, this.posY-4, "   ▀▀ ▀ ");
        }
        if (this.numApparence < 12){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "   ▄   ▄▀");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  █▀███▀█");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "█▀█ ████▀");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "  █▀▀▀▀▀█");
            this.chaine.ajouteChaine(this.posX, this.posY-4, "   ▀▀ ▀ ");
        }
        if (this.numApparence < 9){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "   ▄   ▄▀");
            this.chaine.ajouteChaine(this.posX, this.posY-1, " ▄█▀███▀█");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "█▀███████▀");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "  █▀▀▀▀▀█");
            this.chaine.ajouteChaine(this.posX, this.posY-4, "   ▀▀ ▀ ");
        }
        if (this.numApparence < 6){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "   ▄   ▄▀");
            this.chaine.ajouteChaine(this.posX, this.posY-1, " ▄█▀███▀█");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "█▀███████▀█");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "  █▀▀▀▀▀█ █");
            this.chaine.ajouteChaine(this.posX, this.posY-4, "   ▀▀ ▀ ");

        }
        if (this.numApparence < 3){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "  ▀▄   ▄▀");
            this.chaine.ajouteChaine(this.posX, this.posY-1, " ▄█▀███▀█▄");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "█▀███████▀█");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "  █▀▀▀▀▀█ █");
            this.chaine.ajouteChaine(this.posX, this.posY-4, "   ▀▀ ▀ ");
        }
        this.numApparence ++;   
    }

    @Override
    /** méthode destinée à faire évoluer dans l'espace l'alien */
    public void evolue(){
        if (this.vivant){
            if (this.nbTours < 75){
                if (this.right){
                    this.posX += 0.5;
                } else{
                    this.posX -= 0.5;
                }
                this.chaine.deplaceX(this.posX);

            } else{
                if (this.down)this.posY -= 2.2;
                else {this.posY += 2.2;}
                if (this.posY < 9) this.down = false;
                if (this.posY > 56) this.down = true;
                this.chaine.deplaceY(posY);
                this.right = !this.right;
                this.nbTours = 0;
            }
            this.nbTours++;
        }
        else {
            if (this.numApparence <= 36){
                this.changementApparence();
                this.posY -= 1.1;
                this.chaine.deplaceY(this.posY);
            }
        }
    }
}