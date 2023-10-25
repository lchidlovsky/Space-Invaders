public class Squid extends UFO{
    
    /** constructeur de la classe
     * @param x double position en abscisse de l'alien
     * @param y double position en ordonnée de l'alien
     */
    public Squid(double x, double y){
        super(x, y);
        this.chaine.ajouteChaine(this.posX, this.posY-0, " ▄▄████▄▄ ");
        this.chaine.ajouteChaine(this.posX, this.posY-1, "██████████");
        this.chaine.ajouteChaine(this.posX, this.posY-2, "██▄▄██▄▄██");
        this.chaine.ajouteChaine(this.posX, this.posY-3, " ▄▀▄▀▀▄▀▄ ");
        this.chaine.ajouteChaine(this.posX, this.posY-4,  "▀        ▀");
    }

    @Override
    /** méthode de changement d'apparence */
    protected void changementApparence(){
        if (this.numApparence == 12){
            this.chaine = new EnsembleChaines();
            this.actif = false;
        }
        if (this.numApparence < 12){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-1, "     █   ");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "  ▄     █");
        }
        if (this.numApparence < 10){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "    █");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "     ██   ");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "  ▄▄   ▄█");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "    ▀  ▀  ");
        }
        if (this.numApparence < 8){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "    █ █▄  ");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "█    ██  █");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "  ▄▄  ▄▄█");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "    ▀ ▄▀  ");
        }
        if (this.numApparence < 6){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "  ▄██ █▄  ");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "█  ████  █");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "  ▄▄██▄▄██");
            this.chaine.ajouteChaine(this.posX, this.posY-3, " ▄ ▄▀ ▄▀  ");
        }
        if (this.numApparence < 4){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, " ▄▄████▄  ");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "██ ████  █");
            this.chaine.ajouteChaine(this.posX, this.posY-2, " █▄▄██▄▄██");
            this.chaine.ajouteChaine(this.posX, this.posY-3, " ▄ ▄▀▀▄▀  ");
            this.chaine.ajouteChaine(this.posX, this.posY-4,  "         ▀");

        }
        if (this.numApparence < 2){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, " ▄▄████▄▄ ");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "███████ ██");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "██▄▄██▄▄██");
            this.chaine.ajouteChaine(this.posX, this.posY-3, " ▄ ▄▀▀▄▀  ");
            this.chaine.ajouteChaine(this.posX, this.posY-4,  "▀        ▀");
        }
        this.numApparence ++;   
    }

    /** méthode destinée à faire évoluer dans l'espace l'alien */
    @Override
    public void evolue(){
        if (this.vivant){
            if (this.nbTours < 65){
                if (this.right){
                    this.posX += 0.8;
                } else{
                    this.posX -= 0.8;
                }
                this.chaine.deplaceX(this.posX);

            } else{
                if (this.down)this.posY -= 3;
                else {this.posY += 3;}
                if (this.posY < 9) this.down = false;
                if (this.posY > 56) this.down = true;
                this.chaine.deplaceY(posY);
                this.right = !this.right;
                this.nbTours = 0;
            }
            this.nbTours++;
        }
        else {
            if (this.numApparence <= 13) this.changementApparence();
        }
    }
}