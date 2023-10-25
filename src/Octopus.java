public class Octopus extends UFO{
    
    /** variable déterminant si le paterne doit être changer ou non */
    private boolean changementPaterne;
    /** numéro du paterne à adopter */
    private int paterne;

    /** constructeur de la classe
     * @param x double position en abscisse de l'alien
     * @param y double position en ordonnée de l'alien
     */
    public Octopus(double x, double y){
        super(x, y);
        this.changementPaterne = true;
        this.paterne = 0;
        this.chaine.ajouteChaine(this.posX, this.posY-0, "   ▄██▄");
        this.chaine.ajouteChaine(this.posX, this.posY-1, " ▄██████▄");
        this.chaine.ajouteChaine(this.posX, this.posY-2, "███▄██▄███");
        this.chaine.ajouteChaine(this.posX, this.posY-3, "  ▄▀▄▄▀▄");
        this.chaine.ajouteChaine(this.posX, this.posY-4, " ▀ ▀  ▀ ▀");
    }

    @Override
    /** méthode de changement d'apparence */
    protected void changementApparence(){
        if (this.numApparence == 9){
            this.chaine = new EnsembleChaines();
            this.actif = false;
        }
        if (this.numApparence < 9){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-2, "     █▄");
        }
        if (this.numApparence < 8){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-1, "   █");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "     █▄");
        }
        if (this.numApparence < 7){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  ██ █");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "   ▄ █▄");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "   ▀  ▀");
        }
        if (this.numApparence < 6){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  ██ █");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "   ▄ █▄");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "   ▀  ▀");
        }
        if (this.numApparence < 5){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  ██ ███");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "   ▄██▄");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "  ▄▀▄ ▀");
        }
        if (this.numApparence < 4){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "      ▄");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  ██████");
            this.chaine.ajouteChaine(this.posX, this.posY-2, " █ ▄██▄");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "  ▄▀▄ ▀");
        }
        if (this.numApparence < 3){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "   ▄█ ▄");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  ██████");
            this.chaine.ajouteChaine(this.posX, this.posY-2, " █ ▄██▄█");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "  ▄▀▄ ▀▄");
            this.chaine.ajouteChaine(this.posX, this.posY-4, "      ▀ ");
        }
        if (this.numApparence < 2){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "   ▄██▄");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  ██████");
            this.chaine.ajouteChaine(this.posX, this.posY-2, " ██▄██▄██");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "  ▄▀▄ ▀▄");
            this.chaine.ajouteChaine(this.posX, this.posY-4, " ▀    ▀ ");

        }
        if (this.numApparence < 1){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "   ▄██▄");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  ██████▄");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "███▄██▄██");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "  ▄▀▄▄▀▄");
            this.chaine.ajouteChaine(this.posX, this.posY-4, " ▀ ▀  ▀ ");
        }
        this.numApparence ++;   
    }

    /** méthode destinée à faire évoluer dans l'espace l'alien */
    @Override
    public void evolue(){
        if (this.vivant){

            if (this.paterne == 0){
            //colonnes descendantes
            if ((int) this.posX == 3)this.right = true;
            if (((int) this.posX == 3 || (int) this.posX == 59) && ((this.posY > 6) || this.down) && this.right){
                this.posY -= 0.6;
                this.chaine.deplaceY(this.posY);
                this.down = false;
            }
            //colonne ascendantes
            else {if (((int) this.posX == 31) && ((this.posY < 51) || this.down) && this.right){
                this.posY += 0.6;
                this.chaine.deplaceY(this.posY);
                this.down = false;}
                //dernière colonne
                else{if (((int) this.posX == 87) && ((this.posY < 57) || this.down)){
                    this.posY += 0.6;
                    this.chaine.deplaceY(this.posY);
                    this.down = false;
                    this.right = false;
                    this.changementPaterne = true;
                    }
                    //déplacement horizontal
                    else{
                        if ((int) (this.posX -3) % 28 != 0) this.down = true;
                        if (this.right){
                            this.posX += 0.7;
                        } else{
                            this.posX -= 0.7;
                            if (this.changementPaterne){this.changementPaterne = false; this.paterne++; if (this.paterne == 2)this.paterne=0;}
                        }
                        this.chaine.deplaceX(this.posX);
                    }}}}


            else{
            //colonnes descendantes
            if ((int) this.posX == 16)this.right = true;
            if (((int) this.posX == 16 || (int) this.posX == 72) && ((this.posY > 6) || this.down) && this.right){
                this.posY -= 0.6;
                this.chaine.deplaceY(this.posY);
                this.down = false;
            }
            //colonne ascendantes
            else {if (((int) this.posX == 44) && ((this.posY < 51) || this.down) && this.right){
                this.posY += 0.6;
                this.chaine.deplaceY(this.posY);
                this.down = false;}
                //dernière colonne
                else{if (((int) this.posX == 87) && ((this.posY < 57) || this.down)){
                    this.posY += 0.6;
                    this.chaine.deplaceY(this.posY);
                    this.down = false;
                    this.right = false;
                    this.changementPaterne = true;
                    }
                    //déplacement horizontal
                    else{
                        if ((int) (this.posX -16) % 28 != 0) this.down = true;
                        if (this.right){
                            this.posX += 0.7;
                        } else{
                            this.posX -= 0.7;
                            if (this.changementPaterne){this.changementPaterne = false; this.paterne++; if (this.paterne == 2)this.paterne=0;}
                        }
                        this.chaine.deplaceX(this.posX);
                    }}}

        }} else {
            if (this.numApparence <= 9) this.changementApparence();
        }
    }
}