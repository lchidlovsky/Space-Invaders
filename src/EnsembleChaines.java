import java.util.List;
import java.util.ArrayList;

public class EnsembleChaines {
    /** liste de chaîne de caractères */
    List<ChainePositionnee> chaines;
    
    /** contstructeur de la classe */
    public EnsembleChaines(){chaines= new ArrayList<ChainePositionnee>(); }
    
    /** méthode d'ajout d'une nouvelle chaîne dans la liste
     * @param x int position en largeur de la nouvelle chaîne
     * @param y int position en hauteur de la nouvelle chaîne
     * @param c String la nouvelle chaîen en question
     */
    public void ajouteChaine(double x, double y, String c){
        chaines.add(new ChainePositionnee(x,y,c));
    }
    
    /** méthode de fusion avec une autre liste de chaînes
     * @param e EnsembleChaines liste de chaînes avec laquelle fusionner
     */
    public void union(EnsembleChaines e){
        for(ChainePositionnee c : e.chaines)
            chaines.add(c);
    }
    
    /** méthode de déplacement des chaînes de caractères en largeur
     * @param dx double longueur du déplacement
     */
    public void deplaceX(double dx){
        for(ChainePositionnee ch : this.chaines)
            ch.deplaceX(dx);
    }
    
    /** méthode de déplacement des chaînes de caractères en hauteur
     * @param dx double longueur du déplacement
     */
    public void deplaceY(double dy){
        int n = 0;
        for(ChainePositionnee ch : this.chaines){
            ch.deplaceY(dy-n);
            n++;
        }
    }
    
    /** méthode permettant de savoir si une des chaînes des caractères se situe à un emplacement donné
     * @param x int position en largeur dudit emplacement
     * @param y int position en hauteur dudit emplacement
     * @return boolean true si l'emplacement donné est occupé par une chaîne de caractère
     */
    public boolean contient(int x, int y){
        if (this.chaines.isEmpty()){return false;}
        if (this.chaines.size() == 1){
            ChainePositionnee ch = this.chaines.get(0);
            return ((int) ch.y) == y && ((int) ch.x <= x && x < (int) (ch.x + ch.c.length()));
        }
        if (this.chaines.get(0).y < y || y <= (this.chaines.get(0).y - this.chaines.size())){return false;}
        for (ChainePositionnee ch : this.chaines){
            for (int i = 0; i < ch.c.length(); i++){
                if(ch.c.charAt(i) != ' ' && (int) (ch.x+i) == (int) x){return true;}
            }
        }
        return false;
    }

    /** méthode permettant de déterminer la taille en largeur de l'ensemble des chaînes de caractères
     * @return int largeur de l'ensemble des chaînes de caractères
     */
    public int longueur(){
        int longueur = 0;
        for (ChainePositionnee ch: this.chaines){
            if (ch.c.length() > longueur) longueur = ch.c.length();
        }
        return longueur;
    }
}
