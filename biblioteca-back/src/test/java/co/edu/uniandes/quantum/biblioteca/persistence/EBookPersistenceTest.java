package co.edu.uniandes.quantum.biblioteca.persistence;

import org.jboss.arquillian.container.test.api.Deployment;
import co.edu.uniandes.quantum.biblioteca.entities.EBookEntity;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class EBookPersistenceTest {

    /**
     * Inyección de la dependencia a la clase EBookPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private EBookPersistence persistence;

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
     *
     */
    private List<EBookEntity> data = new ArrayList< >();





    @Before
    public void setUp() throws Exception {
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

    private void clearData() {
        em.createQuery("delete from EBookEntity").executeUpdate();
    }


    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EBookEntity entity = factory.manufacturePojo(EBookEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }


    @AfterClass
    public static void tearDownClass() throws Exception {
    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createEBookEntityTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EBookEntity newEntity = factory.manufacturePojo(EBookEntity.class);
        EBookEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        EBookEntity entity = em.find(EBookEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    @Test
    public void updateEBookTest() {
        EBookEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EBookEntity newEntity = factory.manufacturePojo(EBookEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        EBookEntity resp = em.find(EBookEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    @Test
    public void deleteEBookTest() {
        EBookEntity entity = data.get(0);
        persistence.delete(entity.getId());
        EBookEntity deleted = em.find(EBookEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void getEBookTest() {
        EBookEntity entity = data.get(0);
        EBookEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    @Test
    public void getEBooksTest() {
        List<EBookEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EBookEntity ent : list) {
            boolean found = false;
            for (EBookEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

//    @Test
//    public void getEBookByNameTest() {
//        EBookEntity entity = data.get(0);
//        EBookEntity newEntity = persistence.findByName(entity.getName());
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(entity.getName(), newEntity.getName());
//    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EBookEntity.class.getPackage())
                .addPackage(EBookPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

}
