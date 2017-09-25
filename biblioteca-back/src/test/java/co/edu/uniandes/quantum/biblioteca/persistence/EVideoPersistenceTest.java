package co.edu.uniandes.quantum.biblioteca.persistence;

import co.edu.uniandes.quantum.biblioteca.entities.EVideoEntity;
import org.jboss.arquillian.container.test.api.Deployment;
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
public class EVideoPersistenceTest {


    /**
     * Inyección de la dependencia a la clase EVideoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private EVideoPersistence persistence;

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
    private List<EVideoEntity> data = new ArrayList<>();

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

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
        em.createQuery("delete from EVideoEntity").executeUpdate();
    }


    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EVideoEntity entity = factory.manufacturePojo(EVideoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createEBookEntityTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EVideoEntity newEntity = factory.manufacturePojo(EVideoEntity.class);
        EVideoEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        EVideoEntity entity = em.find(EVideoEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }


    @Test
    public void updateEVideoTest() {
        EVideoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EVideoEntity newEntity = factory.manufacturePojo(EVideoEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        EVideoEntity resp = em.find(EVideoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

    @Test
    public void deleteEVideoTest() {
        EVideoEntity entity = data.get(0);
        persistence.delete(entity.getId());
        EVideoEntity deleted = em.find(EVideoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void getEVideoTest() {
        EVideoEntity entity = data.get(0);
        EVideoEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    @Test
    public void getEVideosTest() {
        List<EVideoEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EVideoEntity ent : list) {
            boolean found = false;
            for (EVideoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

//    @Test
//    public void getEVideoByNameTest() {
//        EVideoEntity entity = data.get(0);
//        EVideoEntity newEntity = persistence.findByName(entity.getName());
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(entity.getName(), newEntity.getName());
//    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EVideoEntity.class.getPackage())
                .addPackage(EVideoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

}
