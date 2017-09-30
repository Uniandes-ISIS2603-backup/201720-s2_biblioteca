package co.edu.uniandes.quantum.biblioteca.persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import co.edu.uniandes.quantum.biblioteca.entities.SalaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author jf.garcia
 */
@RunWith(Arquillian.class)
public class SalaPersistenceTest 
{

 @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SalaEntity.class.getPackage())
                .addPackage(SalaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase SalaPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private SalaPersistence persistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    /**
     * Entities a usar
     */
    private List<SalaEntity> data = new ArrayList<SalaEntity>();

    private void clearData() {
        em.createQuery("delete from SalaEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
           SalaEntity entity = factory.manufacturePojo(SalaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

 public SalaPersistenceTest(){}

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class USalaPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        SalaEntity newEntity = factory.manufacturePojo(SalaEntity.class);
       SalaEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        SalaEntity entity = em.find(SalaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of update method, of class SalaPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        SalaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SalaEntity newEntity = factory.manufacturePojo(SalaEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        SalaEntity resp = em.find(SalaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class SalaPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        SalaEntity entity = data.get(0);
        persistence.delete(entity.getId());
        SalaEntity deleted = em.find(SalaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of find method, of class SalaPersistence.
     */
//    @Test
//    public void testFind() throws Exception {
//        SalaEntity entity = data.get(0);
//        SalaEntity newEntity = persistence.find(entity.getId());
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(entity.getName(), newEntity.getName());
//    }

    

    /**
     * Test of findAll method, of class SalaPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<SalaEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (SalaEntity ent : list) {
            boolean found = false;
            for (SalaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

    }    
}