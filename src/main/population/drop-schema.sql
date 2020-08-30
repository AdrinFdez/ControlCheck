
    alter table `accounting_record` 
       drop 
       foreign key `FK41jm4vk7runvmg5tderffrele`;

    alter table `accounting_record` 
       drop 
       foreign key `FKk1pmfnppwk0kav7xloy8u71uq`;

    alter table `activity` 
       drop 
       foreign key `FKsi3ivubkr0ib5fqb6qv2k7i19`;

    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `authenticated_discussion_forum` 
       drop 
       foreign key `FKr53m4p1houvxgp8vjmm3n803v`;

    alter table `authenticated_discussion_forum` 
       drop 
       foreign key `FKsf8m0fe6n6jstuvfs79frwnma`;

    alter table `banner` 
       drop 
       foreign key `FKt25tdecrkq8bk0wiim2sagjne`;

    alter table `banner` 
       drop 
       foreign key `FKr19baq0bri0akndc7ruwhngy4`;

    alter table `banner` 
       drop 
       foreign key `FKdocr1jjfgwx9ef5jbf675l360`;

    alter table `bookkeeper` 
       drop 
       foreign key FK_krvjp9eaqyapewl2igugbo9o8;

    alter table `bookkeeper_request` 
       drop 
       foreign key `FKftryt23yr3n657pg7ccofc7xn`;

    alter table `credit_card` 
       drop 
       foreign key `FKq3t0lsolo610xt1xti5scf4qe`;

    alter table `credit_card` 
       drop 
       foreign key `FK31e9eqi896koc93q7yjs5yoox`;

    alter table `discussion_forum` 
       drop 
       foreign key `FKmcgrpw22g3baap51wq319v1bp`;

    alter table `discussion_forum` 
       drop 
       foreign key `FK3i1o4uw3r4aktnjntn8ahfy6u`;

    alter table `entrepreneur` 
       drop 
       foreign key FK_r6tqltqvrlh1cyy8rsj5pev1q;

    alter table `investment_round` 
       drop 
       foreign key `FKkj1l8c2ftn9c65y061me6t37j`;

    alter table `investment_round_application` 
       drop 
       foreign key `FK7lj49vrs49hm8siv4o9ytbcn6`;

    alter table `investment_round_application` 
       drop 
       foreign key `FK52jbh9l5j9pmabs2n18pbtvu8`;

    alter table `investor` 
       drop 
       foreign key FK_dcek5rr514s3rww0yy57vvnpq;

    alter table `message` 
       drop 
       foreign key `FK7ju7uxmh5mdbjgrfwgoem3eqd`;

    alter table `message` 
       drop 
       foreign key `FKik4epe9dp5q6uenarfyia7xin`;

    alter table `patron` 
       drop 
       foreign key `FKpj4cod0bcxwxg4nqv4f2xkikg`;

    alter table `patron` 
       drop 
       foreign key FK_8xx5nujhuio3advxc2freyu65;

    alter table `work_programme` 
       drop 
       foreign key `FK3nxyaik1cnvfdg02p9a8ibiko`;

    drop table if exists `accounting_record`;

    drop table if exists `activity`;

    drop table if exists `administrator`;

    drop table if exists `anonymous`;

    drop table if exists `authenticated`;

    drop table if exists `authenticated_discussion_forum`;

    drop table if exists `banner`;

    drop table if exists `bookkeeper`;

    drop table if exists `bookkeeper_request`;

    drop table if exists `cadenas_bulletin`;

    drop table if exists `challenge`;

    drop table if exists `credit_card`;

    drop table if exists `cruz_bulletin`;

    drop table if exists `custom_params`;

    drop table if exists `discussion_forum`;

    drop table if exists `entrepreneur`;

    drop table if exists `fernandez_bulletin`;

    drop table if exists `inquiry`;

    drop table if exists `investment_round`;

    drop table if exists `investment_round_application`;

    drop table if exists `investor`;

    drop table if exists `message`;

    drop table if exists `notice`;

    drop table if exists `overture`;

    drop table if exists `patron`;

    drop table if exists `pinero_bulletin`;

    drop table if exists `rueda_bulletin`;

    drop table if exists `technology_record`;

    drop table if exists `tool_record`;

    drop table if exists `user_account`;

    drop table if exists `work_programme`;

    drop table if exists `hibernate_sequence`;
