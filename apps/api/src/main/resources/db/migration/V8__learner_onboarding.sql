create table learner_onboarding (
    user_id varchar(64) primary key references app_user(id) on delete cascade,
    age_range varchar(32),
    occupation varchar(32) not null,
    occupation_description varchar(120),
    learning_motivations_json text not null,
    primary_goal varchar(240) not null,
    difficulty_skills_json text not null,
    current_level varchar(32) not null,
    weekly_availability_minutes integer not null,
    completed_at timestamptz not null,
    updated_at timestamptz not null,
    constraint ck_onboarding_weekly_minutes check (weekly_availability_minutes between 1 and 10080),
    constraint ck_onboarding_other_description check (
        (occupation = 'OTHER' and occupation_description is not null and length(trim(occupation_description)) > 0)
        or (occupation <> 'OTHER' and occupation_description is null)
    )
);
