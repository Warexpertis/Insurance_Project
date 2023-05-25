package com.wrxprts.ims;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.wrxprts.ims.entity")
public class InsuranceProjectApplication implements CommandLineRunner
{
	
	public static void main(String[] args)
	{
		SpringApplication.run(InsuranceProjectApplication.class, args);
	}
	
	/*
	 * SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	 * 
	 * Date f(String dt) throws ParseException { Date date = formatter.parse(dt);
	 * return date; }
	 *
	 * @Autowired private UserRepository userRepository;
	 */
	
	@Override
	public void run(String... args) throws Exception
	{
		/*
		 * User user1 = new User("Alp Talha", "Demir", f("03/02/2003"), "İstanbul",
		 * "40516523860", "34567"); userRepository.save(user1);
		 * 
		 * User user2 = new User("Walter", "White", f("09/09/1981"), "Ohio",
		 * "12867425523", "er!yo3E"); userRepository.save(user2);
		 * 
		 * User user3 = new User("Rock", "Johnson", f("16/07/1970"), "Ohio",
		 * "03213175683", "theRock123?"); userRepository.save(user3);
		 */
	}
	
}
