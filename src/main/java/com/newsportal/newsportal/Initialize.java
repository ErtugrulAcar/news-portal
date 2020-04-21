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
    private PostRepository postRepository;
    private PermissionRepository permissionRepository;

    public Initialize(UserRepository userRepository, UserAccountRepository userAccountRepository, SecurityQuestionRepository securityQuestionRepository, GroupRepository groupRepository, PostGroupRepository postGroupRepository, PostRepository postRepository, PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;
        this.securityQuestionRepository = securityQuestionRepository;
        this.groupRepository = groupRepository;
        this.postGroupRepository = postGroupRepository;
        this.postRepository = postRepository;
        this.permissionRepository = permissionRepository;
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
        if(postRepository.count() == 0)
            initPost();
        if(permissionRepository.count() == 0)
            initPermissions();

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

    public void initPost(){
        postRepository.saveAll(

                Arrays.asList(
                        new Post().setAuthor(new User().setId(2)).setPostGroup(new PostGroup().setId(PostGrp.ECONOMY))
                        .setTitle("Hanelere 1000'er lira desteğin başvuru detayları belli oldu")
                        .setImageUrl("https://im.haberturk.com/2020/04/21/ver1587471607/2653393_810x458.jpg")
                        .setContent("Aile, Çalışma ve Sosyal Hizmetler Bakanlığı, Ekonomik İstikrar Kalkanı Paketi kapsamında hane başına 1000'er lira desteği içeren 3. faz sosyal destek programı için başvuruları e-Devlet üzerinden almaya başladı. Desteğe, Pandemi Sosyal Destek Programı Faz 1 ve Faz 2 kapsamında verilen 1000 liralık nakdi destekten yararlanmamış olanlar başvurabilecek. Kamu işçileri, memurlar, emekliler ile İŞKUR'un Kısa Çalışma Ödeneği ile İşsizlik Ödeneği'nden faydalananların başvuruları değerlendirmeye alınmayacak. e-Devlet üzerinden T.C. kimlik numarasının son hanesi \"0\" olanlar pazartesi, \"2\" olanlar salı, \"4\" olanlar çarşamba, \"6\" olanlar perşembe ve \"8\" olanlar cuma günü destek başvurusunda bulunabilecek. Destek için cumartesi ve pazar günleri T.C. kimlik numarası ile ilgili sınırlama olmaksızın başvurulabilecek"),

                        new Post().setAuthor(new User().setId(2)).setPostGroup(new PostGroup().setId(PostGrp.ECONOMY))
                        .setTitle("Petrol fiyatları 20 doların altına indi")
                        .setImageUrl("https://im.haberturk.com/2020/04/21/ver1587464320/2653356_810x458.jpg")
                        .setContent("Brent petrolün varili dünya genelinde yeni tip koronavirüs (Kovid-19) nedeniyle devam eden düşük talep ve arz fazlasındaki artışla uluslararası piyasalarda 20 doların altına geriledi.\n" +
                                "\n" +
                                "Dünü 25,48 dolardan tamamlayan Brent petrolün varili, saat 12.35 itibarıyla 18,02 dolara kadar inerken günlük kayıp yüzde 29,27 oldu. Brent petroldeki bu geri çekilme Şubat 2002'den bu yana en düşük seviye olarak kayıtlara geçti. Aynı dakikalarda Batı Teksas türü (WTI) ham petrolün varili 6,95 dolardan alıcı bulurken günlük düşüş yüzde 41,88 oldu.\n" +
                                "\n" +
                                "ABD'de WTI tipi ham petrolün mayıs ayı için vadeli işlem sözleşmelerinin süresi bugün dolarken, kontrat sahiplerinin fiziki petrol teslimatından kaçınmasıyla WTI tipi petrolün varil fiyatı dün yüzde 300'ün üzerinde değer kaybederek eksi 37,63 seviyesine kadar inmişti.\n" +
                                "\n" +
                                "Dünya genelinde hızla yayılan Kovid-19 salgını nedeniyle ekonomik büyüme beklentileri ve petrol tüketimi zayıflamaya devam ediyor. Küresel petrol piyasasındaki arz fazlası her geçen gün artıyor.\n" +
                                "\n" +
                                "Petrol İhraç Eden Ülkeler Örgütü (OPEC) ve OPEC dışı bazı ham petrol üreticisi ülkeler 1 Mayıs'tan itibaren toplam ham petrol üretimlerini 2 ay boyunca günlük 9,7 milyon varil azaltma kararı almıştı.")

                )
        );
    }

    public void initPermissions(){
        permissionRepository.saveAll(
                Arrays.asList(
                    new Permission().setId(1).setName("Haber Oluşturma Yetkisi").setDescription("Mevcut haber türlerinden birine haber oluşturma yetkisi")
                    .setGroups(
                            Arrays.asList(
                                    new Group().setId(Grp.EDITOR)
                            )
                    )
                )
        );
    }

}
