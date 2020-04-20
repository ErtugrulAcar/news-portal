package com.newsportal.newsportal;

import com.newsportal.newsportal.model.*;
import com.newsportal.newsportal.repository.*;
import com.newsportal.newsportal.source.Grp;
import com.newsportal.newsportal.source.PostGrp;
import org.springframework.beans.factory.annotation.Autowired;
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


    }


    public void initGroup(){
        groupRepository.saveAll(
                Arrays.asList(
                        new Group().setName("Müdür"),
                        new Group().setName("Editör"),
                        new Group().setName("Kullanıcı")
                )
        );
    }
    public void initPostGroup(){
        postGroupRepository.saveAll(
                Arrays.asList(
                        new PostGroup().setName("Ekonomi").setDescription("Ekonomi Haberleri"),
                        new PostGroup().setName("Spor").setDescription("Spor Haberleri"),
                        new PostGroup().setName("Hava Durumu").setDescription("Hava Durumu Haberleri"),
                        new PostGroup().setName("Siyaset").setDescription("Siyaset Haberleri")
                )
        );
    }
    public void initUser(){
        userRepository.saveAll(
                Arrays.asList(
                        new User().setName("Kemal").setLastname("Işık").setAddress("Zümrüt Sokak").setPhoneNumber("5316452452").setGroup(new Group().setId(Grp.ADMIN)).setBeInUse(true),
                        new User().setName("Adil").setLastname("Sökmen").setAddress("Kemal Paşa Mahallesi").setPhoneNumber("5453556482").setGroup(new Group().setId(Grp.EDITOR)).setBeInUse(true)
                        .setPostGroup(Arrays.asList(new PostGroup().setId(PostGrp.ECONOMY), new PostGroup().setId(PostGrp.SPORT))),
                        new User().setName("Mustafa").setLastname("Murat").setAddress("Atakent Mahallesi").setPhoneNumber("5143125544").setGroup(new Group().setId(Grp.USER)).setBeInUse(true)
                )

        );
    }
    public void SecurityQuestion(){

    }
}
