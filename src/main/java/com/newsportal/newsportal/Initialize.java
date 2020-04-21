package com.newsportal.newsportal;

import com.newsportal.newsportal.model.*;
import com.newsportal.newsportal.repository.*;
import com.newsportal.newsportal.source.Grp;
import com.newsportal.newsportal.source.PostGrp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Initialize implements CommandLineRunner {
    private UserRepository userRepository;
    private UserAccountRepository userAccountRepository;
    private SecurityQuestionRepository securityQuestionRepository;
    private GroupRepository groupRepository;
    private PostGroupRepository postGroupRepository;

    public Initialize(UserRepository userRepository, UserAccountRepository userAccountRepository, SecurityQuestionRepository securityQuestionRepository, GroupRepository groupRepository, PostGroupRepository postGroupRepository) {
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;
        this.securityQuestionRepository = securityQuestionRepository;
        this.groupRepository = groupRepository;
        this.postGroupRepository = postGroupRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(groupRepository.count() == 0)
            initGroup();
        if(postGroupRepository.count() == 0)
            initPostGroup();
        if(securityQuestionRepository.count() == 0)
            initSecurityQuestion();
        if(userRepository.count() == 0)
            initUser();

    }


    public void initGroup(){
        groupRepository.saveAll(
                Arrays.asList(
                        new Group().setId(1).setName("Müdür"),
                        new Group().setId(2).setName("Editör"),
                        new Group().setId(3).setName("Kullanıcı")
                )
        );
    }
    public void initPostGroup(){
        postGroupRepository.saveAll(
                Arrays.asList(
                        new PostGroup().setId(1).setName("Ekonomi").setDescription("Ekonomi Haberleri"),
                        new PostGroup().setId(2).setName("Spor").setDescription("Spor Haberleri"),
                        new PostGroup().setId(3).setName("Hava Durumu").setDescription("Hava Durumu Haberleri"),
                        new PostGroup().setId(4).setName("Siyaset").setDescription("Siyaset Haberleri")
                )
        );
    }
    public void initSecurityQuestion(){
        securityQuestionRepository.saveAll(
                Arrays.asList(
                        new SecurityQuestion().setId(1).setQuestion("İlkokul öğretmeninizin ismi nedir?"),
                        new SecurityQuestion().setId(2).setQuestion("Annenizin kızlık soyadı nedir?"),
                        new SecurityQuestion().setId(3).setQuestion("İlk evcil hayvanınızın ismi nedir?"),
                        new SecurityQuestion().setId(4).setQuestion("Doğduğunuz şehir neresidir?")
                )
        );
    }

    public void initUser(){
        userRepository.saveAll(
                Arrays.asList(
                        new User().setId(1).setName("Kemal").setLastname("Işık").setAddress("Zümrüt Sokak").setPhoneNumber("5316452452").setGroup(new Group().setId(Grp.ADMIN)).setBeInUse(true),
                        new User().setId(2).setName("Adil").setLastname("Sökmen").setAddress("Kemal Paşa Mahallesi").setPhoneNumber("5453556482").setGroup(new Group().setId(Grp.EDITOR)).setBeInUse(true)
                        .setPostGroup(Arrays.asList(new PostGroup().setId(PostGrp.ECONOMY), new PostGroup().setId(PostGrp.SPORT))),
                        new User().setId(3).setName("Mustafa").setLastname("Murat").setAddress("Atakent Mahallesi").setPhoneNumber("5143125544").setGroup(new Group().setId(Grp.USER)).setBeInUse(true)
                )
        );
        userAccountRepository.saveAll(
                Arrays.asList(
                        new UserAccount()
                                .setUsername("kemal").setPassword("12345")
                                .setSecurityQuestion(new SecurityQuestion().setId(1))
                                .setSecurityQuestionAnswer("kemal")
                                .setUser(new User().setId(1)),
                        new UserAccount()
                                .setUsername("adil").setPassword("12345")
                                .setSecurityQuestion(new SecurityQuestion().setId(2))
                                .setSecurityQuestionAnswer("yılmaz")
                                .setUser(new User().setId(2)),
                        new UserAccount()
                                .setUsername("mustafa").setPassword("12345")
                                .setSecurityQuestion(new SecurityQuestion().setId(4))
                                .setSecurityQuestionAnswer("İstanbul")
                                .setUser(new User().setId(3))

                )
        );
    }

}
