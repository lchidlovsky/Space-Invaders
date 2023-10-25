public class Vaisseau{
    /** coordonnée en abscisse du vaisseau dans la zone de jeu */
    private double posX;
    /** représentation visuelle du vaisseau */
    private EnsembleChaines chaine;

    /** stade d'intégrité du vaisseau */
    public int integrite;

    /** constructeur de la classe */
    public Vaisseau(double x){
        this.posX = x;
        this.integrite = 8;
        this.chaine = new EnsembleChaines();
        this.chaine.ajouteChaine(this.posX, 15, "     ▄");
        this.chaine.ajouteChaine(this.posX, 14, "   ▄███▄");
        this.chaine.ajouteChaine(this.posX, 13, "▄█ █████ █▄");
        this.chaine.ajouteChaine(this.posX, 12, "██ ▄███▄ ██");
        this.chaine.ajouteChaine(this.posX, 11, "███████████");
        this.chaine.ajouteChaine(this.posX, 10, "▀█████████▀");
        this.chaine.ajouteChaine(this.posX, 9, " ██ ▀▀▀ ██");
    }

    /** méthode de modification de l'emplacement du vaisseau en largeur
     * @param dx double nouvelle coordonnée en abscisse du vaisseau
     */
    public void deplace(double dx){
        if (this.integrite == 8){
            this.posX = dx;
            this.chaine.deplaceX(this.posX);}
    }

    /** méthode d'obtention de la représentation graphique duvaisseau
     * @return EnsembleChaines ensemble des chaînes de caractères représentant le vaisseau
     */
    public EnsembleChaines getEnsembleChaines(){
        return this.chaine;
    }

    /** méthode de changement d'apparence du vaisseau pour imager sa destruction */
    public void destruction(){
        if (integrite == 0){
            this.chaine = new EnsembleChaines();
        }
        if (integrite == 1){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(posX, 13, "     █");
            this.chaine.ajouteChaine(posX, 12, "   ▄ █");
            this.chaine.ajouteChaine(posX, 11, "    █");
        }
        if (integrite == 2){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(posX, 14, "     █");
            this.chaine.ajouteChaine(posX, 13, "    ██");
            this.chaine.ajouteChaine(posX, 12, "   ▄███");
            this.chaine.ajouteChaine(posX, 11, "  █ █ █");
            this.chaine.ajouteChaine(posX, 10, "      ██");
        }
        if (integrite == 3){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(posX, 14, "     █");
            this.chaine.ajouteChaine(posX, 13, "    ██");
            this.chaine.ajouteChaine(posX, 12, " █ ▄███");
            this.chaine.ajouteChaine(posX, 11, " ████ ███");
            this.chaine.ajouteChaine(posX, 10, "    ████");
            this.chaine.ajouteChaine(posX, 9, "      ▀ █");
        }
        if (integrite == 4){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(posX, 14, "    ██");
            this.chaine.ajouteChaine(posX, 13, "   ████  █");
            this.chaine.ajouteChaine(posX, 12, " █ ▄███▄ █");
            this.chaine.ajouteChaine(posX, 11, " ████████ ");
            this.chaine.ajouteChaine(posX, 10, "  █ █████");
            this.chaine.ajouteChaine(posX, 9, "  █   ▀ █");
        }
        if (integrite == 5){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(posX, 14, "    ██");
            this.chaine.ajouteChaine(posX, 13, " █ ████  █");
            this.chaine.ajouteChaine(posX, 12, "██ ▄███▄ ██");
            this.chaine.ajouteChaine(posX, 11, " ████████ ");
            this.chaine.ajouteChaine(posX, 10, "  ███████");
            this.chaine.ajouteChaine(posX, 9, "  █   ▀ █");
        }
        if (integrite == 6){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(posX, 14, "    ███");
            this.chaine.ajouteChaine(posX, 13, " █ █████ █");
            this.chaine.ajouteChaine(posX, 12, "██ ▄███▄ ██");
            this.chaine.ajouteChaine(posX, 11, " █████████ ");
            this.chaine.ajouteChaine(posX, 10, " ████████ ▀");
            this.chaine.ajouteChaine(posX, 9, "  █  ▀▀ █");
        }
        if (integrite == 7){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(posX, 15, "     ▄");
            this.chaine.ajouteChaine(posX, 14, "    ███▄");
            this.chaine.ajouteChaine(posX, 13, " █ █████ █");
            this.chaine.ajouteChaine(posX, 12, "██ ▄███▄ ██");
            this.chaine.ajouteChaine(posX, 11, " ██████████");
            this.chaine.ajouteChaine(posX, 10, " ████████ ▀");
            this.chaine.ajouteChaine(posX, 9, "  █  ▀▀ █");
        }
        if (integrite == 8){
            this.chaine = new EnsembleChaines();
            this.chaine.ajouteChaine(posX, 15, "     ▄");
            this.chaine.ajouteChaine(posX, 14, "    ███▄");
            this.chaine.ajouteChaine(posX, 13, "▄█ █████ █");
            this.chaine.ajouteChaine(posX, 12, "██ ▄███▄ ██");
            this.chaine.ajouteChaine(posX, 11, "███████████");
            this.chaine.ajouteChaine(posX, 10, " █████████▀");
            this.chaine.ajouteChaine(posX, 9, " ██ ▀▀▀ █");
        }
        
        this.integrite--;
    }
}