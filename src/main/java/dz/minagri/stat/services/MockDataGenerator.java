/**
 * 
 */
package dz.minagri.stat.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.ui.renderers.LocalDateRenderer;


import dz.minagri.stat.enumeration.Availability;
import dz.minagri.stat.enumeration.Gender;
import dz.minagri.stat.enumeration.TypeCommune;
import dz.minagri.stat.enumeration.TypeDepartement;
import dz.minagri.stat.enumeration.TypeExploitation;
import dz.minagri.stat.enumeration.TypePersonne;
import dz.minagri.stat.model.Account;
import dz.minagri.stat.model.Adresse;
import dz.minagri.stat.model.CarteFellah;
import dz.minagri.stat.model.Category;
import dz.minagri.stat.model.Commune;
import dz.minagri.stat.model.Departement;
import dz.minagri.stat.model.EspeceCultivee;
import dz.minagri.stat.model.Exploitant;
import dz.minagri.stat.model.Exploitant.ExploitantStatus;
import dz.minagri.stat.model.Exploitation;
import dz.minagri.stat.model.Personne;
import dz.minagri.stat.model.Product;
import dz.minagri.stat.model.Role;
import dz.minagri.stat.model.Wilaya;
import dz.minagri.stat.model.Zone;
import dz.minagri.stat.repositories.AccountRepository;
import dz.minagri.stat.repositories.AdresseRepository;
import dz.minagri.stat.repositories.CarteFellahRepository;
import dz.minagri.stat.repositories.CategoryRepository;
import dz.minagri.stat.repositories.CommuneRepository;
import dz.minagri.stat.repositories.DepartementRepository;
import dz.minagri.stat.repositories.EspeceCultiveeRepository;
import dz.minagri.stat.repositories.ExploitantRepository;
import dz.minagri.stat.repositories.ExploitationRepository;
import dz.minagri.stat.repositories.ProductRepository;
import dz.minagri.stat.repositories.WilayaRepository;
import dz.minagri.stat.repositories.ZoneRepository;

