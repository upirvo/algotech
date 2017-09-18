package pl.upir.algotechtest.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;


import pl.upir.algotechtest.AlgotechTestApp;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AlgotechTestApp.class)
/*
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@TestPropertySource(locations = { "classpath:test.properties" })
*/
@SpringBootTest
public class NotificationRepositoryTest {
/*
	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	DictionaryRepository dictionaryRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	NotificationService notificationService;



	private Notification notification;
	private Dictionary dictionary;

	@Test
	@Transactional
	public void checkNotificationsCount() {
		addNotification();
		List<Notification> notifications = notificationRepository.findAll();
		assertEquals(1, notifications.size());

		Dictionary notCatDictionary = notifications.get(0).getNotCategoriesDictionary();
		assertEquals(dictionary, notCatDictionary);
	}

	@Rollback
	@Transactional
	public void addNotification() {
		notification = new Notification();
		addDictionary();
		Dictionary dictionary = dictionaryRepository.findByNameAndType("BANK", DictionaryType.KATEG_ZAWIAD);
		notification.setNotificationDate(new DateTime());
		notification.setNotificationType(NotificationType.BANK);
		notification.setNotCategoriesDictionary(dictionary);
		notificationRepository.saveAndFlush(notification);
	}

	private void addDictionary() {
		dictionary = new Dictionary();
		dictionary.setDictionaryType(DictionaryType.KATEG_ZAWIAD);
		dictionary.setName("BANK");
		dictionary.setCode("B");
		dictionary.setValue("BANK");
		dictionary.setIsValid(true);
		dictionaryRepository.saveAndFlush(dictionary);
	}*/

	@Test
	public void checkNotificationsCount() {
	}
}
