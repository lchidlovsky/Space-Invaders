public class UFO{
    
    /** position en abscisse de l'ufo */
    double posX;
    /** position en ordonnée de l'ufo */
    double posY;
    /** représentation visuelle de l'alien */
    protected EnsembleChaines chaine;
    /** alien en vie ou non */
    public boolean vivant;
    /** alein actif ou non */
    public boolean actif;
    /** numéro de l'apparence de l'alien */
    protected int numApparence;
    /** compteur de tours écoulés */
    protected int nbTours;
    /** variable déterminant s'il faut se déplacer vers la droite */
    protected boolean right;
    /** variable déterminant s'il faut se déplacer vers le bas */
    protected boolean down;

    /** constructeur de la classe
     * @param x double position en abscisse de l'alien
     * @param y double position en ordonnée de l'alien
     */
    protected UFO(double x, double y){
        this.posX = x;
        this.posY = y;
        this.numApparence = 0;
        this.nbTours = 0;
        this.right = true;
        this.down = true;
        this.chaine = new EnsembleChaines();
        this.vivant = true;
        this.actif = true;
    }

    /** méthode d'obtention de la représentation graphique de l'alien
     * @return EnsembleChaines ensemble des chaînes de caractères représentant l'alien
     */
    public EnsembleChaines getEnsembleChaines(){return this.chaine;}
    
    /** méthode permettant de déterminer si l'alien recouvre une position donnée de la zone de jeu
     * @param x int coordonnée en abscisse
     * @param y int coordonnée en ordonnée
     * @return boolean true si l'alien recouvre une position donnée de la zone de jeu
     */
    public boolean contient(int x, int y){
        return this.chaine.contient(x, y);
    }
    
    /** méthode de changement d'apparence */
    protected void changementApparence(){}

    /** méthode d'évolution  */
    public void evolue(){}

    /** méthode permettant de savoir si l'UFO veut tirer
     * @return boolean true si l'UFO veut tirer
     */
    public boolean veutTirer(){return false;}

    /** méthode d'ajout d'une ligne de caractères
     * @param caracteres String chaînes de caractères à ajouter
     */
    public void ajoutLigne(String caracteres){
        this.chaine.ajouteChaine(this.posX, this.posY-this.chaine.chaines.size(), caracteres);
    }
}