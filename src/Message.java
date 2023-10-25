public class Message extends UFO{
    /** clignotement du message */
    private boolean clignotant;

    /** constructeur de la classe
     * @param x double position en abscisse du message
     * @param y double position en abscisse du message
     * @param clignotant boolean message clignotant ou non
     * @param arriveeX coordonée en abscisse de la position d'arrivée du message
     */
    public Message(double x, double y, boolean clignotant){
        super(x, y);
        this.clignotant = clignotant;
    }

    /** méthode d'obtention de la représentation visuelle du message
     * @return EnsembleChaines ensemble des chaînes de caractères représentant le message
     */
    @Override
    public EnsembleChaines getEnsembleChaines(){
        if (this.clignotant){
            if (this.nbTours > 45){return new EnsembleChaines();}
            else{return this.chaine;}
            }
        return this.chaine;   
    }

    /** méthode d'évolution du message */
    @Override
    public void evolue(){
        if (!this.vivant){this.chaine = new EnsembleChaines(); this.actif = false;}
        else{
            this.nbTours ++;
            if (this.nbTours == 90) this.nbTours = 0;}
    }
}