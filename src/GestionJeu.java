import java.util.List;
import java.util.ArrayList;

public class GestionJeu{
    /** hauteur de la fenêtre de jeu */
    private int hauteur;
    /** largeur de la fenêtre de jeu */
    private int largeur;
    /** ensemble des chaînes de caractère visibles en jeu */
    private EnsembleChaines chaines;
    /** coordonnée en abscisse du vaisseau */
    private double positionX;
    /** vaisseau utilisé par le joueur */
    private Vaisseau vaisseau;
    /** clignotement du vaisseau s'il est touché */
    private int clignotementVaisseau;
    /** score de la partie */
    private int score;
    /** nombre de vie du joueur */
    private int lifes;
    /** phase de jeu */
    private int phase;
    /** temps de recharge de l'arme */
    private int cooldown;
    /** liste des projectiles tirés par le joueur */
    private List<Projectile> bullets;
    /** liste des aliens et/ou messages présents à l'écran */
    private List<UFO> ufo;
    /** liste des tirs ennemis */
    private List<BalleEnnemi> shots;

    /** constructeur de la classe */
    public GestionJeu(){
        this.hauteur = 60;
        this.largeur = 100;

        this.positionX = 44;
        this.score = 0;
        this.lifes = 3;
        this.phase = 1;
        this.cooldown = 9;

        this.chaines = new EnsembleChaines();
        this.vaisseau = new Vaisseau(this.positionX);
        this.clignotementVaisseau = 0;

        this.bullets = new ArrayList<>();
        this.ufo = new ArrayList<>();
        this.shots = new ArrayList<>();
    }

    /** méthode d'obtention de la hauteur de la fenêtre de jeu
     * @return int hauteur de la fenêtre de jeu
     */
    public int getHauteur(){return this.hauteur;}
    
    /** méthode d'obtention de la largeur de la fenêtre de jeu
     * @return int largeur de la fenêtre de jeu
     */
    public int getLargeur(){return this.largeur;}
    
    /** méthode d'obtention de l'ensemble des chaînes de caractères devant figurer en jeu
     * @return EnsembleChaines liste de chaînes
     */
    public EnsembleChaines getChaines(){
        EnsembleChaines reunion = new EnsembleChaines();
        if (this.phase == 0) reunion.ajouteChaine(1, 59, "Score : "+this.score);
        if (this.phase > 3 && this.phase < 11){
            reunion.ajouteChaine(1, 59, "Score : "+this.score);
            reunion.ajouteChaine(16, 59, "Lives : "+this.lifes);
        }
        for (UFO ufo : this.ufo) reunion.union(ufo.getEnsembleChaines());

        for (Projectile projectile : this.bullets) reunion.union(projectile.getEnsembleChaines());
        for (BalleEnnemi balle : this.shots) reunion.union(balle.getEnsembleChaines());

        if (this.clignotementVaisseau % 2 == 0){reunion.union(this.vaisseau.getEnsembleChaines());}
        if (this.clignotementVaisseau > 0){this.clignotementVaisseau--;}

        return reunion; 
    }
    
    /** méthode de déplacement à gauche */
    public void toucheGauche(){
        if (!atteintBordure(this.positionX-3)){
            this.positionX -= 3;
            this.vaisseau.deplace(this.positionX);
        }
    }
    
    /** méthode de déplacement à droite */
    public void toucheDroite(){
        if (!atteintBordure(this.positionX+3)){
            this.positionX += 3;
            this.vaisseau.deplace(this.positionX);
        }
    }

    /** méthode de tir */
    public void toucheEspace(){
        if (this.cooldown == 9 && this.phase != 0 && this.phase !=  11){
            this.cooldown--;
            this.bullets.add(new Projectile(this.positionX + this.vaisseau.getEnsembleChaines().longueur()/2, 14));
        }
    }

    /** méthode d'obtention de la position du canon
     * @return double coordonnée du canon en abscisse 
     */
    public double positionCanon(){return this.positionX;}

    /** méthode permettant de savoir si le vaisseau peut se déplacer à une position donnée sans aller en dehors de la zone de jeu
     * @param posX double position en abscisse
     * @return boolean true si le vaisseau se retrouve, ne serait-ce que pour une petite partie, en dehors de la zone de jeu
     */
    private boolean atteintBordure(double posX){return posX < 2 || posX + vaisseau.getEnsembleChaines().longueur() > this.largeur -2;}

