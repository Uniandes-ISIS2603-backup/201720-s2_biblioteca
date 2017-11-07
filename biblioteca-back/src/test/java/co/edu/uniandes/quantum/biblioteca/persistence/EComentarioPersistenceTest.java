/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.persistence;


import co.edu.uniandes.quantum.biblioteca.entities.EComentarioEntity;
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
 * @author de.agudelo
 */
@RunWith(Arquillian.class)
public class EComentarioPersistenceTest
{

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EComentarioEntity.class.getPackage())
                .addPackage(EComentarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase EComentarioPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private EComentarioPersistence persistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para marcar las transacciones del anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    /**
     * Entities a usar
     */
    private List<EComentarioEntity> data = new ArrayList<EComentarioEntity>();

    private void clearData() {
        em.createQuery("delete from EComentarioEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EComentarioEntity entity = factory.manufacturePojo(EComentarioEntity.class);
            System.out.println(entity.getName() + " creado.");
            em.persist(entity);
            data.add(entity);
        }
    }

    public EComentarioPersistenceTest(){}

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
     * Test of create method, of class UEComentarioPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        EComentarioEntity newEntity = factory.manufacturePojo(EComentarioEntity.class);
        EComentarioEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        EComentarioEntity entity = em.find(EComentarioEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of update method, of class EComentarioPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        EComentarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EComentarioEntity newEntity = factory.manufacturePojo(EComentarioEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        EComentarioEntity resp = em.find(EComentarioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class EComentarioPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        EComentarioEntity entity = data.get(0);
        persistence.delete(entity.getId());
        EComentarioEntity deleted = em.find(EComentarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of find method, of class LibroPersistence.
     */
    @Test
    public void testFind() throws Exception {
        EComentarioEntity entity = data.get(0);
        EComentarioEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }



    /**
     * Test of findAll method, of class EComentarioPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<EComentarioEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EComentarioEntity ent : list) {
            boolean found = false;
            for (EComentarioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

    }
}
