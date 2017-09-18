/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.quantum.biblioteca.persistence;
import co.edu.uniandes.quantum.biblioteca.entities.MultaEntity;
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
 * @author jp.sanmiguel
 */
    
@RunWith(Arquillian.class)
public class MultaPersistenceTest {
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Multa, el descriptor de la base
     * de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MultaEntity.class.getPackage())
                .addPackage(MultaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase MultaPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private MultaPersistence persistence;

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
    private List<MultaEntity> data = new ArrayList<MultaEntity>();

    private void clearData() {
        em.createQuery("delete from MultaEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            MultaEntity entity = factory.manufacturePojo(MultaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    public MultaPersistenceTest() {
    }

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
     * Test of create method, of class MultaPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        MultaEntity newEntity = factory.manufacturePojo(MultaEntity.class);
        MultaEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        MultaEntity entity = em.find(MultaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Test of update method, of class MultaPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        MultaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MultaEntity newEntity = factory.manufacturePojo(MultaEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        MultaEntity resp = em.find(MultaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    /**
     * Test of delete method, of class MultaPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        MultaEntity entity = data.get(0);
        persistence.delete(entity.getId());
        MultaEntity deleted = em.find(MultaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

   
}

