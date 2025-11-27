package gwizady;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Gwizady {
    
    public static class Rektascensja implements Serializable{
        /// klasa zawierajaca pola opisujące Rektascensje gwiazdy implementuje interfejs Serializable niezbedny do serializacji
        int godziny, minuty, sekundy;
        
        ///gettery dla pol wykorzystywane do serializacji i deserializacji
        public int getGodziny() {
            return godziny;
        }
        
        public int getMinuty() {
            return minuty;
        }
        
        public int getSekundy() {
            return sekundy;
        }
        
        ///konstruktor bazowy potrzebny do serializacji
        public Rektascensja(){
            
        }
        
        public Rektascensja(int godziny, int minuty, int sekundy){
        this.godziny=Godziny(godziny);
        this.minuty = Minuty(minuty);
        this.sekundy = Sekundy(sekundy);
        }
        
        ///metody sprawdzające poprawność danych
        private int Godziny(int godziny){
            if(godziny>=0&&godziny<24)
                return godziny;
            else {
                throw new IllegalArgumentException("Błędna zakres godzinowy"); 
            }
        }
        
        private int Minuty(int minuty){
            if(minuty>=0&&minuty<60)
                return minuty;
            else {
                throw new IllegalArgumentException("Błędna zakres minutowy"); 
            }
        }
        
        private int Sekundy(int sekundy){
            if(sekundy>=0&&sekundy<60)
                return sekundy;
            else {
                throw new IllegalArgumentException("Błędna zakres sekundowy"); 
            }
        }
        
        ///nadpisanie metody toString() do wypisania wartosci klasy Rekstacensja
        @Override
        public String toString() {
            return godziny + "h " + minuty + "m " + sekundy + "s";
        }
    }
    
    public static class Deklinacja implements Serializable{
         /// klasa zawierajaca pola opisujące Deklinacje gwiazdy implementuje interfejs Serializable niezbedny do serializacji
        int stopnie, minuty, sekundy;
        
        ///gettery dla pol wykorzystywane do serializacji i deserializacji
        public int getStopnie() {
            return stopnie;
        }
        
        public int getMinuty() {
            return minuty;
        }
        
        public int getSekundy() {
            return sekundy;
        }
        
        public Deklinacja(int stopnie, int minuty, int sekundy, String polkula){
            this.stopnie = Stopnie(stopnie, polkula);
            this.minuty = Minuty(minuty);
            this.sekundy = Sekundy(sekundy);
        }
        
        ///konstruktor bazowy potrzebny do serializacji
        public Deklinacja(){
            
        }
        
         ///metody sprawdzające poprawność danych
        private int Stopnie(int stopnie, String polkula){///metoda sprawdza czy stopnie sa z odpowiedniego zakresu(dla pókuli południowej od 0 do 90 dla połnocnej od 0 do -90)
            int Stopnie=0;
            if(polkula.equals("PN")){
                if (stopnie >= 0 && stopnie <= 90) {
                    Stopnie = stopnie;
                } else {
                    throw new IllegalArgumentException("Błędna zakres"); 
                }
            }else if(polkula.equals("PD")){
                if (stopnie >=-90 && stopnie <= 0) {
                Stopnie = stopnie;
            } else {
                throw new IllegalArgumentException("Błędna zakres"); 
                }
            }
            return Stopnie;
        }
        
        /// metody sprawdzajace pozostale pola
        private int Minuty(int minuty){
            if(minuty>=0&&minuty<60)
                return minuty;
            else {
                throw new IllegalArgumentException("Błędna zakres minut w Deklinacji"); 
            }
        }
        
        private int Sekundy(int sekundy){
            if(sekundy>=0&&sekundy<60)
                return sekundy;
            else {
                throw new IllegalArgumentException("Błędna zakres sekund w Deklinacji"); 
            }
        }
        
        ///nadpisanie metody toString() do wypisania wartosci klasy Rekstacensja
        @Override
        public String toString() {
            return stopnie + "° " + minuty + "' " + sekundy + "\"";
        }
    }
    
    public static class Gwiazda implements Serializable{ ///klasa implementuje interfejs Serializable potrzebny do serializacji
        private String nazwa;
        private String nazwaKatalogowa;
        public Deklinacja deklinacja;
        public Rektascensja rektascensja;
        private double obserwowanaWielkoscGwiazdowa;
        private double absolutnaWielkoscGwiazdowa;
        private double odlegloscLataSwietlne;
        private String gwiazdozbiór;
        public String polkula;
        private double temperatura;
        private double masa;
        
        ///getery i setery potrzebne do pobrania lub ustawienia wartosci poza klasa
        public String getNazwa(){
            return nazwa;
        }
        public String getNazwaKatalogowa(){
            return nazwaKatalogowa;
        }
        public void setNazwaKatalogowa(String nazwaKatalogowa) {
            this.nazwaKatalogowa = nazwaKatalogowa;
        }
        public Deklinacja getDeklinacja(){
            return deklinacja;
        }
        public Rektascensja getRektascensja(){
            return rektascensja;
        }
        public double getObserwowanaWielkoscGwiazdowa(){
            return obserwowanaWielkoscGwiazdowa;
        }
        public double getAbsolutnaWielkoscGwiazdowa(){
            return absolutnaWielkoscGwiazdowa;
        }
        public double getOdlegloscLataSwietlne(){
            return odlegloscLataSwietlne;
        }
        public String getGwiazdozbiór(){
            return gwiazdozbiór;
        }
        public String getPolkula(){
            return polkula;
        }
        public double getTemperatura(){
            return temperatura;
        }
        public double getMasa(){
            return masa;
        }
        
        ///Metoda writeObject() potrzebna do Serializacji obiektow klasy Gwiazda
        private void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
            out.writeObject(deklinacja.getStopnie());
            out.writeObject(deklinacja.getMinuty());
            out.writeObject(deklinacja.getSekundy());
            out.writeObject(rektascensja.getGodziny());
            out.writeObject(rektascensja.getMinuty());
            out.writeObject(rektascensja.getSekundy());
        }
        
        ///Metoda readObject() potrzebna do dserializacji obiektow klasy Gwiazda
        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            int stopnie = (int) in.readObject();
            int minuty = (int) in.readObject();
            int sekundy = (int) in.readObject();
            deklinacja = new Deklinacja(stopnie, minuty, sekundy, polkula);
            int godziny = (int) in.readObject();
            minuty = (int) in.readObject();
            sekundy = (int) in.readObject();
            rektascensja = new Rektascensja(godziny, minuty, sekundy);
        }
        
        ///konstruktor domyślny potrzebny do serializacji
        public Gwiazda(){
            
        }
        
        public Gwiazda(String nazwa, Deklinacja deklinacja, Rektascensja rektascensja, double obserwowanaWielkoscGwiazdowa, double absolutnaWielkoscGwiazdowa, 
                double odlegloscLataSwietlne, String gwiazdozbiór, String polkula, double temperatura, double masa) {
            ///Wywołanie metody sprawdzającej czy nazwa każdej gwiazdy składa się z 3 dużych liter oraz 4 cyfr.
            this.nazwa = SprNazwe(nazwa);
            ///przypisanie do parametru deklinacja obiektu klasy Deklinacja
            this.deklinacja = deklinacja;
            ///przypisanie do parametru deklinacja obiektu klasy Deklinacja
            this.rektascensja = rektascensja;
            ///Wywolanie metody sprawdzajacej czy obserowana wielkosc gwiazdowa jest z zakresu od -26.74 do 15.00
            this.obserwowanaWielkoscGwiazdowa=ObserowanaWielkoscGwiazdowa(obserwowanaWielkoscGwiazdowa);
            ///Wywolanie metody wyliczajacej absolutna wielkosc gwiazdowa
            this.absolutnaWielkoscGwiazdowa=AbsolutnaWielkoscGwiazdowa(obserwowanaWielkoscGwiazdowa,odlegloscLataSwietlne);
            this.odlegloscLataSwietlne = odlegloscLataSwietlne;
            this.gwiazdozbiór = gwiazdozbiór;
            //Półkula - Sprawdzenie czy podana nazwa pasuje do wzorca  
            this.polkula = Polkula(polkula); 
            //Temperatura - Sprawdzenie czy podana temperatura jest większa niż minimalne 2000 stopni celsjusza
            this.temperatura = Temperatura(temperatura);
            //Masa - Sprawdzenie czy masa jest liczbą z dozwolonego zakresu
            this.masa=Masa(masa);
        }
        
        ///metody sprawdzajace poprawnosc danych
        private String SprNazwe(String nazwa){
            //Nazwa - Sprawdzenie czy nazwa każdej gwiazdy składa się z 3 dużych liter oraz 4 cyfr.
            String Nazwa="";
            if(nazwa.matches("[A-Z]{3}[0-9]{4}")){
                Nazwa = nazwa;
            }
            else {
                throw new IllegalArgumentException("Błędna nazwa gwiazdy"); 
            }
            return Nazwa;
        }
    
        private String Polkula(String polkula){///Półkula - Sprawdzenie czy podana nazwa pasuje do wzorca
            String Polkula="";
            if("PN".equals(polkula)||"PD".equals(polkula)){
                Polkula = polkula; 
            }else {
                throw new IllegalArgumentException("Błędna nazwa półkuli"); 
            }
            return Polkula;
        }
    
        private double ObserowanaWielkoscGwiazdowa(double obserwowanaWielkoscGwiazdowa){
            //wielkosc gwiazdowa - Sprawdzenie czy liczba jest z dozwolonego zakresu
            if(obserwowanaWielkoscGwiazdowa>=-26.74&&obserwowanaWielkoscGwiazdowa<15.00){
                return obserwowanaWielkoscGwiazdowa;
            }else {
                throw new IllegalArgumentException("Błędna zakres rektascensji"); 
            }
        }
    
        ///metoda oblicza Absotutna wielkosc gwiazdową na podstawie wzoru i parametrów; obserowanaWielkoscGwiazdowa i odlegloscLataSwietlne
        private double AbsolutnaWielkoscGwiazdowa(double obserwowanaWielkoscGwiazdowa, double odlegloscLataSwietlne){
            double r = odlegloscLataSwietlne/3.26;
            double M = obserwowanaWielkoscGwiazdowa-5*Math.log10(r)+5;
            return M;
        }
    
        ///Temperatura - Sprawdzenie czy podana temperatura jest większa niż minimalne 2000 stopni celsjusza
        private double Temperatura(double temperatura){
            double temp;
            if(temperatura>2000){
                temp = temperatura;
            }else {
                throw new IllegalArgumentException("za niska temperatura gwiazdy"); 
            }
            return temp;
        }
        
        ///Metoda sprawdzajaca czy masa miesci sie w dozwolonym zakresie
        private double Masa(double masa){
            double Masa;
            if(masa>=0.1&&masa<=50){
                Masa = masa;  
            }else {
                throw new IllegalArgumentException("nieprawidłowa masa gwiazdy"); 
            }
            return Masa;
        }       
    }     
    
    ///Metoda Wypisz() przechodzi po liście i wypisuje wartosci pol za pomoca getterów 
    public static void Wypisz(List<Gwiazda> listaGwiazd){
        for (Gwiazda gwiazda : listaGwiazd) {
            System.out.println("Nazwa: " + gwiazda.getNazwa() + ", Nazwa katalogowa: " + gwiazda.getNazwaKatalogowa() + ", Deklinacja: " + gwiazda.getDeklinacja() + ", Rektascensja: " + gwiazda.getRektascensja() + 
                    ", Obserwowana wielkość gwiazdowa: " + gwiazda.getObserwowanaWielkoscGwiazdowa() + ", Absolutna wielkość gwiazdowa: " + gwiazda.getAbsolutnaWielkoscGwiazdowa() + ", Odległość: " 
                    + gwiazda.getOdlegloscLataSwietlne() + ", Gwiazdozbiór: " + gwiazda.getGwiazdozbiór() + ", Półkula: " + gwiazda.getPolkula() + ", Temperatura: " + gwiazda.getTemperatura()+ ", Masa: " 
                    + gwiazda.getMasa());
        }
    }
    
    ///metoda zapisujaca liste obiektów klasy gwiazda 
    public static void ZapiszDoPliku(List<Gwiazda> listaGwiazd){
        try {
            FileOutputStream fos = new FileOutputStream("listaGwiazd.txt"); 
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaGwiazd);
            oos.close();
            fos.close();
            System.out.println("Liste zapisano do pliku: listaGwiazd.txt");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    ///metoda pobiera obiekty typu giwazda z pliku i zapisuje je do listy przez co brak koniecznosci wczytywania listy gwiazd z pliku przy kazdym sortowaniu
    public static List<Gwiazda> OdczytZPliku(){
        List<Gwiazda> listaGwiazd = null;
        try (FileInputStream fileIn = new FileInputStream("listaGwiazd.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn)) {
            listaGwiazd = (List<Gwiazda>) in.readObject();
            //Wypisz(listaGwiazd);
        } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
        }
        return listaGwiazd;
    }
    ///metoda wczytuje do listy obiekty z pliku i dodaje je do nowej listy po czym iteruj po tych obiektach i jeśli nazwa nie pasuje do podanej podczas wywolania metody dodaja ja do nowej listy i zapisuje ją do pliku
    public static void UsunObiekt(String nazwaKatalogowa) {
            List<Gwiazda> listaGwiazd = OdczytZPliku();
            List<Gwiazda> newList = new ArrayList<>();
            for (Gwiazda gwiazda : listaGwiazd) {
                if (!gwiazda.getNazwaKatalogowa().equals(nazwaKatalogowa)) {
                    dodajGwiazdeDoListy(newList, gwiazda);
                }
            }
            ZapiszDoPliku(newList);
            System.out.println("Obiekt o nazwie katlogowej: " + nazwaKatalogowa + " has been removed from the file.");
    }
    
    ///metoda przyporzadkowuje nazwe katalogowa na podsatwie zdefiniowanej tablicy liter greckiego alfabetu
    public static String PrzyporzadkujNazweKatalogowa(String gwiazdozbiór, int iloscGwiazd) {
            String[] literyGreckie = {"alpha", "beta", "gamma", "delta", "epsilon", "zeta", "eta", "theta", "iota", "kappa", "lambda", "mu", "nu", "xi", "omicron", "pi", "rho", "sigma", "tau", "upsilon", "phi", "chi", "psi", "omega"};
            int indeks = iloscGwiazd % literyGreckie.length;
            return literyGreckie[indeks] + " " + gwiazdozbiór;
    }
    
    ///metoda dodaje gwiazde do list i przyporzadkowuje jej nazwe katalogowa na podstawie ilosci gwiazd w gwiazdozbiorze
    public static void dodajGwiazdeDoListy(List<Gwiazda> lista, Gwiazda gwiazda) {
        String nazwaGwiazdozbioru = gwiazda.gwiazdozbiór;
        String nazwaKat = "alpha " + nazwaGwiazdozbioru; 
        int iloscGwiazd = 0;
        for (Gwiazda istniejacaGwiazda : lista) { ///petla iteruje po obiektach w liscie kazda gwiazda z danego gwiazdozbioru zwieksza wartosc iloscGwiazd o 1
            if (istniejacaGwiazda.gwiazdozbiór.equals(nazwaGwiazdozbioru)) {
                    iloscGwiazd++;
            }
        }
        if (iloscGwiazd > 0) { /// jesli zmienna iloscGwiazd jest większa niż 0 wywołujemy funkcje Przyporzakowujaca nazwe katalogowa w przeciwnym razie przypisywana jest domyslna/pierwsza nazwa katalogowa
            nazwaKat = PrzyporzadkujNazweKatalogowa(nazwaGwiazdozbioru, iloscGwiazd);
        }
            gwiazda.nazwaKatalogowa = nazwaKat; ///przypisanie nazwy katalogowej do pola klasy gwiazda
            lista.add(gwiazda); /// dodanie nowej gwiazdy
    }
    
    ///Ponizsze metody wczytuja liste gwiazd i wyszukują gwiazdy o zdanych parametrach po czym dodają je do nowej listy i wypisuja za pomoca metody Wypisz()
    public static void WyszukajZGwiazdozbioru(String gwiazdozbior, List<Gwiazda> listaGwiazd){
            List<Gwiazda> newList = new ArrayList<>();
            for(Gwiazda gwiazda : listaGwiazd) {///sprawdza czy gwiazda znajduje sie w podanym gwiazdozbiorze i jesli tak to dodaje ja do listy
                if(gwiazda.getGwiazdozbiór().equals(gwiazdozbior)){
                    newList.add(gwiazda);
                }
            }
            Wypisz(newList);
    }
    
    public static void WyszukajOdleglosc(int parsek, List<Gwiazda> listaGwiazd){
            List<Gwiazda> newList = new ArrayList<>();
            for(Gwiazda gwiazda : listaGwiazd) {///sprawdza czy gwiazda jest nie dalej niż ilosc parsekow podana podczas wywolania metody
                if(gwiazda.getOdlegloscLataSwietlne()/3.26 < parsek){
                    newList.add(gwiazda);
                }
            }
            Wypisz(newList);
    }
    
    public static void WyszukajTemperatura(double tempOd, double tempDo, List<Gwiazda> listaGwiazd){
            List<Gwiazda> newList = new ArrayList<>();
            for(Gwiazda gwiazda : listaGwiazd) { ///sprawdza czy temperatura gwiazdy miesci sie w zakresie podanym podczas wywolania metody
                if(gwiazda.getTemperatura() >= tempOd && gwiazda.getTemperatura()<=tempDo){
                    newList.add(gwiazda);
                }
            }
            Wypisz(newList);
    }
    
    public static void WyszukajWielkoscGwiazdowa(double wielkoscOd, double wielkoscDo, List<Gwiazda> listaGwiazd){
            List<Gwiazda> newList = new ArrayList<>();
            for(Gwiazda gwiazda : listaGwiazd) { ///sprawdza czy gwiazda miesci sie w zakresie podanym podczas wywolania metody
                if(gwiazda.getAbsolutnaWielkoscGwiazdowa() >= wielkoscOd && gwiazda.getAbsolutnaWielkoscGwiazdowa()<=wielkoscDo){
                    newList.add(gwiazda);
                }
            }
            Wypisz(newList);
    }
    
    public static void WyszukajZPolkuli(String polkula, List<Gwiazda> listaGwiazd){
            List<Gwiazda> newList = new ArrayList<>();
            for(Gwiazda gwiazda : listaGwiazd) { ///wypisuje gwiazdy z danej pókuli
                if(polkula.equals(gwiazda.getPolkula())){
                    newList.add(gwiazda);
                }
            }
            Wypisz(newList);
    }
    
    public static void WyszukajSupernowe(List<Gwiazda> listaGwiazd){
            List<Gwiazda> newList = new ArrayList<>();
            for(Gwiazda gwiazda : listaGwiazd) { ///sprawdza czy gwiazda może zostać supernową jeśli tak dodaje ja do nowej listy i wypisuje 
                if(gwiazda.getMasa()>1.44){
                    newList.add(gwiazda);
                }
            }
            Wypisz(newList);
    }
    
    public static void main(String[] args) {
        List<Gwiazda> listaGwiazd = new ArrayList<>();
        Gwiazda gwiazda1 = new Gwiazda("XYZ5678", new Deklinacja(50, 30, 55, "PN"), new Rektascensja(22, 45, 30),
                                -4.56, -12.34, 345.67, "Wolarza", "PN", 7500, 4.5);
        dodajGwiazdeDoListy(listaGwiazd, gwiazda1);
        Gwiazda gwiazda2 = new Gwiazda("ABC1234", new Deklinacja(-50, 45, 55, "PD"), new Rektascensja(22, 45, 30),
                                -5.67, -13.45, 456.78, "Małpy", "PD", 6500, 1.45);
        dodajGwiazdeDoListy(listaGwiazd, gwiazda2);
        Gwiazda gwiazda3 = new Gwiazda("ARK4201", new Deklinacja(-50, 45, 55, "PD"), new Rektascensja(22, 45, 30),
                                -2.67, -11.45, 46.78, "Wolarza", "PD", 3000, 1);
        dodajGwiazdeDoListy(listaGwiazd, gwiazda3);
        ///Stworzenie listy gwiazd i dodanie do niej przykładowych gwiazd
        ZapiszDoPliku(listaGwiazd);///zapisanie listy do pliku
        List<Gwiazda> nowaLista = OdczytZPliku();///przypisanie odczytanej listy obiektów klasy gwiazda do nowej listy
        Wypisz(nowaLista);///wypisanie gwiazd
        WyszukajSupernowe(nowaLista);
        UsunObiekt("alpha Wolarza");///usuniecie gwiazdy 
        List<Gwiazda> nowaLista2 = OdczytZPliku();///ponowne wczytanie gwiazd z pliku
        WyszukajZGwiazdozbioru("Wolarza",nowaLista2);
        WyszukajOdleglosc(50, nowaLista2);
        WyszukajTemperatura(3000,6500, nowaLista2);
        WyszukajWielkoscGwiazdowa(5,10,nowaLista2);
        WyszukajZPolkuli("PD", nowaLista2);
        WyszukajSupernowe(nowaLista2);
        ///wyszukiwanie gwiazd po zdaanych parametrach
    }   
}
