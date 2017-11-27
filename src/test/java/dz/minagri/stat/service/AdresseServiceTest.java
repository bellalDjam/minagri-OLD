/**
 * 
 */
package dz.minagri.stat.service;


import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dz.minagri.stat.AbstractTest;
import dz.minagri.stat.model.Adresse;
import dz.minagri.stat.repositories.CommuneRepository;
import dz.minagri.stat.services.AdresseService;
import junit.framework.Assert;

/**
 * @author bellal djamel
 *
 */
@Transactional
public class AdresseServiceTest extends AbstractTest{
	
	@Autowired
	AdresseService service;
	@Autowired
	CommuneRepository serviceC;
	@Before
	public void SetUp() {
		service.evictCache();
	}
	
	@After
	public void tearDown() {
		//clean up after each test
	}
	@Test
	public void testFindAll() {
		List<Adresse> list =service.findAll();
		Assert.assertNotNull(list);
		
	}
	@Test
    public void testFindOne() {

        Long id = new Long(222);

        Adresse entity = service.findOne(id);

        Assert.assertNotNull("failure - expected not null", entity);
        Assert.assertEquals("failure - expected id attribute match", id,
                entity.getId());

    }
	@Test
    public void testFindOneNotFound() {

        Long id = Long.MAX_VALUE;

        Adresse entity = service.findOne(id);

        Assert.assertNull("failure - expected null", entity);

    }
	@Test
    public void testCreate() {

		
        Adresse add1 = new Adresse();
		add1.setCommune(serviceC.findByNameLike("Biskra"));
		add1.setCodePostal((long) 701);
		add1.setNumero("151");
		add1.setRue("betak");
//		service.create(add1);
		Adresse createdEntity1 = service.create(add1);
//		Adresse add = new Adresse();
//		add.setCommune(serviceC.findByNameLike("Biskra"));
//		add.setCodePostal((long) 701);
//		add.setNumero("15");
//		add.setRue("al5");
//
//        Adresse createdEntity = service.create(add);

        Assert.assertNotNull("failure - expected not null", createdEntity1);
        Assert.assertNotNull("failure - expected id attribute not null",
                createdEntity1.getId());
        Assert.assertEquals("failure - expected Rue attribute match", "betak",
                createdEntity1.getRue());

        List<Adresse> list = service.findAll();

        Assert.assertEquals("failure - expected size", 3, list.size());

    }
	 @Test
	    public void testCreateWithId() {

	        Exception exception = null;

	        Adresse add = new Adresse();
	        add.setId(Long.MAX_VALUE);
	        add.setCommune(serviceC.findByNameLike("Biskra"));
			add.setCodePostal((long) 701);
			add.setNumero("15");
			add.setRue("al5");

	        try {
	            service.create(add);
	        } catch (EntityExistsException e) {
	            exception = e;
	        }

	        Assert.assertNotNull("failure - expected exception", exception);
	        Assert.assertTrue("failure - expected EntityExistsException",
	                exception instanceof EntityExistsException);

	    }
	  @Test
	    public void testUpdate() {

	        Long id = new Long(222);

	        Adresse entity = service.findOne(id);

	        Assert.assertNotNull("failure - expected not null", entity);

	        String updatedText = entity.getRue() + " test";
	        entity.setRue(updatedText);
	        Adresse updatedEntity = service.update(entity);

	        Assert.assertNotNull("failure - expected not null", updatedEntity);
	        Assert.assertEquals("failure - expected id attribute match", id,
	                updatedEntity.getId());
	        Assert.assertEquals("failure - expected text attribute match",
	                updatedText, updatedEntity.getRue());

	    }
	  @Test
	    public void testUpdateNotFound() {

	        Exception exception = null;

	        Adresse entity = new Adresse();
	        entity.setId(Long.MAX_VALUE);
	        entity.setRue("test1");

	        try {
	            service.update(entity);
	        } catch (NoResultException e) {
	            exception = e;
	        }

	        Assert.assertNotNull("failure - expected exception", exception);
	        Assert.assertTrue("failure - expected NoResultException",
	                exception instanceof NoResultException);

	    }
	  @Test
	    public void testDelete() {

	        Long id = new Long(222);

	        Adresse entity = service.findOne(id);

	        Assert.assertNotNull("failure - expected not null", entity);

	        service.delete(id);

	        List<Adresse> list = service.findAll();

	        Assert.assertEquals("failure - expected size", 1, list.size());

	        Adresse deletedEntity = service.findOne(id);

	        Assert.assertNull("failure - expected null", deletedEntity);

	    }

}
