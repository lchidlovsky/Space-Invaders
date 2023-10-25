public class ChainePositionnee{
    double x,y;
    String c;
    /** constructeur de la classe
     * @param a double position en largeur de la chaîne
     * @param b double position en hauteur de la chaîne
     * @param d String ladite chaîne
     */
    public ChainePositionnee(double a,double b, String d){x=a; y=b; c=d;}
    /** méthode de mise à jour de la position en largeur de la chaîne
     * @param a double la nouvelle valeur de la position en largeur
     */
    public void deplaceX(double a){this.x = a;}

    /** méthode de mise à jour de la position en hauteur de la chaîne
     * @param a double la nouvelle valeur de la position en hauteur
     */
    public void deplaceY(double b){this.y = b;}
}
