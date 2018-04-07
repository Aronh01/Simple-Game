/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Shooter.Asteroida;
import Shooter.Gra;
import Shooter.Gracz;
import Shooter.Pocisk;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;


public class Test {
   
    @org.junit.Test
    public void Test1() {          
        List<Pocisk> lista_p;
        Gracz test = new Gracz();
        test.shoot();
        test.shoot();
        lista_p = new ArrayList<Pocisk>();
        lista_p = test.getPociski();
        int ilosc = lista_p.size();
        assertEquals(2,ilosc);
    }
    
    @org.junit.Test
    public void Test2() {         
        Gra test = new Gra();
        assertTrue(test.checkinitialize());
    }
    
    @org.junit.Test
    public void Test3() {    
        Gra test = new Gra();
        test.checkColision();
        assertTrue(test.player.isVisible());
    }

    
    @org.junit.Test
    public void Test4() {   
        Asteroida test = new Asteroida(2000,420);
        test.addSpeed();
        assertEquals(1.0125,Asteroida.SPEED,0);
    }
}
