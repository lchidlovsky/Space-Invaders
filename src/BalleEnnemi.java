import java.util.Random;

public class BalleEnnemi{
    /** position en abscisse de la balle */
    private double positionX;
    /** position en ordonnée de la balle */
    private double positionY;
    /** apparence de la balle */
    private EnsembleChaines apparence;
    /** balle est en train d'exploser ou non */
    private boolean explosion;
    /** numéro de l'apparence de la balle */
    int numApparence;
    /** balle pouvant être supprimée ou non */

    /** constructeur de la classe
     * @param x double position en abscisse de la balle
     * @param y double position en ordonnée de la balle
     */
    public BalleEnnemi(double x, double y){
        this.positionX = x;
        this.positionY = y;
        this.apparence = new EnsembleChaines();
        this.apparence.ajouteChaine(this.positionX, this.positionY, "▄");
        this.explosion = false;
        this.numApparence = 0;
    }

    /** méthode d'obtention de la représentation graphique de la balle
     * @return EnsembleChaines ensemble des chaînes de caractères représentant le projectile
     */
    public EnsembleChaines getEnsembleChaines(){
        return this.apparence;
    }

    public boolean contient(int x, int y){
        return this.apparence.contient(x, y);
    }

    /** méthode de déplacement de la balle */
    public void evolue(){
        if (!this.explosion){
            this.positionY -= 0.33;
            this.apparence.deplaceY(this.positionY);
            if (this.positionY > 15 && this.positionY < 35){
                Random r = new Random();
                int k = r.nextInt(60);
                if (k == 0) this.explosion = true;
            }
            if (this.positionY < 15)this.explosion = true;
        }else{this.numApparence++;}

        if (this.numApparence !=0){
            EnsembleChaines e = new EnsembleChaines();
            if (this.numApparence == 4){
                e.ajouteChaine((int) this.positionX-1, (int) this.positionY+2,  "▄ ▄");
                e.ajouteChaine((int) this.positionX-2, (int) this.positionY+1, "▄   ▄");
                e.ajouteChaine((int) this.positionX-3, (int) this.positionY,  "▄     ▄");
                e.ajouteChaine((int) this.positionX-2, (int) this.positionY-1, "▄   ▄");
                e.ajouteChaine((int) this.positionX-1, (int) this.positionY-2,  "▄ ▄");
            }
            if (this.numApparence == 3){
                e.ajouteChaine((int) this.positionX-1, (int) this.positionY+2,  "▄▄▄");
                e.ajouteChaine((int) this.positionX-2, (int) this.positionY+1, "▄▄ ▄▄");
                e.ajouteChaine((int) this.positionX-3, (int) this.positionY,  "▄▄   ▄▄");
                e.ajouteChaine((int) this.positionX-2, (int) this.positionY-1, "▄▄ ▄▄");
                e.ajouteChaine((int) this.positionX-1, (int) this.positionY-2,  "▄▄▄");
            }
            if (this.numApparence == 2){
                e.ajouteChaine((int) this.positionX, (int) this.positionY+2, "▄");
                e.ajouteChaine((int) this.positionX-1, (int) this.positionY+1, "▄▄▄");
                e.ajouteChaine((int) this.positionX-2, (int) this.positionY, "▄▄ ▄▄");
                e.ajouteChaine((int) this.positionX-1, (int) this.positionY-1, "▄▄▄");
                e.ajouteChaine((int) this.positionX, (int) this.positionY-2, "▄");
            }
            if (this.numApparence == 1){
                e.ajouteChaine((int) this.positionX, (int)this.positionY+1, "▄");
                e.ajouteChaine((int) this.positionX-1, (int) this.positionY, "▄▄▄");
                e.ajouteChaine((int) this.positionX, (int) this.positionY-1, "▄");
            }
            this.apparence = e;
        }
    }
}