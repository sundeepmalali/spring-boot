package com.spring.boot;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.cache.StockTracker;
import com.spring.boot.entity.Stock;
import com.spring.boot.entity.StockRepository;

//@Controller
@RestController
@SpringBootApplication
//@ServletComponentScan
//@EnableWebSecurity
//@EnableOAuth2Sso
@EnableCaching
public class Application  extends WebSecurityConfigurerAdapter  implements CommandLineRunner {

	@Autowired
	private StockTracker tracker;
	
	@Autowired
	private CounterService counterService;
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private StockRepository repo;

	@Value("${n}")
	private String name;
	
	@Autowired
	AppMessage msg;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
//	@RequestMapping("/mustache")
	public String view(Model model) {
		model.addAttribute("company", "Pivotal");
		return "template";
	}

	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().authenticated();
		http.authorizeRequests().anyRequest().authenticated() .and().formLogin();
	}
	
	@RequestMapping("/test")
	public String home() {
		counterService.increment("service.metric.invoke.home");
		return "Hello from Udemy Spring..." + name + ". " + msg.getMessage();
	}
	
	@RequestMapping("/stocks")
	public List<Stock> stocks() {
		return em.createQuery("select s from Stock s").getResultList();
	}
	
	@RequestMapping("/stocks/{symbol}")
	public Stock stockBySymbol(@PathVariable("symbol") String symbol) {
		return repo.findBySymbol(symbol);
	}

	public void run(String... args) throws Exception {
		System.out.println("******** CLI startup for stock price...");
		for (int i = 0; i<10; i++) {
			System.out.println(tracker.getPriceBySymbol("T"));
		}
		
		System.out.println("**************** CLI starup stock price from cache manager...");
		System.out.println(tracker.getPriceFromCacheManager("T"));
	}
}