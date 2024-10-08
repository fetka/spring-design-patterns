package com.frankmoley.lil.designpatternsapp;

import com.frankmoley.lil.designpatternsapp.prototype.ProtoFalse;
import com.frankmoley.lil.designpatternsapp.prototype.ProtoTrue;
import com.frankmoley.lil.designpatternsapp.repository.PresidentRepository;
import com.frankmoley.lil.designpatternsapp.singleton.SingA;
import com.frankmoley.lil.designpatternsapp.singleton.SingB;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = DesignPatternsAppApplication.class)
public class DesignPatternsAppApplicationTest {
    @Autowired
    SingB singB1;
    @Autowired
    SingB singB2;

    @Autowired
    ProtoTrue true1;
    @Autowired
    ProtoTrue true2;

    @Autowired
    ProtoFalse false1;
    @Autowired
    ProtoFalse false2;

    @Autowired
    PresidentRepository presidentRepository;

    @Test
    public void testRepository(){
        System.out.println(presidentRepository.findById(1L));
        System.out.println(presidentRepository.findById(2L));
    }

    @Test
    public void testPrototypes(){
        Assert.assertSame(false1, false2);
        Assert.assertNotSame(true1, true2);
    }

    @Test
    public void testSingletons(){
        SingA singA1 = SingA.getInstance();
        SingA singA2 = SingA.getInstance();
        Assert.assertNotNull(singA1);
        Assert.assertSame(singA1, singA2);
        Assert.assertNotNull(singB1);
        Assert.assertSame(singB1, singB2);
    }

}