/**
 * @author bellal djamel
 *
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MockDataGenerator {

	@Autowired
	private ProductRepository productRespoitory;
	@Autowired
	private AdresseRepository adRepo;
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private WilayaRepository wRepo;

	@Autowired
	private EspeceCultiveeRepository espculRepo;

	@Autowired
	private CommuneRepository cRepo;

	@Autowired
	private ZoneRepository zRepo;
	//	@Autowired
	//	private AdresseRepository aRepo;
	@Autowired
	private CarteFellahRepository caRepo;
	@Autowired
	private ExploitantRepository expRepo;
	@Autowired
	private ExploitationRepository exptRepo;

	@Autowired
	private DepartementRepository depRepo;
	@Autowired
	private AccountRepository acRepo;

	private static final Random random = new Random(1);
	private static final String categoryNames[] = new String[] {
			"Children's books", "Best sellers", "Romance", "Mystery",
			"Thriller", "Sci-fi", "Non-fiction", "Cookbooks" };

	private static String[] word1 = new String[] { "The art of", "Mastering",
			"The secrets of", "Avoiding", "For fun and profit: ",
			"How to fail at", "10 important facts about",
			"The ultimate guide to", "Book of", "Surviving", "Encyclopedia of",
			"Very much", "Learning the basics of", "The cheap way to",
			"Being awesome at", "The life changer:", "The Vaadin way:",
			"Becoming one with", "Beginners guide to",
			"The complete visual guide to", "The mother of all references:" };
	private static String[] exword1 = new String[] { "Zahya", "Dawya",
	"hadjla:" };
	private static String[] exword2 = new String[] { "DYARELRAHMA", "dehlyse",
			"chorfa", "OKBA", "BARAKA: ",
	"hydaya:" };
	private static String[] ldword1 = new String[] { "KOYATTE", "OUED",
			"HADBATE","BIR" };
	private static String[] ldword2 = new String[] { "eddira", "elma",
			"elwaha", "elkhire", "essaba " };

	private static String[] ndword1 = new String[] { "Trek", "Youcef",
			"Adel","Faress" };
	private static String[] ndword2 = new String[] { "Ben Sadek", "Sadek",
			"Ben Saaid","Saaid" };

	private static String[] word2 = new String[] { "gardening",
			"living a healthy life", "designing tree houses", "home security",
			"intergalaxy travel", "meditation", "ice hockey",
			"children's education", "computer programming", "Vaadin TreeTable",
			"winter bathing", "playing the cello", "dummies", "rubber bands",
			"feeling down", "debugging", "running barefoot",
			"speaking to a big audience", "creating software", "giant needles",
			"elephants", "keeping your wife happy" };



	private static final String mll[] = new String[] {
			" Zaouya Benouaar", "Mnahla", "Zaouya-Ouled-Moussa", "Sahra",
			"Essareg", "zerzara", "Elkodya", "Zommitta" };
	private static final String cames[] = new String[] {"Biskra","Oumache","Branis","Chetma", "Ouled Djellal","Ras El Miaad" ,"Besbes" ,"Sidi Khaled",
			"Doucen","Ech Chaïba","Sidi Okba","M'Chouneche","El Haouch","Aïn Naga","Zeribet El Oued","El Feidh"	,"El Kantara" ,
			"Aïn Zaatout","El Outaya" ,"Djemorah"	,"Tolga","Lioua","Lichana", "Ourlal","M'Lili","Foughala","Bordj Ben Azzouz", "El Mizaraa","Bouchagroune"
			,"Mekhadma", "El Ghrous", "El Hadjeb","Khenguet Sidi Nadji"};	

	private static final String comAl[] = new String[] {"Alger-Centre","Sidi M'Hamed","El Madania","Belouizdad","Bab El Oued","Bologhine","Casbah",
			"Oued Koriche","Bir Mourad Raïs","El Biar","Bouzareah","Birkhadem","El Harrach","Baraki","Oued Smar","Bachdjerrah","Hussein Dey","Kouba"
			,"Bourouba","Dar El Beïda","Bab Ezzouar","Ben Aknoun","Dely Ibrahim","El Hammamet","Raïs Hamidou","Djasr Kasentina","El Mouradia","Hydra"
			,"Mohammadia","Bordj El Kiffan","El Magharia","Beni Messous","Les Eucalyptus","Birtouta","Tessala El Merdja","Ouled Chebel","Sidi Moussa"
			,"Aïn Taya","Bordj El Bahri","El Marsa","H'Raoua","Rouïba","Reghaïa","Aïn Benian","Staoueli","Zeralda","Mahelma","Rahmania","Souidania"
			,"Cheraga","Ouled Fayet","El Achour","Draria","Douera","Baba Hassen","Khraicia","Saoula"};

	private static final String supwil[] = new String[] {"439700","4795","25057","6783", "12192","3268" ,"20986" ,"162200"
			,"1575","4439","556185","14227","9061","20673","3568","1190","66415" ,"2577","6504"	,"6764" ,"4026","9096","1439"
			,"4101", "2187","8866"	,"2175","18718", "5941","211980","2121", "78870", "285000","4115","1356","3339","159000"
			,"3152", "54573","9811","4541", "1605", "9373","4891","29950", "2379", "86105","4870"};		

	private static final String cwil[] = new String[] {"ADRAR","CHLEF","LAGHOUAT","OUM EL BOUAGHI", "BATNA","BEJAIA" ,"BISKRA" ,"BECHAR",
			"BLIDA","BOUIRA","TAMANRASSET","TEBESSA","TLEMCEN","TIARET","TIZI OUZOU","ALGER","DJELFA" ,"JIJEL",
			"SETIF","SAIDA" ,"SKIKDA"	,"SIDI BEL ABBES","ANNABA","GUELMA", "CONSTANTINE","MEDEA","MOSTAGANEM","M’SILA", "MASCARA","OUARGLA"
			,"ORAN", "EL BAYADH", "ILLIZI", "BORDJ BOU ARRERIDJ","BOUMERDES","EL TARF","TINDOUF","TISSEMSILT", "EL OUED","KHENCHELA"
			,"SOUK AHRAS", "TIPAZA", "MILA","AIN DEFLA","NAAMA", "AIN TEMOUCHENT", "GHARDAIA","RELIZANE"};

	private static final String espcul[] = new String[] {"BLEDURE","BLETENDRE","ORGE","AVOINE","TRITICALE","MAIS","SORGHO","FEVESFEVEROLLE"
			,"POIS","LENTILLE","POISCHICHE","HARICOT","GESSESGUERFALLA","TREFLE","PDT-MULTIPLICATION","VESCE","LUSERNE","TOMATES-INDUSTR"
			,"TABAC","ARACHIDE","MENTHE","PDT-CONSOMMATION","PATATE-DOUCE","CARROTE","TOMATE-PLCHAMP","TOMATE-SERRES","OIGNON","MELON",
			"MELON-D'EAU","CANTALOUP","PASTEQUE","ARTICHAUT","PIMENT","POIVRON","CONCOMBRE","COURGETTE","AUBERGINE","CHOUXVERT","CHOUXFLEUR"
			,"NAVET","AIL","PETITSPOIS","FENOUIL","SALAD","BETRAVE","FRAISE","CELERIE","CITROULLE","PERCILE","EPINARD"};



	@PostConstruct
	private void init() {
		List<Category> categories = createCategories();
		createProducts(categories);
		createZones();
		createVege();
		createFellah();
		createExpts();
		createAgent();
		createAccount();
	}

	/**
	 * 
	 */
	private void createAgent() {

		List<Personne> personnes = new ArrayList<Personne>();

		dz.minagri.stat.model.MoyenContact c = new dz.minagri.stat.model.MoyenContact();
		c.setFax("0234568l");
		c.setTelephone("045678");
		c.setGsm("04567");
		c.setEmail("bouchra@hotmail.fr");

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

		Departement dsa = new Departement();
		dsa.setAddress(add);
		dsa.setNom("DSA-"+"Biskra");
		dsa.setMoyenContact(c);
		dsa.setTypeDepartement(TypeDepartement.DSA);
		dsa.setPersonnes(personnes);

		for(int i=0; i<ndword1.length; i++ ) {

			Personne per = new Personne();
			per.setFirstName(ndword1[i]);
			per.setLastName(ndword2[i]);
			per.setTypePersonne(TypePersonne.values()[random
			                                          .nextInt(TypePersonne.values().length)]);
			personnes.add(per);
		}
		dsa.setPersonnes(personnes);
		depRepo.save(dsa);
	}

	public void createAccount() {
		List<Role> lR1 = new ArrayList<Role>();

		List<Role> lR = new ArrayList<Role>();

		Role r = new Role("ROLE_ADMIN", "Salah Admin");
		//		r.setOrdinal(1);
		lR.add(r);
		Account ac =new Account();
		//		ac.setReferenceId("001");
		ac.setUsername("salah");
		ac.setPassword("password");
		ac.setCreatedBy("ADMIN");
		ac.setCreatedAt(LocalDate.of(2010, 12, 22));
		ac.setFirstName("Cisalah");
		ac.setLastName("Bensalah");
		ac.setEnabled(true);
		ac.setNonExpired(true);
		ac.setNonLocked(true);
		ac.setCredentialNonExpired(true);
		ac.setRoles(lR);
		acRepo.save(ac);

		Role r1 = new Role();
		r1.setAuthority("ROLE_USER");
		r1.setDescription("Salam User");
		//		r1.setOrdinal(2);
		lR1.add(r1);

		Account ac1 =new Account();
		//		ac1.setReferenceId("002");
		ac1.setUsername("salam");
		ac1.setPassword("password");
		ac1.setCreatedBy("ADMIN");
		ac1.setCreatedAt(LocalDate.of(2010, 11, 22));
		ac1.setFirstName("Cisalam");
		ac1.setLastName("Bensalam");
		ac1.setEnabled(true);
		ac1.setNonExpired(true);
		ac1.setNonLocked(true);
		ac1.setCredentialNonExpired(true);
		ac1.setRoles(lR1);
		acRepo.save(ac1);

	}
	/**
	 * 
	 */
	private void createFellah() {

		//		 Adresse ad1 = new Adresse();
		//		 Commune co =new Commune();
		//		 ad1.setCodePostal((long) 7330);
		//		 ad1.setNumero("4");
		//		 ad1.setRue("Elkodya");
		//		  co = cRepo.findByNameLike("M'Lili");
		//		 ad1.setCommune(co);
		//		 
		//		aRepo.save(ad1);

		CarteFellah cafel = new CarteFellah();
		cafel.setNumS12("1111111111");
		cafel.setZone(zRepo.findByNameLike("Elkodya"));
		cafel.setRegistrationDate(LocalDate.now());
		caRepo.save(cafel);
		Exploitant expl = new Exploitant();
		//		expl.setAdress(ad1);
		//		expl.setCarteFellah(caRepo.findByNumS12Like("1111111111"));
		//		expl.setAdress(aRepo.findByRueAndNumeroLike("Elkodya", "4"));
		expl.setFirstname("testonemane");
		expl.setLastname("testlastname");
		expl.setNationalNumber("222222222222");
		expl.setExploitantStatus(ExploitantStatus.FELLAH);
		expl.setGender(Gender.MR);
		expl.setRegistrationDate(LocalDate.of(2010, 11, 22));
		expRepo.save(expl);
	}

	/**
	 * 
	 */
	private void createVege() {
		for(int i=0; i<espcul.length; i++ ) {
			EspeceCultivee var = new EspeceCultivee();

			if(null==espculRepo.findOneByName(espcul[i])) {
				var.setName(espcul[i]);
				espculRepo.save(var);
			}

		}

	}

	/**
	 * 
	 */
	private void createZones() {

		for(int i=0; i<cwil.length; i++ ) {
			Wilaya wil = new Wilaya();

			if(null==wRepo.findOneBynomWilaya(cwil[i])) {
				Long code = (long) (100*i+100);
				wil.setCodeWilaya(code);
				wil.setTotarea(Integer.parseInt(supwil[i]));
				wil.setNomWilaya(cwil[i]);
				wRepo.save(wil);
			}

		}


		List<Commune>   coms = new ArrayList<>();
		Wilaya bis =wRepo.findOneBynomWilaya("BISKRA");
		for(int i=0; i<cames.length; i++ ) {
			Long code = (long) (700+i+1);
			Commune com = new Commune();
			com.setCodeCommune(code);
			com.setWilaya(bis);
			com.setTypeCommune(TypeCommune.COMMUNAL);
			com.setName(cames[i]);
			if(null==cRepo.findByNameLike(cames[i])) {
				coms.add(com);
			}

		}
		if(null!=coms) {
			bis.setCommunes(coms);
			wRepo.save(bis);
		}
		List<Zone> zones  =new ArrayList<>();
	
		Commune commlil =  cRepo.findByNameLike("M'Lili");
		for(int i =0;i<mll.length;i++) {
			Zone zn  =new Zone();
			zn.setName(mll[i]);
			zn.setCommune(commlil);
			zones.add(zn);
			if(null==zRepo.findOneByName(mll[i])) {
				zones.add(zn);
			}
			
		}
		if(null!=zones) {
			commlil.getZones().addAll(zones);
			cRepo.save(commlil);
		}

		List<Commune>   comsal = new ArrayList<>();
		Wilaya alg = wRepo.findOneBynomWilaya("ALGER");

		for(int i=0; i<comAl.length; i++ ) {
			Long code = (long) (1600+i+1);
			Commune com = new Commune();
			com.setCodeCommune(code);
			com.setWilaya(alg);
			com.setTypeCommune(TypeCommune.COMMUNAL);
			com.setName(comAl[i]);
			if(null==cRepo.findByNameLike(comAl[i])) {
				comsal.add(com);
			}
			System.out.println(alg+"  alg");
			System.out.println(code+"  code");
		}
		if(null!=comsal) {
			alg.setCommunes(comsal);
			wRepo.save(alg);
		}

	}

	private List<Category> createCategories() {
		List<Category> categories = new ArrayList<>();
		for (String name : categoryNames) {
			Category c = createCategory(name);
			categories.add(c);
		}
		return categories;

	}

	private void createProducts(List<Category> categories) {
		List<Product> products = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Product p = createProduct(categories);
			products.add(p);
		}
	}

	private Category createCategory(String name) {
		Category category = new Category();
		category.setName(name);
		return categoryRepository.save(category);
	}

	private Product createProduct(List<Category> categories) {
		Product product = new Product();
		product.setProductName(generateName());

		product.setPrice(new BigDecimal((random.nextInt(250) + 50) / 10.0));
		product.setAvailability(Availability.values()[random
		                                              .nextInt(Availability.values().length)]);
		if (product.getAvailability() == Availability.AVAILABLE) {
			product.setStockCount(random.nextInt(523));
		}

		product.setCategory(getCategory(categories, 1, 2));
		return productRespoitory.save(product);
	}
	private void createExpts() {
		for (int i = 0; i < 10; i++) {
			Exploitation p = createExploitation();
		}
	}
	private Exploitation createExploitation() {
		Exploitation exp = new Exploitation();
		exp.setNom(generateExpName());
		exp.setLieuDit(generateLieudit());
		exp.setTypeExploitation(TypeExploitation.values()[random
		                                                  .nextInt(TypeExploitation.values().length)]);
		exp.setdLat(random.nextDouble()+34.8058);
		exp.setdLon(random.nextDouble()+6.70698);
		exp.setZone(zRepo.findOneByName("Sahra"));

		return exptRepo.save(exp);
	}

	private Set<Category> getCategory(List<Category> categories, int min,
			int max) {
		int nr = random.nextInt(max) + min;
		HashSet<Category> productCategories = new HashSet<>();
		for (int i = 0; i < nr; i++) {
			productCategories
			.add(categories.get(random.nextInt(categories.size())));
		}

		return productCategories;
	}

	private String generateName() {
		return word1[random.nextInt(word1.length)] + " "
				+ word2[random.nextInt(word2.length)];
	}
	private String generateExpName() {
		return exword1[random.nextInt(exword1.length)] + " "
				+ exword2[random.nextInt(exword2.length)];
	}
	private String generateLieudit() {
		return ldword1[random.nextInt(ldword1.length)] + " "
				+ ldword2[random.nextInt(ldword2.length)];
	}

}
