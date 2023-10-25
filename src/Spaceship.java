import java.util.Random;

public class Spaceship extends UFO{

    private boolean changementDirection;

    /** constructeur de la classe
     * @param x double position en abscisse de l'alien
     * @param y double position en ordonnée de l'alien
     */
    public Spaceship(double x, double y){
        super(x, y);
        this.right = x < 50;
        this.changementDirection = true;
        this.chaine.ajouteChaine(this.posX, this.posY-0, "    ▄▄████▄▄");
        this.chaine.ajouteChaine(this.posX, this.posY-1, "  ▄██████████▄");
        this.chaine.ajouteChaine(this.posX, this.posY-2, "▄██▄██▄██▄██▄██▄");
        this.chaine.ajouteChaine(this.posX, this.posY-3, "  ▀█▀  ▀▀  ▀█▀");
    }

    @Override
    /** méthode de changement d'apparence */
    protected void changementApparence(){
        if (this.numApparence == 10){
            this.chaine = new EnsembleChaines();
            this.actif = false;
        }
        if (this.numApparence < 10){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-2, "     █▄");
        }
        if (this.numApparence < 9){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-1, "     █");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "     █▄█");
        }
        if (this.numApparence < 8){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-1, "   ███ █");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "     █▄█");
        }
        if (this.numApparence < 7){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  ▄███ █");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "  █ ██▄██");
        }
        if (this.numApparence < 6){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  ▄██████");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "  █ ██▄██▄");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "    ▀");
        }
        if (this.numApparence < 5){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "     ▄█");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  ▄████████");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "  █ ██▄██▄██");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "    ▀");
        }
        if (this.numApparence < 4){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "     ▄█  █▄");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  ▄█████████");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "  █ ██▄██▄██▄█");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "    ▀  ▀");
        }
        if (this.numApparence < 3){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "     ▄█  █▄");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  ▄██████████");
            this.chaine.ajouteChaine(this.posX, this.posY-2, " ██ ██▄██▄██▄██");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "    ▀  ▀   ▀");
        }
        if (this.numApparence < 2){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "     ▄████▄");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  ▄██████████");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "▄██ ██▄██▄██▄██");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "  ▀ ▀  ▀▀  ▀");

        }
        if (this.numApparence < 1){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(this.posX, this.posY-0, "     ▄████▄▄");
            this.chaine.ajouteChaine(this.posX, this.posY-1, "  ▄██████████");
            this.chaine.ajouteChaine(this.posX, this.posY-2, "▄██▄██▄██▄██▄██▄");
            this.chaine.ajouteChaine(this.posX, this.posY-3, "  ▀ ▀  ▀▀  ▀█▀");
        }
        this.numApparence ++;   
    }
    
    @Override
    public boolean veutTirer(){
        Random r = new Random();
        int k = r.nextInt(6);
        return (k == 0 && this.posY == 57 && this.posX > 10 && this.posX < 74 && (this.posX < 35 || this.posX > 65));
    }

    /** méthode destinée à faire évoluer dans l'espace l'alien */
    @Override
    public void evolue(){
        if (this.vivant){

            //colonne descendante
            if ((int) this.posX == 44 && this.posY > 5){
                this.posY -= 0.55;
                this.chaine.deplaceY(this.posY);
            }
            //colonnes ascendantes
            else {if (((int) this.posX == 1 || (int) this.posX == 82) && this.posY < 57){
                    this.posY += 0.55;
                    this.chaine.deplaceY(this.posY);
                    if (this.changementDirection){
                        this.right = !this.right;
                        this.changementDirection = false;}
                    }
                    //déplacement horizontal
                    else{
                        this.changementDirection = true;
                        if (this.right){
                            this.posX += 0.7;
                        } else{
                            this.posX -= 0.7;
                        }
                        this.chaine.deplaceX(this.posX);
                    }}

        }else {
            if (this.numApparence < 11) this.changementApparence();
        }
    }
}