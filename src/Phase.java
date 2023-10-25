import java.lang.Math;

public class Phase extends Message{
    /** coordonée en abscisse de la position d'arrivée du message de phase */
    private double arriveeX;

    /** constructeur de la classe
     * @param x double position en abscisse du message de phase
     * @param y double position en abscisse du message de phase
     * @param clignotant boolean message clignotant ou non
     * @param arriveeX coordonée en abscisse de la position d'arrivée du message de phase
     */
    public Phase(double x, double y, boolean clignotant, double arriveeX){
        super(x, y, clignotant);
        this.arriveeX = arriveeX;
    }

    /** méthode d'évolution du message de phase*/
    @Override
    public void evolue(){
        if (!this.vivant){this.chaine = new EnsembleChaines(); this.actif = false;}
        this.nbTours ++;
        if (this.nbTours == 90) this.nbTours = 0;
        
        if (this.posX != this.arriveeX){
            this.posX = this.posX + ((this.arriveeX - this.posX) / Math.abs(this.arriveeX - this.posX));
            this.chaine.deplaceX(this.posX);
        }
    }
}