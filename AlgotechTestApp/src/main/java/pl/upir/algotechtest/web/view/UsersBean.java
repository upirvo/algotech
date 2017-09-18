package pl.upir.algotechtest.web.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.validator.ValidatorException;

import com.upir.algotechtest.entity.User;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import pl.upir.algotechtest.services.UserService;
import pl.upir.algotechtest.utils.SendMessage;
import pl.upir.algotechtest.web.controller.MainController;
//import pl.upir.algotechtest.web.view.lazy.NotiLazyDataModel;

@ManagedBean
public class UsersBean implements Serializable {
	private final Logger log = LoggerFactory.getLogger(MainController.class);
	private static final long serialVersionUID = 1L;
	@Autowired
	private Environment env;
	@Autowired
	private UserService userService;
	@Autowired
	private ResourceBundle bundle;

	private User user;
	private String name;
	private String surname;
	private Long pesel;
	private List<User> userList;
	private int age;
	private Date birth;


	//private NotiLazyDataModel notifications;



	@PostConstruct
	public void init(){
		getAllUsers();
	}

	public void saveUser(){
		if(user==null) {
			user = new User();
		}
			user.setName(this.name);
			user.setPesel(this.pesel);
			user.setSurname(this.surname);
		userService.saveNewUser(user);
		getAllUsers();
		clean();
	}

	private static byte[] longToBytes(String x) {
		String[] str = x.split("");
		byte[] bytes = new byte[11];
		for(int i=0;i<str.length;i++) {
			bytes[i]=Byte.parseByte(str[i]);
		}
		return bytes;
	}

	private void getAllUsers(){
		userList = userService.getAllUsers();
		userList.forEach(x->{
			byte[] peselByte = longToBytes(Long.toString(x.getPesel()));
			x.setBirth(LocalDate.of(getBirthYear(peselByte), getBirthMonth(peselByte),getBirthDay(peselByte)));
			x.setAge(calculateAge(getBirthYear(peselByte), getBirthMonth(peselByte),getBirthDay(peselByte)));
		});
	}


	public void updateUser(final SelectEvent event){
		user = (User) event.getObject();
		System.out.println(event.getObject());
		setName(user.getName());
		setSurname(user.getSurname());
		setPesel(user.getPesel());
	}
	/**
	 * clean view
	 */
	public void clean()
	{
		user=null;
		setName(null);
		setSurname(null);
		setPesel(null);
		
	}

	private int getBirthYear(byte[] PESEL) {
		int year;
		int month;
		year = 10 * PESEL[0];
		year += PESEL[1];
		month = 10 * PESEL[2];
		month += PESEL[3];
		if (month > 80 && month < 93) {
			year += 1800;
		}
		else if (month > 0 && month < 13) {
			year += 1900;
		}
		else if (month > 20 && month < 33) {
			year += 2000;
		}
		else if (month > 40 && month < 53) {
			year += 2100;
		}
		else if (month > 60 && month < 73) {
			year += 2200;
		}
		return year;
	}

	private int getBirthMonth(byte[] PESEL) {
		int month;
		month = 10 * PESEL[2];
		month += PESEL[3];
		if (month > 80 && month < 93) {
			month -= 80;
		}
		else if (month > 20 && month < 33) {
			month -= 20;
		}
		else if (month > 40 && month < 53) {
			month -= 40;
		}
		else if (month > 60 && month < 73) {
			month -= 60;
		}
		return month;
	}


	private int getBirthDay(byte[] PESEL) {
		int day;
		day = 10 * PESEL[4];
		day += PESEL[5];
		return day;
	}

	private int calculateAge(int year, int month, int day) {
		Date now = new Date();
		int nowMonth = now.getMonth()+1;
		int nowYear = now.getYear()+1900;
		int result = nowYear - year;

		if (month > nowMonth) {
			result--;
		}
		else if (month == nowMonth) {
			int nowDay = now.getDate();

			if (day > nowDay) {
				result--;
			}
		}
		return result;
	}


	/*----------------------------GET-SET-------------------------------------*/


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Long getPesel() {
		return pesel;
	}

	public void setPesel(Long pesel) {
		this.pesel = pesel;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
}