    /** méthode d'obtention du nombre d'aliens en vie restants
     * @return int le nombre d'aliens encore actifs
     */
    private int nbUfoActifRestant(){
        int ufoActif = this.ufo.size();
        for (UFO ufo : this.ufo){
            if (!ufo.actif) ufoActif--;
        }
        return ufoActif;
    }

    /** méthode faisant évoluer les projectiles dans l'espace tout en s'assurant de les faire disparaître s'ils atteignent la bordure */
    private void evolutionProjectiles(){
        List<Integer> projectilesToutEnHaut = new ArrayList<>();
        for (int i = 0; i < this.bullets.size(); i++){
            this.bullets.get(i).evolue();
            if (((int) this.bullets.get(i).positionY) == this.hauteur -2) projectilesToutEnHaut.add(i);
        }
        for (int i=0; i<projectilesToutEnHaut.size(); i++) {this.bullets.remove(projectilesToutEnHaut.get(i)-i);}
    }

    /** méthode faisant évoluer les tirs ennemis */
    private void evolutionTirsEnnemis(){
        List<Integer> ballesExploses = new ArrayList<>();
        for (int i=0; i < this.shots.size(); i++){
            this.shots.get(i).evolue();
            if (this.shots.get(i).numApparence == 5) ballesExploses.add(i);
        }
        for (int i=0; i<ballesExploses.size(); i++) {this.shots.remove(ballesExploses.get(i)-i);}
    }

    /** méthode destinée à faire tirer les ennemis */
    private void tirEnnemi(){
        if (this.phase == 10)
            for (UFO ufo : this.ufo){
                if (ufo.veutTirer()) this.shots.add(new BalleEnnemi(ufo.posX + ufo.getEnsembleChaines().longueur() / 2, ufo.posY-3));
            }
    }

    /** méthode d'attaque des aliens mettant à mort chaque alien touché et supprimant chaque projectile qui a fait mouche */
    private void attaqueUfo(){
        List<Integer> projectilesEnContact = new ArrayList<>();
        for (int i = 0; i < this.bullets.size(); i++){
            for (UFO ufo : this.ufo){
                if (ufo.actif && ufo.contient((int) this.bullets.get(i).positionX, (int) this.bullets.get(i).positionY)){
                    projectilesEnContact.add(i);
                    if (ufo.vivant){
                        ufo.vivant = false;
                        augmentationScore();
                    }}}}
        for (int i=0; i<projectilesEnContact.size(); i++){this.bullets.remove(projectilesEnContact.get(i)-i);}
    }

    /** méthode de suppression des balles en contacte avec des projectiles */
    private void contactBalleProjectile(){
        List<Integer> projectilesEnContact = new ArrayList<>();
        for (BalleEnnemi balle : this.shots){
            for (int i=0; i<this.bullets.size(); i++){
                if (balle.contient((int) this.bullets.get(i).positionX, (int) this.bullets.get(i).positionY)){
                    balle.numApparence++;
                    projectilesEnContact.add(i);
                }}}
        for (int k=0; k<projectilesEnContact.size(); k++) {this.bullets.remove(projectilesEnContact.get(k)-k);}
    }

    /** méthode de décrémentation des vies si le vaisseau est touché par l'une des balles */
    private void contactBalleVaisseau(){
        List<Integer> ballesEnContact = new ArrayList<>();
        for (int i=0; i<this.shots.size(); i++){
            for (int x=0; x <this.vaisseau.getEnsembleChaines().longueur(); x++){
                for (int y=15; y>8; y--){
                    if (this.shots.get(i).contient((int) this.positionX+x, y)){
                        if (!ballesEnContact.contains(i))ballesEnContact.add(i);
                        if (this.lifes >0){this.lifes--;}
                        this.clignotementVaisseau = 14;
        }}}}
        for (int i=0; i<ballesEnContact.size(); i++) {this.shots.remove(ballesEnContact.get(i)-i);}
    }

    /** méthode de vérification pour savoir si le vaisseau est en contact avec l'un des aliens
     * @return boolean renvoie true si l'un des aliens touche le vaisseau
     */
    private boolean contactALienVaisseau(){
        if(this.phase <= 3) return false;
        for (UFO ufo :this.ufo){
            for (int x=0; x <this.vaisseau.getEnsembleChaines().longueur(); x++){
                for (int y=15; y>8; y--){
                    if (ufo.contient((int) this.positionX+x, y)) return true;
                }
            }
        }
        return false;
    }

