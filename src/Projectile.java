public class Projectile{
    /** position en abscisse du projectile */
    double positionX;
    /** position en ordonnée du projectile */
    double positionY;

    /** constructeur de la classe
     * @param x double position en abscisse du projectile
     * @param y double position en ordonnée du projectile
     */
    public Projectile(double x, double y){
        this.positionX = x;
        this.positionY = y;
    }

    /** méthode d'obtention de la représentation graphique du projectile
     * @return EnsembleChaines ensemble des chaînes de caractères représentant le projectile
     */
    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines e = new EnsembleChaines();
        e.ajouteChaine(positionX, positionY, "^");
        return e;
    }

    /** méthode de déplacement du projectile */
    public void evolue(){this.positionY += 0.4;}
}