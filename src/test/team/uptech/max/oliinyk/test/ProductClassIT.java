package team.uptech.max.oliinyk.test;

import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import team.uptech.max.oliinyk.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductClassIT {
	TestRestTemplate restTemplate = new TestRestTemplate();

	@SuppressWarnings("unchecked")
	@Test
	public void getAllProd() throws Exception {
		ResponseEntity<List<Product>> responceEntity = restTemplate.exchange("http://localhost:8080/findallproducts",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
				});
		List<Product> actualList = responceEntity.getBody();
		// validate
		assertThat(actualList.size(), CoreMatchers.is(2));
		List<Long> actualId = actualList.stream().map(product -> product.getId()).collect(Collectors.toList());
		assertThat(actualId, Matchers.containsInAnyOrder(23, 26));
	}

}
