
    create table `accounting_record` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_moment` datetime(6),
        `status` varchar(255),
        `title` varchar(255),
        `bookkeeper_id` integer not null,
        `investment_round_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `activity` (
       `id` integer not null,
        `version` integer not null,
        `budget_amount` double precision,
        `budget_currency` varchar(255),
        `end_date` datetime(6),
        `start_date` datetime(6),
        `title` varchar(255),
        `work_programme_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated_discussion_forum` (
       `id` integer not null,
        `version` integer not null,
        `forum_id` integer not null,
        `user_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `banner` (
       `id` integer not null,
        `version` integer not null,
        `imageurl` varchar(2048),
        `slogan` varchar(255),
        `targeturl` varchar(2048),
        `administrator_id` integer,
        `credit_card_id` integer not null,
        `patron_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `bookkeeper` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm` varchar(255),
        `responsibility_statement` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `bookkeeper_request` (
       `id` integer not null,
        `version` integer not null,
        `firm` varchar(255),
        `responsibility_statement` varchar(255),
        `status` bit,
        `user_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `cadenas_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `address` varchar(1024),
        `city` varchar(255),
        `email` varchar(320),
        `gender` varchar(255),
        `name` varchar(255),
        `surname` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `deadline_date` datetime(6),
        `description` varchar(255),
        `goal_average` varchar(255),
        `goal_expert` varchar(255),
        `goal_rookie` varchar(255),
        `reward_average_amount` double precision,
        `reward_average_currency` varchar(255),
        `reward_expert_amount` double precision,
        `reward_expert_currency` varchar(255),
        `reward_rookie_amount` double precision,
        `reward_rookie_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `credit_card` (
       `id` integer not null,
        `version` integer not null,
        `brand` varchar(255),
        `card_number` varchar(255),
        `cvv` integer not null,
        `expiration_month` integer not null,
        `expiration_year` integer not null,
        `holder` varchar(255),
        `administrator_id` integer,
        `patron_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `cruz_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `email` varchar(320),
        `gender` varchar(255),
        `name` varchar(255),
        `phone_number` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `custom_params` (
       `id` integer not null,
        `version` integer not null,
        `sectors` varchar(255),
        `spam_threshold` double precision,
        `spam_words` varchar(1024),
        primary key (`id`)
    ) engine=InnoDB;

    create table `discussion_forum` (
       `id` integer not null,
        `version` integer not null,
        `creation_moment` datetime(6),
        `title` varchar(255),
        `investment_round_id` integer not null,
        `owner_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `entrepreneur` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `name` varchar(255),
        `qualification` varchar(255),
        `sector` varchar(255),
        `skill` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `fernandez_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `address` varchar(1024),
        `birthday` datetime(6),
        `country` varchar(255),
        `email` varchar(320),
        `name` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `inquiry` (
       `id` integer not null,
        `version` integer not null,
        `creation` datetime(6),
        `deadline` datetime(6),
        `description` varchar(1024),
        `mail` varchar(320),
        `money_max_amount` double precision,
        `money_max_currency` varchar(255),
        `money_min_amount` double precision,
        `money_min_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `investment_round` (
       `id` integer not null,
        `version` integer not null,
        `creation_moment` datetime(6),
        `description` varchar(255),
        `kind` varchar(255),
        `link` varchar(2048),
        `money_amount` double precision,
        `money_currency` varchar(255),
        `status` varchar(255),
        `text` varchar(2048),
        `ticker` varchar(255),
        `title` varchar(255),
        `entrepreneur_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `investment_round_application` (
       `id` integer not null,
        `version` integer not null,
        `checked` bit,
        `creation_moment` datetime(6),
        `justification` varchar(1024),
        `link` varchar(255),
        `offer_amount` double precision,
        `offer_currency` varchar(255),
        `pass` varchar(255),
        `statement` varchar(255),
        `ticker` varchar(255),
        `update_moment` datetime(6),
        `investment_round_id` integer not null,
        `investor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `investor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `profile` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `message` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(1024),
        `creation_moment` datetime(6),
        `tags` varchar(255),
        `title` varchar(255),
        `forum_id` integer not null,
        `user_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `notice` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_date` datetime(6),
        `deadline_date` datetime(6),
        `header_picture` varchar(2048),
        `optional_link1` varchar(2048),
        `optional_link2` varchar(2048),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `overture` (
       `id` integer not null,
        `version` integer not null,
        `creation` datetime(6),
        `deadline` datetime(6),
        `description` varchar(1024),
        `mail` varchar(255),
        `money_max_amount` double precision,
        `money_max_currency` varchar(255),
        `money_min_amount` double precision,
        `money_min_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `patron` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `organization_name` varchar(255),
        `credit_card_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `pinero_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `address` varchar(255),
        `date` datetime(6),
        `description` varchar(255),
        `email` varchar(320),
        `name` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `rueda_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `comment` varchar(255),
        `email` varchar(320),
        `name` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `technology_record` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(1024),
        `email` varchar(320),
        `inventor_name` varchar(255),
        `open_source` bit,
        `rating` integer,
        `sector` varchar(255),
        `title` varchar(255),
        `website` varchar(2048),
        primary key (`id`)
    ) engine=InnoDB;

    create table `tool_record` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(1024),
        `email` varchar(320),
        `inventor_name` varchar(255),
        `open_source` bit,
        `rating` integer,
        `sector` varchar(255),
        `title` varchar(255),
        `website` varchar(2048),
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email_display_name` varchar(255),
        `identity_email_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `work_programme` (
       `id` integer not null,
        `version` integer not null,
        `investment_round_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );
create index IDXq2kmppvjve3ly0vmhmm5fohq3 on `bookkeeper` (`firm`, `responsibility_statement`);
create index IDXt0jk3y3ri0nv4ljk7kwcwku2d on `bookkeeper_request` (`status`);

    alter table `investment_round` 
       add constraint UK_408l1ohatdkkut5bkt0eu6ifs unique (`ticker`);

    alter table `investment_round_application` 
       add constraint UK_19trt3covn07b80eur5ubs1ye unique (`ticker`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `accounting_record` 
       add constraint `FK41jm4vk7runvmg5tderffrele` 
       foreign key (`bookkeeper_id`) 
       references `bookkeeper` (`id`);

    alter table `accounting_record` 
       add constraint `FKk1pmfnppwk0kav7xloy8u71uq` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `activity` 
       add constraint `FKsi3ivubkr0ib5fqb6qv2k7i19` 
       foreign key (`work_programme_id`) 
       references `work_programme` (`id`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `authenticated_discussion_forum` 
       add constraint `FKr53m4p1houvxgp8vjmm3n803v` 
       foreign key (`forum_id`) 
       references `discussion_forum` (`id`);

    alter table `authenticated_discussion_forum` 
       add constraint `FKsf8m0fe6n6jstuvfs79frwnma` 
       foreign key (`user_id`) 
       references `authenticated` (`id`);

    alter table `banner` 
       add constraint `FKt25tdecrkq8bk0wiim2sagjne` 
       foreign key (`administrator_id`) 
       references `administrator` (`id`);

    alter table `banner` 
       add constraint `FKr19baq0bri0akndc7ruwhngy4` 
       foreign key (`credit_card_id`) 
       references `credit_card` (`id`);

    alter table `banner` 
       add constraint `FKdocr1jjfgwx9ef5jbf675l360` 
       foreign key (`patron_id`) 
       references `patron` (`id`);

    alter table `bookkeeper` 
       add constraint FK_krvjp9eaqyapewl2igugbo9o8 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `bookkeeper_request` 
       add constraint `FKftryt23yr3n657pg7ccofc7xn` 
       foreign key (`user_id`) 
       references `authenticated` (`id`);

    alter table `credit_card` 
       add constraint `FKq3t0lsolo610xt1xti5scf4qe` 
       foreign key (`administrator_id`) 
       references `administrator` (`id`);

    alter table `credit_card` 
       add constraint `FK31e9eqi896koc93q7yjs5yoox` 
       foreign key (`patron_id`) 
       references `patron` (`id`);

    alter table `discussion_forum` 
       add constraint `FKmcgrpw22g3baap51wq319v1bp` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `discussion_forum` 
       add constraint `FK3i1o4uw3r4aktnjntn8ahfy6u` 
       foreign key (`owner_id`) 
       references `authenticated` (`id`);

    alter table `entrepreneur` 
       add constraint FK_r6tqltqvrlh1cyy8rsj5pev1q 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `investment_round` 
       add constraint `FKkj1l8c2ftn9c65y061me6t37j` 
       foreign key (`entrepreneur_id`) 
       references `entrepreneur` (`id`);

    alter table `investment_round_application` 
       add constraint `FK7lj49vrs49hm8siv4o9ytbcn6` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `investment_round_application` 
       add constraint `FK52jbh9l5j9pmabs2n18pbtvu8` 
       foreign key (`investor_id`) 
       references `investor` (`id`);

    alter table `investor` 
       add constraint FK_dcek5rr514s3rww0yy57vvnpq 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `message` 
       add constraint `FK7ju7uxmh5mdbjgrfwgoem3eqd` 
       foreign key (`forum_id`) 
       references `discussion_forum` (`id`);

    alter table `message` 
       add constraint `FKik4epe9dp5q6uenarfyia7xin` 
       foreign key (`user_id`) 
       references `authenticated` (`id`);

    alter table `patron` 
       add constraint `FKpj4cod0bcxwxg4nqv4f2xkikg` 
       foreign key (`credit_card_id`) 
       references `credit_card` (`id`);

    alter table `patron` 
       add constraint FK_8xx5nujhuio3advxc2freyu65 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `work_programme` 
       add constraint `FK3nxyaik1cnvfdg02p9a8ibiko` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);
