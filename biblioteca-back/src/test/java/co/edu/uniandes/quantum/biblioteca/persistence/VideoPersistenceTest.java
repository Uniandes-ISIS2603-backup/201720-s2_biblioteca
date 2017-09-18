package co.edu.uniandes.quantum.biblioteca.persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import co.edu.uniandes.quantum.biblioteca.entities.VideoEntity;
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
public class VideoPersistenceTest 
{

 @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VideoEntity.class.getPackage())
                .addPackage(VideoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase VideoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private VideoPersistence persistence;

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
    private List<VideoEntity> data = new ArrayList<VideoEntity>();

    private void clearData() {
        em.createQuery("delete from VideoEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
           VideoEntity entity = factory.manufacturePojo(VideoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

 public VideoPersistenceTest(){}

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
     * Test of create method, of class UVideoPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        VideoEntity newEntity = factory.manufacturePojo(VideoEntity.class);
       VideoEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        VideoEntity entity = em.find(VideoEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of update method, of class VideoPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        VideoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        VideoEntity newEntity = factory.manufacturePojo(VideoEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        VideoEntity resp = em.find(VideoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class VideoPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        VideoEntity entity = data.get(0);
        persistence.delete(entity.getId());
        VideoEntity deleted = em.find(VideoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of find method, of class LibroPersistence.
     */
    @Test
    public void testFind() throws Exception {
        VideoEntity entity = data.get(0);
        VideoEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    @Test
    public void testFindByName() {
        VideoEntity entity = data.get(0);
        VideoEntity newEntity = persistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Test of findAll method, of class VideoPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<VideoEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (VideoEntity ent : list) {
            boolean found = false;
            for (VideoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

    }    
}