package com.fab.hotel.fwallet;

import com.fab.hotel.fwallet.model.AddMoney;
import com.fab.hotel.fwallet.model.PayMoney;
import com.fab.hotel.fwallet.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FwalletApplicationTests extends FwalletAbstractTest{

	@Override
	@Before
	public void setUp() {
		super.setUp();
		String uri = "/api/user";
		User user = new User();
		user.setName("Nitin");
		user.setPhoneNum("9971782240");
		user.setPassword("nitin");
		user.setGender("M");
		user.setEmailID("nitinranjan269@gmail.com");
		user.setDob(new Date());
		try {
			String inputJson = super.mapToJson(user);
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(inputJson)).andReturn();
		}  catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public void createUser() throws Exception {
		String uri = "/api/user";
		User user = new User();
		user.setName("Nitin2");
		user.setPhoneNum("1234567890");
		user.setPassword("nitin");
		user.setGender("M");
		user.setEmailID("xxxxxxxxxxxxxxxx@gmail.com");
		user.setDob(new Date());
		String inputJson = super.mapToJson(user);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
	}
	@Test
	public void getUserList() throws Exception {
		String uri = "/api/users";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		User[] userList = super.mapFromJson(content, User[].class);
		Assert.assertTrue(userList.length > 0);
	}
	@Test
	public void getUserByPhoneNumber() throws Exception {
		String uri = "/api/user/9971782240";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
	}

	@Test
	public void updateUser() throws Exception {
		String uri = "/api/user/9971782240";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		User user = super.mapFromJson(content, User.class);
		user.setName("Nitin Ranjan");

		String inputJson = super.mapToJson(user);
		mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);	}

	@Test
	public void testAddMoney() throws Exception {
		String uri = "/api/addmoney/9971782240";
		AddMoney addMoney = new AddMoney();
		addMoney.setAmount(10000);
		addMoney.setMessage("Test Add");
		String inputJson = super.mapToJson(addMoney);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
	}

	@Test
	public void testPayMoney() throws Exception {
		String uri = "/api/pay/9971782240";
		PayMoney payMoney = new PayMoney();
		payMoney.setAmount(100);
		payMoney.setMessage("Test pay");
		payMoney.setPhoneNum("1234567890");
		String inputJson = super.mapToJson(payMoney);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
	}

	@Test
	public void getTransaction() throws Exception {
		String uri = "/api/transaction/9971782240";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
	}
}