    /** méthode d'incrémentation du score de la partie */
    private void augmentationScore(){
        if(this.phase == 4) this.score +=2;
        if(this.phase == 6) this.score +=5;
        if(this.phase == 8) this.score +=9;
        if(this.phase == 10) this.score +=15;
    }

    /** méthode de changement de phase afin de faire avancer la partie */
    private void changementPhase(){

        if (this.lifes == 0){
            this.bullets.clear();
            this.shots.clear();
            this.ufo.clear();
            this.ufo.add(new Message(22, 38, true));
            this.ufo.get(0).ajoutLigne("   _________    __  _________   ____ _    ____________");
            this.ufo.get(0).ajoutLigne("  / ____/   |  /  |/  / ____/  / __ | |  / / ____/ __ \\");
            this.ufo.get(0).ajoutLigne(" / / __/ /| | / /|_/ / __/    / / / | | / / __/ / /_/ /");
            this.ufo.get(0).ajoutLigne("/ /_/ / ___ |/ /  / / /___   / /_/ /| |/ / /___/ _, _/");
            this.ufo.get(0).ajoutLigne("\\____/_/  |_/_/  /_/_____/   \\____/ |___/_____/_/ |_|");
        }
        else{
            Phase wave = new Phase(-13, 45, false, 35);
            wave.ajoutLigne("╦ ╦╔═╗╦  ╦╔═╗");
            wave.ajoutLigne("║║║╠═╣╚╗╔╝║╣");
            wave.ajoutLigne("╚╩╝╩ ╩ ╚╝ ╚═╝");
            
            if (this.phase == 10){
                this.bullets.clear();
                this.shots.clear();
                this.score += this.lifes*50;
                this.ufo.add(new Message(3, 47, true));
                this.ufo.get(this.ufo.size()-1).ajoutLigne("  ____                                  _           _         _    _                        _");
                this.ufo.get(this.ufo.size()-1).ajoutLigne(" / ___| ___   _ __    __ _  _ __  __ _ | |_  _   _ | |  __ _ | |_ (_)  ___   _ __   ___    | |");
                this.ufo.get(this.ufo.size()-1).ajoutLigne("| |    / _ \\ | '_ \\  / _` || '__|/ _` || __|| | | || | / _` || __|| | / _ \\ | '_ \\ / __|   | |");
                this.ufo.get(this.ufo.size()-1).ajoutLigne("| |___| (_) || | | || (_| || |  | (_| || |_ | |_| || || (_| || |_ | || (_) || | | |\\__ \\   |_|");
                this.ufo.get(this.ufo.size()-1).ajoutLigne(" \\____|\\___/ |_| |_| \\__, ||_|   \\__,_| \\__| \\__,_||_| \\__,_| \\__||_| \\___/ |_| |_||___/   (_)");
                this.ufo.get(this.ufo.size()-1).ajoutLigne("                     |___/ ");
                this.ufo.add(new Message(32, 32, false));
                this.ufo.get(this.ufo.size()-1).ajoutLigne("You have survived with a score of "+this.score+".");
            }
            if (this.phase == 9){
                this.bullets.clear();
                this.ufo.clear();
                this.ufo.add(new Spaceship(-16, 57));
                this.ufo.add(new Spaceship(130, 57));
                this.ufo.add(new Spaceship(370, 57));
                this.ufo.add(new Spaceship(-310, 57));
                this.ufo.add(new Spaceship(870, 57));
                this.ufo.add(new Spaceship(-760, 57));
            }
            if (this.phase == 8){
                this.lifes++;
                this.bullets.clear();
                this.ufo.add(wave);
                this.ufo.add(new Phase(100, 45, false, 52));
                this.ufo.get(this.ufo.size()-1).ajoutLigne("╔═╗╔═╗╦ ╦╦═╗");
                this.ufo.get(this.ufo.size()-1).ajoutLigne("╠╣ ║ ║║ ║╠╦╝");
                this.ufo.get(this.ufo.size()-1).ajoutLigne("╚  ╚═╝╚═╝╩╚═");
            }
            if (this.phase == 7){
                this.bullets.clear();
                this.ufo.clear();
                this.ufo.add(new Octopus(-10, 42));
                this.ufo.add(new Octopus(-24, 42));
                this.ufo.add(new Octopus(-38, 42));
                this.ufo.add(new Octopus(-52, 42));
                this.ufo.add(new Octopus(-66, 42));
                this.ufo.add(new Octopus(-80, 42));

                this.ufo.add(new Octopus(-196, 42));
                this.ufo.add(new Octopus(-210, 42));
                this.ufo.add(new Octopus(-224, 42));
                this.ufo.add(new Octopus(-238, 42));
                this.ufo.add(new Octopus(-252, 42));
                this.ufo.add(new Octopus(-266, 42));

                this.ufo.add(new Octopus(-672, 42));
                this.ufo.add(new Octopus(-686, 42));
                this.ufo.add(new Octopus(-700, 42));
                this.ufo.add(new Octopus(-714, 42));
                this.ufo.add(new Octopus(-728, 42));
                this.ufo.add(new Octopus(-742, 42));

                this.ufo.add(new Octopus(-858, 42));
                this.ufo.add(new Octopus(-872, 42));
                this.ufo.add(new Octopus(-886, 42));
                this.ufo.add(new Octopus(-900, 42));
                this.ufo.add(new Octopus(-914, 42));
                this.ufo.add(new Octopus(-928, 42));
            }
            if (this.phase == 6){
                this.lifes++;
                this.bullets.clear();
                this.ufo.add(wave);
                this.ufo.add(new Phase(100, 45, false, 52));
                this.ufo.get(this.ufo.size()-1).ajoutLigne("╔╦╗╦ ╦╦═╗╔═╗╔═╗");
                this.ufo.get(this.ufo.size()-1).ajoutLigne(" ║ ╠═╣╠╦╝║╣ ║╣ ");
                this.ufo.get(this.ufo.size()-1).ajoutLigne(" ╩ ╩ ╩╩╚═╚═╝╚═╝");
            }
            if (this.phase == 5){
                this.bullets.clear();
                this.ufo.clear();
                this.ufo.add(new Crab(34, 63));

                this.ufo.add(new Crab(4, 70));
                this.ufo.add(new Crab(64, 70));

                this.ufo.add(new Crab(34, 77));

                this.ufo.add(new Crab(4, 84));
                this.ufo.add(new Crab(64, 84));

                this.ufo.add(new Crab(34, 91));

                this.ufo.add(new Crab(4, 98));
                this.ufo.add(new Crab(64, 98));

                this.ufo.add(new Crab(4, 105));
                this.ufo.add(new Crab(34, 105));
                this.ufo.add(new Crab(64, 105));

                this.ufo.add(new Crab(4, 112));
                this.ufo.add(new Crab(34, 112));
                this.ufo.add(new Crab(64, 112));

                this.ufo.add(new Crab(4, 119));
                this.ufo.add(new Crab(34, 119));
                this.ufo.add(new Crab(64, 119));
                
                this.ufo.add(new Crab(34, 126));
            }
            if (this.phase == 4){
                this.lifes++;
                this.bullets.clear();
                this.ufo.add(wave);
                this.ufo.add(new Phase(100, 45, false, 52));
                this.ufo.get(this.ufo.size()-1).ajoutLigne("╔╦╗╦ ╦╔═╗");
                this.ufo.get(this.ufo.size()-1).ajoutLigne(" ║ ║║║║ ║");
                this.ufo.get(this.ufo.size()-1).ajoutLigne(" ╩ ╚╩╝╚═╝");
            }
            if (this.phase == 3){
                this.bullets.clear();
                this.ufo.clear();
                
                this.ufo.add(new Squid(11, 63));
                this.ufo.add(new Squid(27, 63));

                this.ufo.add(new Squid(3, 70));
                this.ufo.add(new Squid(19, 70));
                this.ufo.add(new Squid(35, 70));

                this.ufo.add(new Squid(11, 77));
                this.ufo.add(new Squid(27, 77));

                this.ufo.add(new Squid(3, 85));
                this.ufo.add(new Squid(19, 85));
                this.ufo.add(new Squid(35, 85));

                this.ufo.add(new Squid(11, 92));
                this.ufo.add(new Squid(27, 92));

                this.ufo.add(new Squid(3, 99));
                this.ufo.add(new Squid(19, 99));
                this.ufo.add(new Squid(35, 99));

                this.ufo.add(new Squid(11, 107));
                this.ufo.add(new Squid(27, 107));

                this.ufo.add(new Squid(3, 113));
                this.ufo.add(new Squid(19, 113));
                this.ufo.add(new Squid(35, 113));

                this.ufo.add(new Squid(11, 120));
                this.ufo.add(new Squid(27, 120));
            }
            if (this.phase == 2){
                this.bullets.clear();
                this.ufo.add(wave);
                this.ufo.add(new Phase(100, 45, false, 52));
                this.ufo.get(this.ufo.size()-1).ajoutLigne("╔═╗╔╗╔╔═╗");
                this.ufo.get(this.ufo.size()-1).ajoutLigne("║ ║║║║║╣");
                this.ufo.get(this.ufo.size()-1).ajoutLigne("╚═╝╝╚╝╚═╝");
            }
            if (this.phase == 1){
                this.ufo.add(new Message(7, 50, false));
                this.ufo.get(0).ajoutLigne("Welcome to a");
                this.ufo.add(new Message(13, 48, false));
                this.ufo.get(1).ajoutLigne("  _____");
                this.ufo.get(1).ajoutLigne(" / ____|");
                this.ufo.get(1).ajoutLigne("| (___  _ __   __ _  ___ ___");
                this.ufo.get(1).ajoutLigne(" \\___ \\| '_ \\ / _` |/ __/ _ \\");
                this.ufo.get(1).ajoutLigne(" ____) | |_) | (_| | (_|  __/");
                this.ufo.get(1).ajoutLigne("|_____/| .__/ \\__,_|\\___\\___|");
                this.ufo.get(1).ajoutLigne("       | |");
                this.ufo.get(1).ajoutLigne("       |_|");
                this.ufo.add(new Message(43, 48, false));
                this.ufo.get(2).ajoutLigne(" _____                     _");
                this.ufo.get(2).ajoutLigne("|_   _|                   | |");
                this.ufo.get(2).ajoutLigne("  | |  _ ____   ____ _  __| | ___ _ __ ___");
                this.ufo.get(2).ajoutLigne("  | | | '_ \\ \\ / / _` |/ _` |/ _ \\ '__/ __|");
                this.ufo.get(2).ajoutLigne(" _| |_| | | \\ V / (_| | (_| |  __/ |  \\__ \\");
                this.ufo.get(2).ajoutLigne("|_____|_| |_|\\_/ \\__,_|\\__,_|\\___|_|  |___/");
                this.ufo.add(new Message(70, 40, false));
                this.ufo.get(3).ajoutLigne("game by lchidlovsky");
                this.ufo.add(new Message(39, 27, false));
                this.ufo.get(4).ajoutLigne("Press SPACE to shoot !");
                this.ufo.add(new Message(29, 22, false));
                this.ufo.get(5).ajoutLigne("Use LEFT and RIGHT to move your spaceship.");
                this.ufo.add(new Message(36, 3, true));
                this.ufo.get(6).ajoutLigne("Destroy everything to start !");
            }
            this.phase ++;
        }
    }

    /** méthode déterminant ce qu'il se passe à chaque tour de jeu */
    public void jouerUnTour(){
        if (this.cooldown < 9) this.cooldown--;
        if (this.cooldown == 0) this.cooldown = 9;

        for (UFO ufo : this.ufo){ufo.evolue();}
        //on fait bouger les projectiles
        tirEnnemi();
        evolutionTirsEnnemis();
        evolutionProjectiles();
        contactBalleProjectile();
        //on fait disparaître les aliens touchés
        attaqueUfo();
        
        contactBalleVaisseau();
        //on vérifie que le vaisseau ne touche pas un alien
        if (this.phase != 0 && (contactALienVaisseau() || this.lifes == 0)){
            this.phase = 0;
            this.lifes = 0;
            this.clignotementVaisseau = 0;
            changementPhase();}
        
        //on détruit peu à peu le vaisseau si la partie est terminée
        if (this.lifes == 0) this.vaisseau.destruction();
        //si tous les ufo ont été éliminés
        if (this.lifes != 0 && nbUfoActifRestant() == 0 || ((this.phase > 0 && this.phase < 4) && nbUfoActifRestant() == 1)){
            changementPhase();}
    }
}