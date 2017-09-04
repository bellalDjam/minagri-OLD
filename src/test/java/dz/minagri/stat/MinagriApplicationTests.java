package dz.minagri.stat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import dz.minagri.stat.enumeration.TypeCommune;
import dz.minagri.stat.model.Commune;
import dz.minagri.stat.model.Wilaya;
import dz.minagri.stat.repositories.CarteFellahRepository;
import dz.minagri.stat.repositories.CommuneRepository;
import dz.minagri.stat.repositories.WilayaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MinagriApplicationTests {
	@Autowired
	CarteFellahRepository cartRepo;

	@Autowired
	WilayaRepository wRepo;
	
	@Autowired
	CommuneRepository cRepo;
	
	private static final String cames[] = new String[] {"Aïn Naga","Aïn Zaatout"
				,"Besbes" ,"Biskra"	,"Bordj Ben Azzouz"	,"Bouchagroune"
				,"Branis","Chetma"	,"Djemorah"	,"Doucen"	,"Ech Chaïba"
				,"El Feidh"	, "El Ghrous", "El Hadjeb", "El Haouch"
				,"El Kantara" ,"El Mizaraa"	,"El Outaya" ,"Foughala"
				,"Khenguet Sidi Nadji"	,"Lichana"	,"Lioua" ,"M'Chouneche"
				,"Mekhadma"	,"M'Lili", "Ouled Djellal", "Oumache"
				,"Ourlal" , "Ras El Miaad" , "Sidi Khaled", "Sidi Okba"
				,"Tolga","Zeribet El Oued"};
	@Test
	public void contextLoads() {
	}

	@Test
	public void testrepo() {
//		Commune com = new Commune();
//		com.setWilaya(wRepo.findOne((long) 30593));
//		com.setTypeCommune(TypeCommune.COMMUNAL);
//		com.setName("Aïn Naga");
//		cRepo.save(com);
//		CarteFellah cf =new CarteFellah();
//		LocalDate dt = LocalDate.now();
//		cf.setNumS12("555jj555");
//		cf.setZone("5555jjjjhhhh55");
//		cf.setRegistrationDate(dt);
//		cartRepo.save(cf);	
//		Wilaya wl = new Wilaya();
//		wl.setCodeWilaya((long) 07);
//		wl.setNomWilaya("Biskra");

//		 createCommune(wl);
//		 wRepo.save(wl);
//		wRepo.save(wl);
//		Wilaya wl1 = new Wilaya();
//		wl1.setCodeWilaya((long) 06);
//		wl1.setNomWilaya("Blida");
//		wRepo.save(wl1);
		 
		 List<Commune>   coms = new ArrayList<>();
			
			for(String  lname :cames ) {
				 
				Commune com = new Commune();
//				com.setWilaya(wRepo.findOne((long) 30593));
				com.setTypeCommune(TypeCommune.COMMUNAL);
				com.setName(lname);
				cRepo.save(com);
			}
	}

	/**
	 * @return
	 */
//	private List<Commune> createCommune(Wilaya wl1) {
////		 Wilaya wl1=wRepo.findOne((long) 25403);
//		 List<Commune>   coms = new ArrayList<>();
//	
//		for(String  lname :cames ) {
//			 
//			Commune com = new Commune();
//			com.setWilaya(wRepo.findOne((long) 30593));
//			com.setTypeCommune(TypeCommune.COMMUNAL);
//			com.setName(lname);
//			cRepo.save(com);
//		}
////		wRepo.save(wl1);
//		return coms;
//	}
	
	
}
