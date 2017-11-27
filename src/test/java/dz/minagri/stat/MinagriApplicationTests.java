package dz.minagri.stat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vaadin.spring.navigator.SpringViewProvider;

import dz.minagri.stat.model.Adresse;
import dz.minagri.stat.repositories.AdresseRepository;
import dz.minagri.stat.repositories.CarteFellahRepository;
import dz.minagri.stat.repositories.CommuneRepository;
import dz.minagri.stat.repositories.WilayaRepository;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MinagriApplicationTests {
	@Autowired
	CarteFellahRepository cartRepo;

	@Autowired
	WilayaRepository wRepo;
	@Autowired
	AdresseRepository adRepo;
	
	@Autowired
	CommuneRepository cRepo;
	@Autowired
	 SpringViewProvider viewProvider;
	
	private static final String cames[] = new String[] {"Biskra","Oumache","Branis","Chetma", "Ouled Djellal","Ras El Miaad" ,"Besbes" ,"Sidi Khaled",
			"Doucen","Ech Chaïba","Sidi Okba","M'Chouneche","El Haouch","Aïn Naga","Zeribet El Oued","El Feidh"	,"El Kantara" ,
			"Aïn Zaatout","El Outaya" ,"Djemorah"	,"Tolga","Lioua","Lichana", "Ourlal","M'Lili","Foughala","Bordj Ben Azzouz", "El Mizaraa","Bouchagroune"
			,"Mekhadma", "El Ghrous", "El Hadjeb","Khenguet Sidi Nadji"};	
	private static final String supwil[] = new String[] {"439700","4795","25057","6783", "12192","3268" ,"20986" ,"162200"
			,"1575","4439","556185","14227","9061","20673","3568","1190","66415" ,"2577","6504"	,"6764" ,"4026","9096","1439"
			,"4101", "2187","8866"	,"2175","18718", "5941","211980","2121", "78870", "285000","4115","1356","3339","159000"
			,"3152", "54573","9811","4541", "1605", "9373","4891","29950", "2379", "86105","4870"};		
					
	private static final String cwil[] = new String[] {"ADRAR","CHLEF","LAGHOUAT","OUM EL BOUAGHI", "BATNA","BEJAIA" ,"BISKRA" ,"BECHAR",
			"BLIDA","BOUIRA","TAMANRASSET","TEBESSA","TLEMCEN","TIARET","TIZI OUZOU","ALGER","DJELFA" ,"JIJEL",
			"SETIF","SAIDA" ,"SKIKDA"	,"SIDI BEL ABBES","ANNABA","GUELMA", "CONSTANTINE","MEDEA","MOSTAGANEM","M’SILA", "MASCARA","OUARGLA"
			,"ORAN", "EL BAYADH", "ILLIZI", "BORDJ BOU ARRERIDJ","BOUMERDES","EL TARF","TINDOUF","TISSEMSILT", "EL OUED","KHENCHELA"
			,"SOUK AHRAS", "TIPAZA", "MILA","AIN DEFLA","NAAMA", "AIN TEMOUCHENT", "GHARDAIA","RELIZANE"};
	
	private static final String comAl[] = new String[] {"Alger-Centre","Sidi M'Hamed","El Madania","Belouizdad","Bab El Oued","Bologhine","Casbah",
			"Oued Koriche","Bir Mourad Raïs","El Biar","Bouzareah","Birkhadem","El Harrach","Baraki","Oued Smar","Bachdjerrah","Hussein Dey","Kouba"
			,"Bourouba","Dar El Beïda","Bab Ezzouar","Ben Aknoun","Dely Ibrahim","El Hammamet","Raïs Hamidou","Djasr Kasentina","El Mouradia","Hydra"
			,"Mohammadia","Bordj El Kiffan","El Magharia","Beni Messous","Les Eucalyptus","Birtouta","Tessala El Merdja","Ouled Chebel","Sidi Moussa"
			,"Aïn Taya","Bordj El Bahri","El Marsa","H'Raoua","Rouïba","Reghaïa","Aïn Benian","Staoueli","Zeralda","Mahelma","Rahmania","Souidania"
			,"Cheraga","Ouled Fayet","El Achour","Draria","Douera","Baba Hassen","Khraicia","Saoula"};
//	
//	@Test
//	public void contextLoads() {
//	}
//
	@Test
	public void testrepo() {
//		
//	
//	}
		Adresse add1 = new Adresse();
		add1.setCommune(cRepo.findByNameLike("Biskra"));
		add1.setCodePostal((long) 701);
		add1.setNumero("11");
		add1.setRue("beta");
		adRepo.save(add1);
		Adresse add = new Adresse();
		add.setCommune(cRepo.findByNameLike("Biskra"));
		add.setCodePostal((long) 701);
		add.setNumero("1");
		add.setRue("alfa");
		adRepo.save(add);
//		
//		for(int i=0; i<cwil.length; i++ ) {
//			Wilaya wil = new Wilaya();
//			Long code = (long) (100*i+100);
//			wil.setCodeWilaya(code);
//			wil.setTotarea(Integer.parseInt(supwil[i]));
//			wil.setNomWilaya(cwil[i]);
//			wRepo.save(wil);
		}
//	
//		List<Departement> result = departementDAO.getAll(Departement.class);
//		 List<Commune>   coms = new ArrayList<>();
//		 Wilaya bis =wRepo.findOneBynomWilaya("BISKRA");
//			for(int i=0; i<cames.length; i++ ) {
//				Long code = (long) (700+i+1);
//				Commune com = new Commune();
//				com.setCodeCommune(code);
//				com.setWilaya(bis);
//				com.setTypeCommune(TypeCommune.COMMUNAL);
//				com.setName(cames[i]);
//				coms.add(com);
//			}
//			System.out.println(coms+ " coms");
//			System.out.println(bis+ " wilaya");
//			bis.setCommunes(coms);
//			wRepo.save(bis);
//
//	}
	@Test
	public void testFindAll() {
		List<Adresse> result = adRepo.findAll();
		System.out.println(result);
//		List<Facture> result1 = factureFDAO.findAllPayedFactures();
//		System.out.println("AAAAAAAAAAAAAAAAA"+result1.size());

		Assert.assertNotNull(result);

//		Assert.assertEquals(EtatFacture.NONPAYEE, result.get(0).getEtatFacture());

	}
}
