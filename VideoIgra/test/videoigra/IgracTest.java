/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoigra;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static videoigra.Igrac.Stanje.*;

/**
 *
 * @author Vujos Petrovic
 */
public class IgracTest {
    
    Oruzje oruzje = mock(Oruzje.class);
    Odeca odeca = mock(Odeca.class);
    Magija magija = mock(Magija.class);
    
    ArrayList<Oruzje> listaOruzja = new ArrayList<>();
    ArrayList<Odeca> listaOdece;
    ArrayList<Magija> listaMagije;
    
    Igrac igrac;
    Igrac meta;
    
    public IgracTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        listaOruzja = new ArrayList<>();
        listaOdece = new ArrayList<>();
        listaMagije = new ArrayList<>();
        igrac = new Igrac("Igrac", 10, 10, 10, 10, DEFANZIVNO, listaOruzja, listaOdece, listaMagije);
        meta = new Igrac("Meta", 10, 10, 10, 10, DEFANZIVNO, listaOruzja, listaOdece, listaMagije);
    }
    
    @After
    public void tearDown() {
    }
    /*
            TESTIRANJE mETODOM CRNE KUTIJE
                KLASE EKVIVALENCIJE 
                    NAPADI IGRACA
    */
    @Test
    public void napadniIgracaKE1() {  
        System.out.println("Napadni igraca KE - energija < 20");
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(10);
        igrac.setSnaga(10);
        igrac.setStanje(DEFANZIVNO);
        
        double expResult = (0+2*igrac.getSnaga())*0.8;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaKE2() {  
        System.out.println("Napadni igraca KE - energija > 20");
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(30);
        igrac.setSnaga(10);
        igrac.setStanje(DEFANZIVNO);
        double expResult = (30/2+2*igrac.getSnaga())*0.8;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaKE3() {  
        System.out.println("Napadni igraca KE - energija > 20 - energija = energija - 21");
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(30);
        igrac.setSnaga(10);
        igrac.setStanje(DEFANZIVNO);
        double expResult = igrac.getEnergija()-21;
        igrac.napadniIgraca(0, meta);
        double result = igrac.getEnergija();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaKE4() {  
        System.out.println("Napadni igraca KE - energija < 20 - energija = energija");
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(10);
        igrac.setSnaga(10);
        igrac.setStanje(DEFANZIVNO);
        double expResult = igrac.getEnergija();
        igrac.napadniIgraca(0, meta);
        double result = igrac.getEnergija();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaKE5() {  
        System.out.println("Napadni igraca KE - snaga < oruzje.getPotrebnaSnaga()");
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(30);
        igrac.setSnaga(10);
        igrac.setStanje(DEFANZIVNO);
        double expResult = (oruzje.getPotrebnaSnaga()/2+2*igrac.getSnaga())*0.8;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaKE6() {  
        System.out.println("Napadni igraca KE - snaga > oruzje.getPotrebnaSnaga()");
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(30);
        igrac.setSnaga(50);
        igrac.setStanje(DEFANZIVNO);
        double expResult = (oruzje.getPotrebnaSnaga()+2*igrac.getSnaga())*0.8;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    /*  
                GRANICNE VREDNOSTI
                  NAPADI IGRACA
    */
    
    @Test
    public void napadniIgracaGV1() {  
        System.out.println("Napadni igraca GV - Energja <= 20");
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(19);
        igrac.setSnaga(10);
        igrac.setStanje(DEFANZIVNO);
        
        double expResult = (0+2*igrac.getSnaga())*0.8;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaGV2() {  
        System.out.println("Napadni igraca GV - Energja <= 20");
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(20);
        igrac.setSnaga(10);
        igrac.setStanje(DEFANZIVNO);
        
        double expResult = (0+2*igrac.getSnaga())*0.8;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaGV3() {  
        System.out.println("Napadni igraca GV - Energja > 20");
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(21);
        igrac.setSnaga(10);
        igrac.setStanje(DEFANZIVNO);
        double expResult = (30/2+2*igrac.getSnaga())*0.8;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaGV4() {  
        System.out.println("Napadni igraca GV - energija = energija");
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(20);
        igrac.setSnaga(10);
        igrac.setStanje(DEFANZIVNO);
        double expResult = igrac.getEnergija();
        igrac.napadniIgraca(0, meta);
        double result = igrac.getEnergija();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaGV5() {  
        System.out.println("Napadni igraca GV - energija = energija - 21");
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(21);
        igrac.setSnaga(10);
        igrac.setStanje(DEFANZIVNO);
        double expResult = igrac.getEnergija()-21;
        igrac.napadniIgraca(0, meta);
        double result = igrac.getEnergija();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaGV6() {  
        System.out.println("Napadni igraca GV - snaga < oruzje.getPotrebnaSnaga()");
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(30);
        igrac.setSnaga(29);
        igrac.setStanje(DEFANZIVNO);
        double expResult = (oruzje.getPotrebnaSnaga()/2+2*igrac.getSnaga())*0.8;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaGV7() {  
        System.out.println("Napadni igraca GV - snaga >= oruzje.getPotrebnaSnaga()");
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(30);
        igrac.setSnaga(30);
        igrac.setStanje(DEFANZIVNO);
        double expResult = (oruzje.getPotrebnaSnaga()+2*igrac.getSnaga())*0.8;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaGV8() {  
        System.out.println("Napadni igraca GV - snaga >= oruzje.getPotrebnaSnaga()");
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(30);
        igrac.setSnaga(31);
        igrac.setStanje(DEFANZIVNO);
        double expResult = (oruzje.getPotrebnaSnaga()+2*igrac.getSnaga())*0.8;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }   
    
    /*
            INDEXI KOJI IZLAZE VAN GRANICA
                    NAPADNI IGRACA
    */
    
    
    @Test
    public void napadniIgracaIndex1() {  
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(10);
        igrac.setSnaga(10);
        igrac.setStanje(DEFANZIVNO);
        
        double expResult = (0+2*igrac.getSnaga())*0.8;
        double result = igrac.napadniIgraca(-1, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaIndex2() {  
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(10);
        igrac.setSnaga(10);
        igrac.setStanje(DEFANZIVNO);
        
        double expResult = (0+2*igrac.getSnaga())*0.8;
        double result = igrac.napadniIgraca(1, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    /*
        TESTIRANJE METODOM BELE KUTIJE
            ISKAZI UJEDNO I ODLUKE
                NAPADI IGRACA
    */
    
    
    @Test
    public void napadniIgracaIskaz1() {  
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(10);
        igrac.setSnaga(10);
        igrac.setStanje(DEFANZIVNO);
        
        double expResult = (0+2*igrac.getSnaga())*0.8;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void napadniIgracaIskaz2() {  
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(10);
        igrac.setSnaga(10);
        igrac.setStanje(PASIVNO);
        
        double expResult = (0/2+2*igrac.getSnaga())*1;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void napadniIgracaIskaz3() {  
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(50);
        igrac.setSnaga(50);
        igrac.setStanje(AGRESIVNO);
        
        double expResult = (30+2*igrac.getSnaga())*1.2;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }

    /*
                TESTIRANJE PUTANJA
                   NAPADI IGRACA
    */
    
    @Test
    public void napadniIgracaPutanje1() {  
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(10);
        igrac.setSnaga(10);
        igrac.setStanje(DEFANZIVNO);
        
        double expResult = (0/2+igrac.getSnaga()*2)*0.8;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaPutanje2() {  
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(10);
        igrac.setSnaga(10);
        igrac.setStanje(PASIVNO);
        
        double expResult = (0/2+igrac.getSnaga()*2)*1;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaPutanje3() {  
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(10);
        igrac.setSnaga(10);
        igrac.setStanje(AGRESIVNO);
        
        double expResult = (0/2+igrac.getSnaga()*2)*1.2;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaPutanje4() {  
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(10);
        igrac.setSnaga(50);
        igrac.setStanje(DEFANZIVNO);
        
        double expResult = (0/2+igrac.getSnaga()*2)*0.8;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaPutanje5() {  
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(10);
        igrac.setSnaga(50);
        igrac.setStanje(PASIVNO);
        
        double expResult = (0/2+igrac.getSnaga()*2)*1;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaPutanje6() {  
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(10);
        igrac.setSnaga(50);
        igrac.setStanje(AGRESIVNO);
        
        double expResult = (0/2+igrac.getSnaga()*2)*1.2;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaPutanje7() {  
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(30);
        igrac.setSnaga(10);
        igrac.setStanje(DEFANZIVNO);
        
        double expResult = (30/2+igrac.getSnaga()*2)*0.8;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaPutanje8() {  
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(30);
        igrac.setSnaga(10);
        igrac.setStanje(PASIVNO);
        
        double expResult = (30/2+igrac.getSnaga()*2)*1;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaPutanje9() {  
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(30);
        igrac.setSnaga(10);
        igrac.setStanje(AGRESIVNO);
        
        double expResult = (30/2+igrac.getSnaga()*2)*1.2;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaPutanje10() {  
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(30);
        igrac.setSnaga(50);
        igrac.setStanje(DEFANZIVNO);
        
        double expResult = (30+igrac.getSnaga()*2)*0.8;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaPutanje11() {  
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(30);
        igrac.setSnaga(50);
        igrac.setStanje(PASIVNO);
        
        double expResult = (30+igrac.getSnaga()*2)*1;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void napadniIgracaPutanje12() {  
        when(oruzje.getSteta()).thenReturn(30.0);
        when(oruzje.getPotrebnaSnaga()).thenReturn(30.0);
        
        listaOruzja.add(oruzje);
        
        igrac.setEnergija(30);
        igrac.setSnaga(50);
        igrac.setStanje(AGRESIVNO);
        
        double expResult = (30+igrac.getSnaga()*2)*1.2;
        double result = igrac.napadniIgraca(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    /*
            TESTIRANJE METODOM CRNE KUTIJE
                KLASE EKVIVALENICJE
                  UPOTREBI MAGIJU
    */
    
    @Test
    public void upotrebiMagijuKE1() {  
        System.out.println("Upotrebi magiju KE inteligencija  < potrebnaInteligencija ocekuje se energja = 0");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(10);
        igrac.setZdravlje(10);
        igrac.setEnergija(50);
        
        igrac.upotrebiMagiju(0, meta);
        
        double expResult = 0;
        double result = igrac.getEnergija();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuKE2() {    
        System.out.println("Upotrebi magiju KE inteligencija  < potrebnaInteligencija  ocekuje se zdravlje = zravlje * 0.9");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(10);
        igrac.setZdravlje(10);
        igrac.setEnergija(50);
        
        double expResult = igrac.getZdravlje()*0.9;
        igrac.upotrebiMagiju(0, meta);
        double result = igrac.getZdravlje();
        assertEquals(expResult, result, 0.0);
    }
    @Test
    public void upotrebiMagijuKE3() {  
        System.out.println("Upotrebi magiju KE inteligencija  > potrebnaInteligencija ocekuje se da energja ostane ista");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(30);
        igrac.setZdravlje(10);
        igrac.setEnergija(50);
        
        double expResult = igrac.getEnergija();
        igrac.upotrebiMagiju(0, meta);
        double result = igrac.getEnergija();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuKE4() {    
        System.out.println("Upotrebi magiju KE inteligencija  > potrebnaInteligencija ocekuje se da zdravlje ostane isto");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(30);
        igrac.setZdravlje(10);
        igrac.setEnergija(50);
        
        double expResult = igrac.getZdravlje();
        igrac.upotrebiMagiju(0, meta);
        double result = igrac.getZdravlje();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuKE5() {    
        System.out.println("Upotrebi magiju KE energija  > potrebnaEnergija ocekuje se energija = energija -  potrebna energija");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(30);
        igrac.setZdravlje(10);
        igrac.setEnergija(50);
        
        double expResult = igrac.getEnergija()-magija.getPotrebnaEnergija();
        igrac.upotrebiMagiju(0, meta);
        double result = igrac.getEnergija();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuKE6() {    
        System.out.println("Upotrebi magiju KE energija < potrebnaEnergija, zdravlje > potrebnaEnergija ocekuje se energija = 0");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(30);
        igrac.setZdravlje(10);
        igrac.setEnergija(10);
        
        double expResult = 0;
        igrac.upotrebiMagiju(0, meta);
        double result = igrac.getEnergija();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuKE7() {    
        System.out.println("Upotrebi magiju KE energija  < potrebnaEnergija, zdravlje > potrebnaEnergija ocekuje se zdravlje = zdravlje - potrebnaEnergija");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(30);
        igrac.setZdravlje(40);
        igrac.setEnergija(10);
        
        double expResult = igrac.getZdravlje()-magija.getPotrebnaEnergija();
        igrac.upotrebiMagiju(0, meta);
        double result = igrac.getZdravlje();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuKE8() {    
        System.out.println("Upotrebi magiju KE energija < potrebnaEnergija, zdravlje < potrebnaEnergija ocekuje se steta = 0");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(30);
        igrac.setZdravlje(10);
        igrac.setEnergija(10);
        
        double expResult = 0;
        double result = igrac.upotrebiMagiju(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    /*
            GRANICNE VREDNOSTI
              UPOTREBI MAGIJU
    */
    
    
        @Test
    public void upotrebiMagijuGV1() {  
        System.out.println("Upotrebi magiju GV inteligencija  < potrebnaInteligencija ocekuje se energja = 0");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(19);
        igrac.setZdravlje(10);
        igrac.setEnergija(50);
        
        igrac.upotrebiMagiju(0, meta);
        
        double expResult = 0;
        double result = igrac.getEnergija();
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void upotrebiMagijuGV2() {    
        System.out.println("Upotrebi magiju GV inteligencija  < potrebnaInteligencija ocekuje se zdravlje = zravlje * 0.9");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(19);
        igrac.setZdravlje(10);
        igrac.setEnergija(50);
        
        double expResult = igrac.getZdravlje()*0.9;
        igrac.upotrebiMagiju(0, meta);
        double result = igrac.getZdravlje();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuGV3() {  
        System.out.println("Upotrebi magiju GV inteligencija  = potrebnaInteligencija ocekuje se da energja ostane ista");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(20);
        igrac.setZdravlje(10);
        igrac.setEnergija(50);
        
        double expResult = igrac.getEnergija();
        igrac.upotrebiMagiju(0, meta);
        double result = igrac.getEnergija();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuGV4() {  
        System.out.println("Upotrebi magiju GV inteligencija  > potrebnaInteligencija ocekuje se da energja ostane ista");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(20);
        igrac.setZdravlje(10);
        igrac.setEnergija(50);
        
        double expResult = igrac.getEnergija();
        igrac.upotrebiMagiju(0, meta);
        double result = igrac.getEnergija();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuGV5() {    
        System.out.println("Upotrebi magiju GV inteligencija  = potrebnaInteligencija ocekuje se da zdravlje ostane isto");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(20);
        igrac.setZdravlje(10);
        igrac.setEnergija(50);
        
        double expResult = igrac.getZdravlje();
        igrac.upotrebiMagiju(0, meta);
        double result = igrac.getZdravlje();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuGV6() {    
        System.out.println("Upotrebi magiju GV inteligencija  > potrebnaInteligencija ocekuje se da zdravlje ostane isto");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(21);
        igrac.setZdravlje(10);
        igrac.setEnergija(50);
        
        double expResult = igrac.getZdravlje();
        igrac.upotrebiMagiju(0, meta);
        double result = igrac.getZdravlje();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuGV7() {    
        System.out.println("Upotrebi magiju GV energija  > potrebnaEnergija ocekuje se energija = energija -  potrebna energija");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(30);
        igrac.setZdravlje(10);
        igrac.setEnergija(21);
        
        double expResult = igrac.getEnergija();
        igrac.upotrebiMagiju(0, meta);
        double result = igrac.getEnergija()-magija.getPotrebnaEnergija();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuGV8() {    
        System.out.println("Upotrebi magiju GV energija  = potrebnaEnergija ocekuje se energija = energija -  potrebna energija");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(30);
        igrac.setZdravlje(10);
        igrac.setEnergija(20);
        
        double expResult = igrac.getEnergija();
        igrac.upotrebiMagiju(0, meta);
        double result = igrac.getEnergija()-magija.getPotrebnaEnergija();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuGV9() {    
        System.out.println("Upotrebi magiju GV energija < potrebnaEnergija ocekuje se energija = 0");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(30);
        igrac.setZdravlje(10);
        igrac.setEnergija(19);
        
        double expResult = 0;
        igrac.upotrebiMagiju(0, meta);
        double result = igrac.getEnergija();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuGV10() {    
        System.out.println("Upotrebi magiju GV energija < potrebnaEnergija, zdravlje = potrebnaEnergija ocekuje se steta = 0");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(30);
        igrac.setZdravlje(20);
        igrac.setEnergija(0);
        
        int in = igrac.getInteligencija();
        double expResult = magija.getSteta()*(in*0.10 + (in - magija.getPotrebnaInteligencija()) + (in - meta.getInteligencija()));
        double result = igrac.upotrebiMagiju(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuGV11() {    
        System.out.println("Upotrebi magiju GV energija < potrebnaEnergija, zdravlje > potrebnaEnergija ocekuje se steta po formuli");
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(30);
        igrac.setZdravlje(21);
        igrac.setEnergija(10);
        
        int in = igrac.getInteligencija();
        double expResult = magija.getSteta()*(in*0.10 + (in - magija.getPotrebnaInteligencija()) + (in - meta.getInteligencija()));
        double result = igrac.upotrebiMagiju(0, meta);
        assertEquals(expResult, result, 0.0);
    }

    
    /*
             INDEXI IZVAN GRANICA
                UPOTREBI MAGIJU
    */
    
    
    @Test
    public void upotrebiMagijuIndex3() {    
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(30);
        igrac.setZdravlje(20);
        igrac.setEnergija(0);
        
        int in = igrac.getInteligencija();
        double expResult = magija.getSteta()*(in*0.10 + (in - magija.getPotrebnaInteligencija()) + (in - meta.getInteligencija()));
        double result = igrac.upotrebiMagiju(-1, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuIndex4() {    
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(30);
        igrac.setZdravlje(20);
        igrac.setEnergija(0);
        
        int in = igrac.getInteligencija();
        double expResult = magija.getSteta()*(in*0.10 + (in - magija.getPotrebnaInteligencija()) + (in - meta.getInteligencija()));
        double result = igrac.upotrebiMagiju(1, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    /*
        TESTIRANJE METODOM BELE KUTIJE
               POKRIVANJE PUTANJA
                UPOTREBI MAGIJU
    */
    
        @Test
    public void upotrebiMagijuPutanje1() {    
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(30);
        igrac.setZdravlje(30);
        igrac.setEnergija(10);
        
        int in = igrac.getInteligencija();
        double expResult = magija.getSteta()*(in*0.10 + (in - magija.getPotrebnaInteligencija()) + (in - meta.getInteligencija()));
        double result = igrac.upotrebiMagiju(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuPutanje2() {    
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(60.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(30);
        igrac.setZdravlje(30);
        igrac.setEnergija(10);
        
        int in = igrac.getInteligencija();
        double expResult = 0*(in*0.10 + (in - magija.getPotrebnaInteligencija()) + (in - meta.getInteligencija()));
        double result = igrac.upotrebiMagiju(0, meta);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void upotrebiMagijuPutanje3() {    
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(-20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(10);
        igrac.setZdravlje(30);
        igrac.setEnergija(10);
        
        int in = igrac.getInteligencija();
        double expResult = magija.getSteta()*(in*0.10 + (in - magija.getPotrebnaInteligencija()) + (in - meta.getInteligencija()));
        double result = igrac.upotrebiMagiju(0, meta);
        assertEquals(expResult, result, 0.0);
    }

    /*
            POKRIVANJE ISKAZA
             UPOTREBI MAGIJU
    */
    
    @Test
    public void upotrebiMagijuIskazi1() {    
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(10);
        igrac.setZdravlje(10);
        igrac.setEnergija(10);
        
        int in = igrac.getInteligencija();
        double expResult = 0*(in*0.10 + (in - magija.getPotrebnaInteligencija()) + (in - meta.getInteligencija()));
        double result = igrac.upotrebiMagiju(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void upotrebiMagijuIskazi2() {    
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(10);
        igrac.setZdravlje(30);
        igrac.setEnergija(10);
        
        int in = igrac.getInteligencija();
        double expResult = magija.getSteta()*(in*0.10 + (in - magija.getPotrebnaInteligencija()) + (in - meta.getInteligencija()));
        double result = igrac.upotrebiMagiju(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    
    /*
                POKRIVANJE USLOVA
                 UPOTREBI MAGIJU
    */
    
    @Test
    public void upotrebiMagijuUslovi1() {    
        when(magija.getSteta()).thenReturn(20.0);
        when(magija.getPotrebnaInteligencija()).thenReturn(20.0);
        when(magija.getPotrebnaEnergija()).thenReturn(20.0);
        
        listaMagije.add(magija);

        igrac.setInteligencija(30);
        igrac.setZdravlje(30);
        igrac.setEnergija(30);
        
        int in = igrac.getInteligencija();
        double expResult = magija.getSteta()*(in*0.10 + (in - magija.getPotrebnaInteligencija()) + (in - meta.getInteligencija()));
        double result = igrac.upotrebiMagiju(0, meta);
        assertEquals(expResult, result, 0.0);
    }
    
    /*
            TESTIRANJE METODOM CRNE KUTIJE
                KLASE EKVIVALENIJE
                    ODBRANI SE
    */

   @Test (expected = IllegalArgumentException.class)
    public void odbraniSeKE1() {    
        System.out.println("Odbrani se KE negativna steata");
        when(oruzje.getTezina()).thenReturn(10.0);
        when(odeca.getTezina()).thenReturn(10.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOruzja.add(oruzje);
        listaOdece.add(odeca);

        igrac.setStanje(DEFANZIVNO);
        igrac.setSnaga(10);
        
        int steta = -10;
        double expResult = steta/(odeca.getOdbrambenaVrednost()*0.3);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSeKE2() {    
        System.out.println("Odbrani se KE tezina < snaga*3");
        when(oruzje.getTezina()).thenReturn(10.0);
        when(odeca.getTezina()).thenReturn(10.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOruzja.add(oruzje);
        listaOdece.add(odeca);

        igrac.setStanje(PASIVNO);
        igrac.setSnaga(50);
        
        int steta = 10;
        double expResult = steta/(odeca.getOdbrambenaVrednost()*0.2);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSeKE3() {    
        System.out.println("Odbrani se KE tezina > snaga*3");
        when(oruzje.getTezina()).thenReturn(10.0);
        when(odeca.getTezina()).thenReturn(10.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOruzja.add(oruzje);
        listaOdece.add(odeca);

        igrac.setStanje(PASIVNO);
        igrac.setSnaga(5);
        
        int steta = 10;
        double expResult = steta*1.5/(odeca.getOdbrambenaVrednost()*0.9*0.15);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test(expected = ArithmeticException.class)
    public void odbraniSeKE4() {    
        System.out.println("Odbrani se KE prazne liste ne moze da se deli sa 0");
        
        igrac.setStanje(PASIVNO);
        igrac.setSnaga(50);
        int steta = 10;
        double expResult = steta/(odeca.getOdbrambenaVrednost()*0.2);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    /*
            GRANICE VREDNOSTI
                ODBRANI SE
    */
    
    @Test (expected = IllegalArgumentException.class)
    public void odbraniSeGV1() {    
        System.out.println("Odbrani se GV negativna steta");
        
        igrac.setStanje(PASIVNO);
        igrac.setSnaga(50);
        int steta = -1;
        double expResult = steta/(odeca.getOdbrambenaVrednost()*0.2);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSeGV2() {    
        System.out.println("Odbrani se GV pozitivna steta");
        when(oruzje.getTezina()).thenReturn(10.0);
        when(odeca.getTezina()).thenReturn(10.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOruzja.add(oruzje);
        listaOdece.add(odeca);

        igrac.setStanje(PASIVNO);
        igrac.setSnaga(5);

        int steta = 0;
        double expResult = steta*1.5/(odeca.getOdbrambenaVrednost()*0.9*0.15);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSeGV3() {    
        System.out.println("Odbrani se GV pozitivna steta");
        when(oruzje.getTezina()).thenReturn(10.0);
        when(odeca.getTezina()).thenReturn(10.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOruzja.add(oruzje);
        listaOdece.add(odeca);

        igrac.setStanje(PASIVNO);
        igrac.setSnaga(5);

        int steta = 1;
        double expResult = steta*1.5/(odeca.getOdbrambenaVrednost()*0.9*0.15);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSeGV4() {    
        System.out.println("Odbrani se GV tezina < snaga*3");
        when(oruzje.getTezina()).thenReturn(10.0);
        when(odeca.getTezina()).thenReturn(4.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOruzja.add(oruzje);
        listaOdece.add(odeca);

        igrac.setStanje(PASIVNO);
        igrac.setSnaga(5);
        
        int steta = 10;
        double expResult = steta/(odeca.getOdbrambenaVrednost()*0.2);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSeGV5() {    
        System.out.println("Odbrani se GV tezina >= snaga*3");
        when(oruzje.getTezina()).thenReturn(10.0);
        when(odeca.getTezina()).thenReturn(5.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOruzja.add(oruzje);
        listaOdece.add(odeca);

        igrac.setStanje(PASIVNO);
        igrac.setSnaga(5);
        
        int steta = 10;
        double expResult = steta*1.5/(odeca.getOdbrambenaVrednost()*0.9*0.15);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSeGV6() {    
        System.out.println("Odbrani se GV tezina > snaga*3");
        when(oruzje.getTezina()).thenReturn(10.0);
        when(odeca.getTezina()).thenReturn(6.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOruzja.add(oruzje);
        listaOdece.add(odeca);

        igrac.setStanje(PASIVNO);
        igrac.setSnaga(5);
        
        int steta = 10;
        double expResult = steta*1.5/(odeca.getOdbrambenaVrednost()*0.9*0.15);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    
    /*
            TESTIRANJE METODOM BELE KUTIJE
                TESTIRANJE PUTANJA
                    ODBRANI SE
    */
    
    @Test (expected = ArithmeticException.class)
    public void odbraniSePutanje1() {    
        igrac.setStanje(DEFANZIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta/(0*0.3);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test (expected = ArithmeticException.class)
    public void odbraniSePutanje2() {    
        igrac.setStanje(PASIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta/(0*0.2);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test (expected = ArithmeticException.class)
    public void odbraniSePutanje3() {    
        igrac.setStanje(AGRESIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta/(0*0.1);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test (expected = ArithmeticException.class)
    public void odbraniSePutanje4() {    
        igrac.setStanje(DEFANZIVNO);
        igrac.setSnaga(-10);
        
        int steta = 10;
        double expResult = steta*1.5/(0*0.9*0.25);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test (expected = ArithmeticException.class)
    public void odbraniSePutanje5() {    
        igrac.setStanje(PASIVNO);
        igrac.setSnaga(-10);
        
        int steta = 10;
        double expResult = steta*1.5/(0*0.9*0.15);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test (expected = ArithmeticException.class)
    public void odbraniSePutanje6() {    
        igrac.setStanje(AGRESIVNO);
        igrac.setSnaga(-10);
        
        int steta = 10;
        double expResult = steta*1.5/(0*0.9*0.08);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSePutanje7() {    
        when(odeca.getTezina()).thenReturn(10.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOdece.add(odeca);

        igrac.setStanje(DEFANZIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta/(odeca.getOdbrambenaVrednost()*0.3);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSePutanje8() {    
        when(odeca.getTezina()).thenReturn(10.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOdece.add(odeca);

        igrac.setStanje(PASIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta/(odeca.getOdbrambenaVrednost()*0.2);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSePutanje9() {    
        when(odeca.getTezina()).thenReturn(10.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOdece.add(odeca);

        igrac.setStanje(AGRESIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta/(odeca.getOdbrambenaVrednost()*0.1);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSePutanje10() {    
        when(odeca.getTezina()).thenReturn(40.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOdece.add(odeca);

        igrac.setStanje(DEFANZIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta*1.5/(odeca.getOdbrambenaVrednost()*0.9*0.25);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSePutanje11() {    
        when(odeca.getTezina()).thenReturn(40.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOdece.add(odeca);

        igrac.setStanje(PASIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta*1.5/(odeca.getOdbrambenaVrednost()*0.9*0.15);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSePutanje12() {    
        when(odeca.getTezina()).thenReturn(40.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOdece.add(odeca);

        igrac.setStanje(AGRESIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta*1.5/(odeca.getOdbrambenaVrednost()*0.9*0.08);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }    
    
    @Test (expected = ArithmeticException.class)
    public void odbraniSePutanje13() {    
        when(oruzje.getTezina()).thenReturn(10.0);
        
        listaOruzja.add(oruzje);

        igrac.setStanje(DEFANZIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta/(0*0.3);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test (expected = ArithmeticException.class)
    public void odbraniSePutanje14() {    
        when(oruzje.getTezina()).thenReturn(10.0);
        
        listaOruzja.add(oruzje);

        igrac.setStanje(PASIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta/(0*0.2);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test (expected = ArithmeticException.class)
    public void odbraniSePutanje15() {    
        when(oruzje.getTezina()).thenReturn(10.0);
        
        listaOruzja.add(oruzje);

        igrac.setStanje(AGRESIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta/(0*0.1);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test (expected = ArithmeticException.class)
    public void odbraniSePutanje16() {    
        when(oruzje.getTezina()).thenReturn(40.0);
        
        listaOruzja.add(oruzje);
        igrac.setStanje(DEFANZIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta*1.5/(0*0.9*0.25);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test (expected = ArithmeticException.class)
    public void odbraniSePutanje17() {    
        when(oruzje.getTezina()).thenReturn(40.0);
        
        listaOruzja.add(oruzje);

        igrac.setStanje(PASIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta*1.5/(0*0.9*0.15);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test (expected = ArithmeticException.class)
    public void odbraniSePutanje18() {    
        when(oruzje.getTezina()).thenReturn(40.0);
        
        listaOruzja.add(oruzje);

        igrac.setStanje(AGRESIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta*1.5/(0*0.9*0.08);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    /*
            POKRIVANJE ISKAZA
                ODBRANI SE
    */
    
    @Test (expected = IllegalArgumentException.class)
    public void odbraniSeIskazi1() {    
        double expResult = 16.0;
        double result = igrac.odbraniSe(-10);
        assertEquals(expResult, result, 0.0);
    }
 
    @Test
    public void odbraniSeIskazi2() {    
        when(oruzje.getTezina()).thenReturn(10.0);
        when(odeca.getTezina()).thenReturn(10.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOruzja.add(oruzje);
        listaOdece.add(odeca);

        igrac.setStanje(DEFANZIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta/(odeca.getOdbrambenaVrednost()*0.3);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSeIskazi3() {    
        when(oruzje.getTezina()).thenReturn(10.0);
        when(odeca.getTezina()).thenReturn(10.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOruzja.add(oruzje);
        listaOdece.add(odeca);

        igrac.setStanje(PASIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta/(odeca.getOdbrambenaVrednost()*0.2);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSeIskazi4() {    
        when(oruzje.getTezina()).thenReturn(10.0);
        when(odeca.getTezina()).thenReturn(10.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOruzja.add(oruzje);
        listaOdece.add(odeca);

        igrac.setStanje(AGRESIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta/(odeca.getOdbrambenaVrednost()*0.1);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSeIskazi5() {    
        when(oruzje.getTezina()).thenReturn(20.0);
        when(odeca.getTezina()).thenReturn(20.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOruzja.add(oruzje);
        listaOdece.add(odeca);

        igrac.setStanje(DEFANZIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta*1.5/(odeca.getOdbrambenaVrednost()*0.9*0.25);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSeIskazi6() {    
        when(oruzje.getTezina()).thenReturn(20.0);
        when(odeca.getTezina()).thenReturn(20.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOruzja.add(oruzje);
        listaOdece.add(odeca);

        igrac.setStanje(PASIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta*1.5/(odeca.getOdbrambenaVrednost()*0.9*0.15);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odbraniSeIskazi7() {    
        when(oruzje.getTezina()).thenReturn(20.0);
        when(odeca.getTezina()).thenReturn(20.0);
        when(odeca.getOdbrambenaVrednost()).thenReturn(30.0);

        listaOruzja.add(oruzje);
        listaOdece.add(odeca);

        igrac.setStanje(AGRESIVNO);
        igrac.setSnaga(10);
        
        int steta = 10;
        double expResult = steta*1.5/(odeca.getOdbrambenaVrednost()*0.9*0.08);
        double result = igrac.odbraniSe(steta);
        assertEquals(expResult, result, 0.0);
    }
  
    /*
        TESTIRANJE METODOM CRNE KUTJE
            GRANICNE VREDNOSTI
    
    */
    
     @Test
    public void odmoriSeGV1() {
        System.out.println("GV manje od 75 ocekuje se 100-energija");
        igrac.setEnergija(74);
        
        double expResult = 100-igrac.getEnergija();
        double result = igrac.odmoriSe();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odmoriSeGV2() {
        System.out.println("GV tacno 75 ocekuje se 100-energija");
        igrac.setEnergija(75);
        
        double expResult = 100-igrac.getEnergija();
        double result = igrac.odmoriSe();
        assertEquals(expResult, result, 0.0);
    }
        
    @Test
    public void odmoriSeGV3() {
        System.out.println("GV vise od 75 ocekuje se 0");
        igrac.setEnergija(76);
        
        double expResult = 0;
        double result = igrac.odmoriSe();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odmoriSeGV4() {
        System.out.println("GV manje od 50 ocekuje se 50");
        igrac.setEnergija(49);
        
        double expResult = 50;
        double result = igrac.odmoriSe();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odmoriSeGV5() {
        System.out.println("GV tacno 50 ocekuje se 50");
        igrac.setEnergija(50);
        
        double expResult = 50;
        double result = igrac.odmoriSe();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odmoriSeGV6() {
        System.out.println("GV vise od 50 ali manje od 75 ocekuje se 100-energija");
        igrac.setEnergija(51);
        
        double expResult = 100-igrac.getEnergija();;
        double result = igrac.odmoriSe();
        assertEquals(expResult, result, 0.0);
    }

    /*
        TESTIRANJE METODMO BELE KUTIJE
            POKRIVANJE ISKAZA
                ODMORI SE
    */
    
    @Test
    public void odmoriSeIskazi1() {    
        igrac.setEnergija(10);
        
        double expResult = 50;
        double result = igrac.odmoriSe();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void odmoriSeIskazi2() {    
        igrac.setEnergija(-500);
        double expResult = 50;
        double result = igrac.odmoriSe();
        assertEquals(expResult, result, 0.0);
    }
    /*
        POKRIVANJE USLOVA
            ODMORI SE
    */
    
    
    @Test
    public void odmoriSeUslov1() {    
        igrac.setEnergija(80);
        
        double expResult = 0;
        double result = igrac.odmoriSe();
        assertEquals(expResult, result, 0.0);
    }
    
}